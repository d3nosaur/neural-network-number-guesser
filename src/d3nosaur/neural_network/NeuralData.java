package d3nosaur.neural_network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NeuralData {
	public static void saveObject(Object layer, String name) {
		try {
			FileOutputStream fileOut = new FileOutputStream("data/" + name + ".ser");
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(layer);
			objOut.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Object getObject(String name) {
		Object matrix = null;
		
		try {
			FileInputStream fileIn = new FileInputStream("data/" + name + ".ser");
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			matrix = objIn.readObject();
			objIn.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return matrix;
	}
	
	public static boolean doesObjectExist(String name) {
		File checkFile = new File("data/" + name + ".ser");
		return checkFile.exists();
	}
	
	public static void saveBestLayer(DoubleMatrix matrix, String layer) {
		saveObject(matrix, "best_" + layer);
	}
	
	public static DoubleMatrix getBestLayer(String layer) {
		if(doesObjectExist("best_" + layer))
			return (DoubleMatrix) getObject("best_" + layer);
		
		return null;
	}
	
	public static void saveBestAccuracy(float accuracy) {
		saveObject(accuracy, "best_accuracy");
	}
	
	public static float getBestAccuracy() {
		if(doesObjectExist("best_accuracy"))
			return (float) getObject("best_accuracy");
		
		return 0f;
	}
}
