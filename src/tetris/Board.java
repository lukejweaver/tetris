package tetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener, BlockBlueprints {

	private static final long serialVersionUID = 1L;

	TetrisFrame tf;
	
	TetrominoCollection tetrominoCollection = new TetrominoCollection();
	
	GridBackground gridBackground = new GridBackground();
		
	public Board() {
		setupPanel();
		createTetrisFrame();
		bindKeys();
		Timer t = new Timer(10, this);
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
	
	private void bindKeys() {
		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("A"), "left");
		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("D"), "right");
		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("W"), "rotateLeft");
		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("S"), "rotateRight");
		getActionMap().put("left", new MoveLeft());
		getActionMap().put("right", new MoveRight());
		getActionMap().put("rotateLeft", new RotateLeft());
		getActionMap().put("rotateRight", new RotateRight());
	}

	void paintCurrentTetromino (Graphics2D g2d) {
		for (Block block : tetrominoCollection.currentTetromino().getBlocks()) {
			g2d.setColor(block.getColor());
			g2d.fill3DRect(block.getX(), block.getY(), block.getWidth(), block.getHeight(), true);
		}
		g2d.draw(tetrominoCollection.currentTetromino().getBoundingRectangle()); 
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		paintCurrentTetromino(g2d);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tetrominoCollection.currentTetromino().moveDown();
		this.repaint();
	}
	
	private class MoveLeft extends AbstractAction {

		private static final long serialVersionUID = 1L;

		MoveLeft() {
		};

		@Override
		public void actionPerformed(ActionEvent e) {
			tetrominoCollection.currentTetromino().changeX(-20);;
		}
	}
	
	private class MoveRight extends AbstractAction {

		private static final long serialVersionUID = 1L;

		MoveRight() {
		};

		@Override
		public void actionPerformed(ActionEvent e) {
			tetrominoCollection.currentTetromino().changeX(20);;;
		}
	}
	
	private class RotateLeft extends AbstractAction {

		private static final long serialVersionUID = 1L;

		RotateLeft() {
		};

		@Override
		public void actionPerformed(ActionEvent e) {
			tetrominoCollection.currentTetromino().rotateBlocks(-90);;
		}
	}
	
	private class RotateRight extends AbstractAction {

		private static final long serialVersionUID = 1L;

		RotateRight() {
		};

		@Override
		public void actionPerformed(ActionEvent e) {
			tetrominoCollection.currentTetromino().rotateBlocks(90);;
		}
	}
}
