package d3nosaur.neural_network;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PredictionPanel extends JPanel {
	private final static JLabel predictionLabel = new JLabel("Prediction: ?");
	
	public PredictionPanel() {
		predictionLabel.setFont(new Font("Helvetica", 1, 20));
		
		this.add(predictionLabel);
	}
	
	public static void setPrediction(int value) {
		predictionLabel.setText("Prediction: " + String.valueOf(value));
	}
}
