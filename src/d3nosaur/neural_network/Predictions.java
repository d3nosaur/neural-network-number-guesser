package d3nosaur.neural_network;

import java.awt.image.BufferedImage;

import d3nosaur.neural_network.mnist.MNISTMatrix;

public class Predictions {
	public Predictions() {
		PredictionPanel.setPrediction(0);
	}
	
	// Returns an MNISTMatrix of the BufferedImage
	public static MNISTMatrix imageToMatrix(BufferedImage image) {
		int h = image.getHeight();
		int w = image.getWidth();
		
		MNISTMatrix matrix = new MNISTMatrix(w, h);
		matrix.setLabel(-1);
		
		for(int y=0; y<h; y++) {
			for(int x=0; x<w; x++) {
				int color = image.getRGB(x, y);
				int value = 255 - (color & 0x000000ff);
				
				matrix.setValue(y, x, value);
			}
		}
		
		return matrix;
	}
}
