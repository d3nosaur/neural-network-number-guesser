package d3nosaur.paint;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import d3nosaur.Main;
import d3nosaur.neural_network.Predictions;

// Turns mouse inputs into points to create curves on a paint panel
public class MousePaint extends MouseAdapter {
	private PaintPanel paintPanel;
	
	public MousePaint(PaintPanel paintPanel) {
		this.paintPanel = paintPanel;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e))
			paintPanel.curveStart(e.getPoint());
		else if(SwingUtilities.isRightMouseButton(e)) {
			paintPanel.clearPanel();
			PredictionPanel.clearPrediction();
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
			paintPanel.curveEnd(e.getPoint());
			PredictionPanel.setPrediction(Main.network.predict(Predictions.imageToMatrix(paintPanel.getCompressedImage(28, 28))));
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e))
			paintPanel.curveAdd(e.getPoint());
	}
}
