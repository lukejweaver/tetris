package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GridBackground extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GridBackground() {
		setupPanel();
	}
	
	private void setupPanel() {
		setSize(360, 600);
		setOpaque(false);
		setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.GRAY);
		for (int y = 0; y < 600/20; y++) {
			for (int x = 0; x < 360/20; x++) {
				g2d.drawRect(x*20, y*20, 20, 20);			
			}
		}
	}
}
