package d3nosaur;

import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import d3nosaur.neural_network.PredictionPanel;
import d3nosaur.neural_network.mnist.MNISTInterpreter;
import d3nosaur.neural_network.mnist.MNISTMatrix;
import d3nosaur.paint.MousePaint;
import d3nosaur.paint.PaintPanel;

public class Main {
	private static void initUIComponents() {
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
	
	public static void main(String[] args) throws IOException {
		initUIComponents();
		
		//MNISTMatrix[] matrix = MNISTInterpreter.readData("data/train-images.idx3-ubyte", "data/train-labels.idx1-ubyte");
		//MNISTInterpreter.printData(matrix[matrix.length - 1]);
	}
}
