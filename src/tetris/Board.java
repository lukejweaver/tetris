package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	TetrisFrame tf;
	
	ArrayList<Block> tPiece = new ArrayList<Block>();
	
	public Board() {
		setupPanel();
		createTetrisFrame();
		createTBlock();
		Timer t = new Timer(300, this);
		t.start();
	}
	
	private void createTBlock() {
		tPiece.add(new Block(new Point(165, 20)));
		tPiece.add(new Block(new Point(145, 20)));
		tPiece.add(new Block(new Point(185, 20)));
		tPiece.add(new Block(new Point(165, 0)));
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
	
	void paintCurrentPiece (Graphics2D g2d) {
		for (Block block : tPiece) {
			g2d.setColor(new Color(255, 106, 106));
			g2d.fillRoundRect(block.getX(), block.getY(), block.getWidth(), block.getHeight(), 5, 5);
			g2d.setColor(Color.BLACK);
			g2d.drawRoundRect(block.getX(), block.getY(), block.getWidth(), block.getHeight(), 5, 5);
			block.setY(20);

		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		paintCurrentPiece(g2d);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
	}
}
