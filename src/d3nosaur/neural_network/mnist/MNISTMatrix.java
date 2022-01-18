package d3nosaur.neural_network.mnist;

import d3nosaur.neural_network.DoubleMatrix;

@SuppressWarnings("serial")
public class MNISTMatrix extends DoubleMatrix {
	private int label;
	
	public MNISTMatrix(int rows, int columns) {
		super(rows, columns);
	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}
	
	@Override
	public void printMatrix() {
		System.out.println("Label: " + label);
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				System.out.print(df.format(matrix[x][y]) + " ");
			}
			System.out.println();
		}
	}
}
