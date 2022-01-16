package d3nosaur.neural_network.mnist;

public class MNISTMatrix {
	private int[][] matrix;
	private int label;
	private int rows;
	private int columns;
	
	public MNISTMatrix(int rows, int columns) {
		matrix = new int[rows][columns];
		
		this.rows = rows;
		this.columns = columns;
	}
	
	public int getValue(int row, int column) {
		return matrix[row][column];
	}
	
	public void setValue(int row, int column, int value) {
		matrix[row][column] = value;
	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}
}
