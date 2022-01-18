package d3nosaur.neural_network;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Random;

public class DoubleMatrix implements Serializable {
	private static final long serialVersionUID = -8757133062247135187L;
	protected double[][] matrix;
	protected int width;
	protected int height;
	protected DecimalFormat df = new DecimalFormat("0.0");
	
	public DoubleMatrix(int width, int height) {
		matrix = new double[width][height];
		
		this.width = width;
		this.height = height;
	}
	
	public double[] flatten() {
		double[] flattenedMatrix = new double[width*height];
		
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				flattenedMatrix[(y*height)+x] = matrix[x][y];
			}
		}
		
		return flattenedMatrix;
	}
	
	public double getValue(int x, int y) {
		return matrix[x][y];
	}
	
	public void setValue(int x, int y, double value) {
		matrix[x][y] = value;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public double[] getColumn(int x) {
		return matrix[x];
	}
	
	public double[] getRow(int row) {
		double[] output = new double[width];
		
		for(int i=0; i<width; i++) {
			output[i] = matrix[i][row];
		}
		
		return output;
	}
	
	public void printMatrix() {
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				System.out.print(df.format(matrix[x][y]) + " ");
			}
			System.out.println();
		}
	}
	
	public static DoubleMatrix valueOf(double[] array) {
		DoubleMatrix matrix = new DoubleMatrix(array.length, 1);
		
		for(int i=0; i<array.length; i++) {
			matrix.setValue(i, 0, array[i]);
		}
		
		return matrix;
	}
	
	Random rand = new Random();
	public DoubleMatrix randomize() {
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				matrix[x][y] = rand.nextDouble();
			}
		}
		
		return this;
	}
}
