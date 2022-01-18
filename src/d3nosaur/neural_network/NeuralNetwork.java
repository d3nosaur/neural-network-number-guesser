package d3nosaur.neural_network;

import java.io.IOException;
import java.util.ArrayList;

import d3nosaur.neural_network.mnist.MNISTInterpreter;
import d3nosaur.neural_network.mnist.MNISTMatrix;

public class NeuralNetwork {
	private int inputSize = 784;
	private int outputSize = 10;
	private int hiddenSize = 16;
	
	private DoubleMatrix hiddenLayer = NeuralMath.divide(new DoubleMatrix(inputSize + 1, hiddenSize).randomize(), hiddenSize);
	private DoubleMatrix outputLayer = NeuralMath.divide(new DoubleMatrix(hiddenSize + 1, outputSize).randomize(), outputSize);
	
	private DoubleMatrix[] layers = {hiddenLayer, outputLayer};
	
	private int iteration = 0;
	private int sameAccuracy = 0;
	private float bestAccuracy = 0;
	
	private String testDataPath = "data/t10k-images.idx3-ubyte";
	private String testLabelPath = "data/t10k-labels.idx1-ubyte";
	
	public void setHiddenLayer(DoubleMatrix layer) {
		this.layers[0] = layer;
	}
	
	public void setOutputLayer(DoubleMatrix layer) {
		this.layers[1] = layer;
	}
	
	public void setBestAccuracy(float accuracy) {
		this.bestAccuracy = accuracy;
	}
	
	public DoubleMatrix getLayer(int index) {
		return layers[index];
	}
	
	public void train(String dataPath, String labelPath) throws IOException {
		MNISTMatrix[] data = MNISTInterpreter.readData(dataPath, labelPath);
		int batchSize = data.length;
		
		System.out.println("Starting training on " + batchSize + " examples");
		
		while(sameAccuracy < 10) {
			for(MNISTMatrix matrix : data)
				backPropogate(matrix);
			
			iteration++;
			
			float accuracy = testAccuracy();
			
			if(accuracy > bestAccuracy) {
				sameAccuracy = 0;
				bestAccuracy = accuracy;
				
				NeuralData.saveBestLayer(layers[0], "hidden");
				NeuralData.saveBestLayer(layers[1], "output");
				NeuralData.saveBestAccuracy(accuracy);
			} else {
				sameAccuracy++;
			}
			
			System.out.println("Iteration: " + iteration + "   Accuracy: " + accuracy + "%   Best Accuracy: " + bestAccuracy);
		}
		
		System.out.println("Finished training. 10 iterations without improvement");
	}

	public ArrayList<double[]> feedForward(MNISTMatrix matrix) {
		ArrayList<double[]> outputs = new ArrayList<double[]>();
		double[] inputVector = matrix.flatten();
		
		for(DoubleMatrix layer : layers) {
			double[] inputBias = NeuralMath.arrayAppend(inputVector, 1);
			double[] output = NeuralMath.inner(layer, inputBias);

			output = NeuralMath.activation(output);
			
			outputs.add(output);
			inputVector = output;
		}

		return outputs;
	}
	
	public void backPropogate(MNISTMatrix matrix) {
		double[] values = matrix.flatten();
		int target = matrix.getLabel();
		
		float coefficient = 1f/(iteration + 10);
		
		ArrayList<double[]> feedOutput = feedForward(matrix);
		double[] hiddenOutputs = feedOutput.get(0);
		double[] outputs = feedOutput.get(1);
		
		double[] outputDeltas = NeuralMath.multiply(NeuralMath.multiply(outputs, NeuralMath.subtract(1, outputs)), NeuralMath.subtract(outputs, target, 1));
		double[] hiddenDeltas = NeuralMath.multiply(NeuralMath.dot(NeuralMath.rotate(NeuralMath.popColumn(layers[1])), outputDeltas), NeuralMath.multiply(hiddenOutputs, NeuralMath.subtract(1, hiddenOutputs)));
		
		layers[1] = NeuralMath.subtract(layers[1], NeuralMath.multiply(coefficient, NeuralMath.outer(outputDeltas, NeuralMath.arrayAppend(hiddenOutputs, 1))));
		layers[0] = NeuralMath.subtract(layers[0], NeuralMath.multiply(coefficient, NeuralMath.outer(hiddenDeltas, NeuralMath.arrayAppend(values, 1))));
	}
	
	public int predict(MNISTMatrix matrix) {
		ArrayList<double[]> feedOutputs = feedForward(matrix);
		
		return NeuralMath.maxArg(feedOutputs.get(feedOutputs.size()-1));
	}
	
	public float testAccuracy() throws IOException {
		MNISTMatrix[] data = MNISTInterpreter.readData(testDataPath, testLabelPath);
		
		float right = 0;
		float wrong = 0;
		
		for(MNISTMatrix matrix : data) {
			int prediction = predict(matrix);
			if(prediction == matrix.getLabel())
				right++;
			else
				wrong++;
		}
		
		return (right/(right + wrong))*100;
	}
	
	public void printArray(double[] array) {
		for(double i : array)
			System.out.print(i + " ");
		System.out.println();
	}
}
