package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener, BlockBlueprints {

	private static final long serialVersionUID = 1L;

	TetrisFrame tf;

	Tetromino tetromino;
		
	public Board() {
		setupPanel();
		createTetrisFrame();
		tetromino = new Tetromino(TBLOCK);
		Timer t = new Timer(300, this);
		t.start();
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

	void paintCurrentTetromino (Graphics2D g2d) {
		if (tetromino != null) {
			for (Block block : tetromino.getBlocks()) {
				g2d.setColor(block.getColor());
				g2d.fillRoundRect(block.getX(), block.getY(), block.getWidth(), block.getHeight(), 5, 5);
				g2d.setColor(Color.BLACK);
				g2d.drawRoundRect(block.getX(), block.getY(), block.getWidth(), block.getHeight(), 5, 5);
				block.setY(20);
			} 			
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		paintCurrentTetromino(g2d);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
