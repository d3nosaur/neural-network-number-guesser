package d3nosaur;

import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import d3nosaur.neural_network.DoubleMatrix;
import d3nosaur.neural_network.NeuralData;
import d3nosaur.neural_network.NeuralNetwork;
import d3nosaur.paint.MousePaint;
import d3nosaur.paint.PaintPanel;
import d3nosaur.paint.PredictionPanel;

public class Main {
	public static NeuralNetwork network = new NeuralNetwork();
	private static boolean training = false;

	private static void initPaintComponents() {
		PaintPanel paintPanel = new PaintPanel();
		MousePaint mousePaint = new MousePaint(paintPanel);
		paintPanel.addMouseListener(mousePaint);
		paintPanel.addMouseMotionListener(mousePaint);
		
		PredictionPanel predictionPanel = new PredictionPanel();
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(predictionPanel);
		container.add(paintPanel);
		
		JFrame frame = new JFrame("Neural Number Guesser");
		frame.getContentPane().add(container);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static NeuralNetwork getNeuralNetwork() {
		return network;
	}
	
	public static void main(String[] args) throws IOException {
		DoubleMatrix hidden = NeuralData.getBestLayer("hidden");
		if(hidden != null)
			network.setHiddenLayer(hidden);
		
		DoubleMatrix output = NeuralData.getBestLayer("output");
		if(output != null)
			network.setOutputLayer(output);
	
		network.setBestAccuracy(NeuralData.getBestAccuracy());
		
		if(training)
			network.train("data/train-images.idx3-ubyte", "data/train-labels.idx1-ubyte");
		else
			initPaintComponents();
	}
}
