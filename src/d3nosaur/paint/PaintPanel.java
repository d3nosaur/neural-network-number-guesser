package d3nosaur.paint;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class PaintPanel extends JPanel {
	private static final int panelWidth = 500;
	private static final int panelHeight = 500;
	private static final Color bgColor = Color.white;
	private static final Color drawColor = Color.black;
	private static final float strokeWidth = 40f;
	private static final Stroke stroke = new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	
	private BufferedImage bImage = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_BYTE_GRAY);
	private ArrayList<Point> points = new ArrayList<Point>();
	
	public PaintPanel() {
		Graphics g = bImage.getGraphics();
		g.setColor(bgColor);
		g.fillRect(0, 0, panelWidth, panelHeight);
		g.dispose();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bImage, 0, 0, null);
		Graphics2D g2 = (Graphics2D) g;
		drawCurve(g2);
	}
	
	// Adds drawCurve output to Buffered Image
	private void addCurveToBufferedImage() {
		Graphics2D g2 = bImage.createGraphics();
		drawCurve(g2);
		g2.dispose();
	}
	
	// Takes point array and draws them to the screen
	private void drawCurve(Graphics2D g2) {
		g2.setStroke(stroke);
		g2.setColor(drawColor);
		
		if (points != null && points.size() > 1) {
			for (int i = 0; i < points.size() - 1; i++) {
				int x1 = points.get(i).x;
				int y1 = points.get(i).y;
				int x2 = points.get(i + 1).x;
				int y2 = points.get(i + 1).y;
				g2.drawLine(x1, y1, x2, y2);
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(panelWidth, panelHeight);
	}
	
	// Called on left mouse click, initializes new curve
	public void curveStart(Point point) {
		points.clear();
		points.add(point);
	}
	
	// Called on left mouse click release, updates the buffer image
	public void curveEnd(Point point) {
		points.add(point);
		addCurveToBufferedImage();
		
		points.clear();
		repaint();
	}
	
	// Adds the curve progress to the screen when holding left click
	public void curveAdd(Point point) {
		points.add(point);
		repaint();
	}
	
	// Used on right click to clear the screen, resets buffer image
	public void clearPanel() {
		Graphics g = bImage.getGraphics();
		g.setColor(bgColor);
		g.fillRect(0, 0, panelWidth, panelHeight);
		g.dispose();
		repaint();
	}
	
	public BufferedImage getCompressedImage(int width, int height) {
		Image resizedImage = bImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		BufferedImage bResizedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = bResizedImage.getGraphics();
		g.drawImage(resizedImage, 0, 0, null);
		g.dispose();
		
		return bResizedImage;
	}
}
