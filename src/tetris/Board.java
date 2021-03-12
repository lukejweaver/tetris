package tetris;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	
	TetrisFrame tf;
	
	public Board() {
		setupPanel();
		createTetrisFrame();
	}
	
	private void setupPanel() {
		setSize(350, 600);
		setOpaque(false);
		setVisible(true);
	}
	
	private void createTetrisFrame() {
		tf = newFrame();
		tf.add(this);
	}
	
	private TetrisFrame newFrame() {
		return new TetrisFrame();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(255, 106, 106));
		g2d.fillRoundRect(165, 30, 20, 20, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(165, 30, 20, 20, 5, 5);
		g2d.setColor(new Color(255, 106, 106));
		g2d.fillRoundRect(145, 30, 20, 20, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(145, 30, 20, 20, 5, 5);
		g2d.setColor(new Color(255, 106, 106));
		g2d.fillRoundRect(185, 30, 20, 20, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(185, 30, 20, 20, 5, 5);
		g2d.setColor(new Color(255, 106, 106));
		g2d.fillRoundRect(165, 10, 20, 20, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(165, 10, 20, 20, 5, 5);
		
	}
}
