package d3nosaur.neural_network;

import java.awt.image.BufferedImage;

import d3nosaur.neural_network.mnist.MNISTMatrix;
import d3nosaur.paint.PredictionPanel;

public class Predictions {
	public Predictions() {
		PredictionPanel.clearPrediction();
	}
	
	// Returns an MNISTMatrix of the BufferedImage
	public static MNISTMatrix imageToMatrix(BufferedImage image) {
		int h = image.getHeight();
		int w = image.getWidth();
		
		MNISTMatrix matrix = new MNISTMatrix(w, h);
		matrix.setLabel(-1);
		
		for(int x=0; x<w; x++) {
			for(int y=0; y<h; y++) {
				int color = image.getRGB(x, y);
				int value = 255 - (color & 0x000000ff);
				
				matrix.setValue(x, y, value);
			}
		}
		
		return matrix;
	}
}
