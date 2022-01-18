package d3nosaur.neural_network.mnist;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MNISTInterpreter {
	@SuppressWarnings("unused")
	public static MNISTMatrix[] readData(String dataPath, String labelPath) throws IOException {
		DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(dataPath)));
		int dataMagicNumber = dataInputStream.readInt();
		int dataItemCount = dataInputStream.readInt();
		int rows = dataInputStream.readInt();
		int columns = dataInputStream.readInt();

		DataInputStream labelInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(labelPath)));
		int labelMagicNumber = labelInputStream.readInt();
		int labelItemCount = labelInputStream.readInt();
		
		
		MNISTMatrix[] data = new MNISTMatrix[dataItemCount];
		
		assert dataItemCount == labelItemCount : "Item Counts are not Equal";
		
		// Turns the data collected into MNISTMatrix's and adds to data array
		for(int i=0; i<dataItemCount; i++) {
			MNISTMatrix matrix = new MNISTMatrix(rows, columns);
			matrix.setLabel(labelInputStream.readUnsignedByte());
			
			for(int x=0; x<matrix.getWidth(); x++) {
				for(int y=0; y<matrix.getHeight(); y++) {
					matrix.setValue(y, x, dataInputStream.readUnsignedByte() / 255f);
				}
			}
			
			data[i] = matrix;
		}
		
		dataInputStream.close();
		labelInputStream.close();
		
		return data;
	}
}
