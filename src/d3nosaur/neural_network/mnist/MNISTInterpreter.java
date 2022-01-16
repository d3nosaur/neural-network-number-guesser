package d3nosaur.neural_network.mnist;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MNISTInterpreter {
	public static MNISTMatrix[] readData(String dataPath, String labelPath) throws IOException {
		DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(dataPath)));
		int dataMagicNumber = dataInputStream.readInt();
		int dataItemCount = dataInputStream.readInt();
		int rows = dataInputStream.readInt();
		int columns = dataInputStream.readInt();
		
		System.out.println("Data Magic Number: " + dataMagicNumber);
		System.out.println("Data Item Count: " + dataItemCount);
		System.out.println("Row Count: " + rows);
		System.out.println("Column Count: " + columns);
		
		DataInputStream labelInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(labelPath)));
		int labelMagicNumber = labelInputStream.readInt();
		int labelItemCount = labelInputStream.readInt();
		
		System.out.println("Label Magic Number: " + labelMagicNumber);
		System.out.println("Label Item Count: " + labelItemCount);
		
		MNISTMatrix[] data = new MNISTMatrix[dataItemCount];
		
		assert dataItemCount == labelItemCount : "Item Counts are not Equal";
		
		for(int i=0; i<dataItemCount; i++) {
			MNISTMatrix matrix = new MNISTMatrix(rows, columns);
			matrix.setLabel(labelInputStream.readUnsignedByte());
			
			for(int r=0; r<rows; r++) {
				for(int c=0; c<columns; c++) {
					matrix.setValue(r, c, dataInputStream.readUnsignedByte());
				}
			}
			
			data[i] = matrix;
		}
		
		dataInputStream.close();
		labelInputStream.close();
		
		return data;
	}
	
	public static void printData(MNISTMatrix matrix) {
		System.out.println("Label: " + matrix.getLabel());
		
		for(int r=0; r<matrix.getRows(); r++) {
			for(int c=0; c<matrix.getColumns(); c++) {
				System.out.print(matrix.getValue(r, c) + " ");
			}
			System.out.println();
		}
	}
}
