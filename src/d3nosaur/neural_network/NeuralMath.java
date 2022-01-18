package d3nosaur.neural_network;

import java.util.Random;

public class NeuralMath {
	private static Random rand = new Random();
	
	public static double sig(double x) {
		return (1/(1+(Math.pow(Math.E, -1*x))));
	}
	
	public static double[] sig(double[] array) {
		double[] output = new double[array.length];
		
		for(int i=0; i<array.length; i++) {
			output[i] = sig(array[i]);
		}
		
		return output;
	}
	
	public static double ReLU(double x) {
		return Math.max(0, x);
	}
	
	public static double[] ReLU(double[] array) {
		double[] output = new double[array.length];
				
		for(int i=0; i<array.length; i++) {
			output[i] = ReLU(array[i]);
		}
				
		return output;
	}
	
	public static double[] activation(double[] array) {
		return sig(array);
	}
	
	// Returns a 2D double matrix with values following the normal distribution
	public static DoubleMatrix randomMatrix(int rows, int columns) {
		DoubleMatrix matrix = new DoubleMatrix(rows, columns);
		
		System.out.println(rows + ", " + columns);
		
		for(int y=0; y<columns; y++) {
			for(int x=0; x<rows; x++) {
				matrix.setValue(x, y, rand.nextDouble());
				//System.out.print(matrix.getValue(x, y) + " ");
			}
		}
		
		return matrix;
	}
	
	public static DoubleMatrix zeroMatrix(int rows, int columns) {
		DoubleMatrix matrix = new DoubleMatrix(rows, columns);
		
		for(int y=0; y<columns; y++) {
			for(int x=0; x<rows; x++) {
				matrix.setValue(x, y, 0);
			}
		}
		
		return matrix;
	}
	
	public static double[] arrayAppend(double[] inputVector, int number) {
		double[] newArray = new double[inputVector.length + 1];
		newArray[inputVector.length] = number;
		
		for(int i=0; i<inputVector.length; i++) {
			newArray[i] = inputVector[i];
		}
		
		return newArray;
	}
	
	public static int[] arrayAppend(int[] inputVector, int number) {
		int[] newArray = new int[inputVector.length + 1];
		newArray[inputVector.length] = number;
		
		for(int i=0; i<inputVector.length; i++) {
			newArray[i] = inputVector[i];
		}
		
		return newArray;
	}
	
	public static double inner(double[] arr1, double[] arr2) {
		return sum(multiply(arr1, arr2));
	}
	
	public static double[] inner(DoubleMatrix layer, double[] bias) {
		double[] output = new double[layer.getHeight()];
		
		for(int i=0; i<layer.getHeight(); i++) {
			output[i] = inner(layer.getRow(i), bias);
		}
		
		return output;
	}
	
	public static double[] multiply(double[] arr1, double[] arr2) {
		double[] output = new double[arr1.length];
		
		for(int i=0; i<arr1.length; i++) {
			output[i] = arr1[i] * arr2[i];
		}
		
		return output;
	}

	public static double[] multiply(double number, double[] arr1) {
		double[] output = new double[arr1.length];
		
		for(int i=0; i<arr1.length; i++) {
			output[i] = arr1[i] * number;
		}
		
		return output;
	}
	
	public static DoubleMatrix multiply(double number, DoubleMatrix matrix) {
		DoubleMatrix output = new DoubleMatrix(matrix.getWidth(), matrix.getHeight());
		
		for(int x=0; x<matrix.getWidth(); x++) {
			for(int y=0; y<matrix.getHeight(); y++) {
				output.setValue(x, y, number * matrix.getValue(x, y));
			}
		}
		
		return output;
	}
	
	public static double[] divide(double[] arr1, double number) {
		double[] output = new double[arr1.length];
		
		for(int i=0; i<arr1.length; i++) {
			output[i] = arr1[i] / number;
		}
		
		return output;
	}
	
	public static DoubleMatrix divide(DoubleMatrix matrix, double number) {
		DoubleMatrix output = new DoubleMatrix(matrix.getWidth(), matrix.getHeight());
		
		for(int x=0; x<matrix.getWidth(); x++) {
			for(int y=0; y<matrix.getHeight(); y++) {
				output.setValue(x, y, matrix.getValue(x, y) / number);
			}
		}
		
		return output;
	}
	
	public static DoubleMatrix subtract(DoubleMatrix matrix1, DoubleMatrix matrix2) {
		DoubleMatrix output = new DoubleMatrix(matrix1.getWidth(), matrix1.getHeight());
		
		for(int x=0; x<matrix1.getWidth(); x++) {
			for(int y=0; y<matrix1.getHeight(); y++) {
				output.setValue(x, y, matrix1.getValue(x, y) - matrix2.getValue(x, y));
			}
		}
		
		return output;
	}
	
	public static double[] subtract(double[] arr1, double[] arr2) {
		double[] output = new double[arr1.length];
		
		for(int i=0; i<arr1.length; i++) {
			output[i] = arr1[i] - arr2[i];
		}
		
		return output;
	}
	
	public static double[] subtract(int number, double[] arr1) {
		double[] output = new double[arr1.length];
		
		for(int i=0; i<arr1.length; i++) {
			output[i] = number - arr1[i];
		}
		
		return output;
	}
	
	public static double[] subtract(double[] arr1, int number) {
		double[] output = new double[arr1.length];
		
		for(int i=0; i<arr1.length; i++) {
			output[i] = arr1[i] - number;
		}
		
		return output;
	}
	
	public static double[] subtract(double[] arr1, int index, int subtract) {
		double[] output = arr1.clone();
		output[index]-=subtract;
		
		return output;
	}
	
	public static double sum(double[] ... arrays) {
		double sum = 0;
		
		for(double[] array : arrays) {
			for(double i : array) {
				sum+=i;
			}
		}
		
		return sum;
	}
	
	public static int maxArg(double[] array) {
		int maxIndex = 0;
		double maxValue = 0;
		
		for(int i=0; i<array.length; i++) {
			if(array[i] > maxValue) {
				maxIndex = i;
				maxValue = array[i];
			}
		}
		
		return maxIndex;
	}
	
	public static DoubleMatrix outer(double[] arr1, double[] arr2) {
		DoubleMatrix matrix = new DoubleMatrix(arr2.length, arr1.length);
		
		for(int y=0; y<arr1.length; y++) {
			for(int x=0; x<arr2.length; x++) {
				matrix.setValue(x, y, arr1[y] * arr2[x]);
			}
		}
		
		return matrix;
	}
	
	public static DoubleMatrix popColumn(DoubleMatrix matrix) {
		int w = matrix.getWidth() - 1;
		int h = matrix.getHeight();
		
		DoubleMatrix output = new DoubleMatrix(w, h);
		
		for(int x=0; x<w; x++) {
			for(int y=0; y<h; y++) {
				output.setValue(x, y, matrix.getValue(x, y));
			}
		}
		
		return output;
	}
	
	// Rotate the matrix like a rubix cube
	public static DoubleMatrix rotate(DoubleMatrix matrix) {
		DoubleMatrix output = new DoubleMatrix(matrix.getHeight(), matrix.getWidth());
		
		for(int c=0; c<matrix.getWidth(); c++) {
			double[] hold = matrix.getColumn(c);
			for(int i=0; i<hold.length; i++) {
				output.setValue(hold.length - i - 1, c, hold[i]);
			}
		}
		
		return output;
	}
	
	public static DoubleMatrix dot(DoubleMatrix a, DoubleMatrix b) {
		int r1 = a.getHeight();
		int r2 = b.getHeight();
		int c1 = a.getWidth();
		int c2 = b.getWidth();
		
		if(c1 != r2) {
			System.out.println("Bad dot product");
			return null;
		}
		
		DoubleMatrix output = new DoubleMatrix(r1, c2);
		
		for(int i=0; i<r1; i++) {
			for(int j=0; j<c2; j++) {
				for(int k=0; k<c1; k++) {
					output.setValue(j, i, output.getValue(j, i) + a.getValue(k, i) * b.getValue(j, k));
				}
			}
		}
		
		return output;
	}
	
	public static double[] dot(DoubleMatrix a, double[] b) {
		int ah = a.getHeight();
		int aw = a.getWidth();
		
		if(aw != b.length) {
			System.out.println("Bad dot product");
			return null;
		}
		
		double[] output = new double[ah];
		
		for(int i=0; i<ah; i++) {
			for(int j=0; j<aw; j++) {
				output[i] += a.getValue(j, i) * b[j];
			}
		}
		
		return output;
	}
}
