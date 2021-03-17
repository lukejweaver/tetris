package tetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener, BlockBlueprints {

	private static final long serialVersionUID = 1L;

	TetrisFrame tf;
	
	TetrominoCollection tetrominoCollection = new TetrominoCollection();
	
	GridBackground gridBackground = new GridBackground();
		
	public Board() {
		setupPanel();
		createTetrisFrame();
		Timer t = new Timer(500, this);
		t.start();
	}

	private void setupPanel() {
		setSize(360, 600);
		setOpaque(false);
		setVisible(true);
	}

	private void createTetrisFrame() {
		tf = newFrame();
		tf.add(this);
		tf.add(gridBackground);
	}

	private TetrisFrame newFrame() {
		return new TetrisFrame();
	}

	void paintCurrentTetromino (Graphics2D g2d) {
		for (Block block : tetrominoCollection.currentTetromino().getBlocks()) {
			g2d.setColor(block.getColor());
			g2d.fill3DRect(block.getX(), block.getY(), block.getWidth(), block.getHeight(), true);
			block.incrementY(20);
		} 			
//		g2d.draw(tetrominoCollection.currentTetromino().getBoundingRectangle()); 
		tetrominoCollection.currentTetromino().moveDown();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		paintCurrentTetromino(g2d);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tetrominoCollection.currentTetromino().rotateBlocks(-90);
		this.repaint();
	}
}
