package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener, BlockBlueprints {

	private static final long serialVersionUID = 1L;

	ArrayList<Line> lines = new ArrayList<Line>();
	
	TetrisFrame tf;
	
	TetrominoCollection tetrominoCollection = new TetrominoCollection();
	
	GridBackground gridBackground = new GridBackground();
	
	JLabel scoreLabel = new JLabel();
	
	LineCollection lineCollection = new LineCollection();
	
	int intScore = 0;
	
	String scoreString = "Score: ";
	
	static final int PANELWIDTH = 360;
	static final int PANELHEIGHT = 600;
		
	public Board() {
		setupPanel();
		createTetrisFrame();
		bindKeys();
		Timer t = new Timer(10, this);
		t.start();
	}

	private void setupPanel() {
		setSize(PANELWIDTH, PANELHEIGHT);
		setOpaque(false);
	    setLayout(new BorderLayout()); 
	    scoreLabel.setHorizontalAlignment(JLabel.RIGHT);
	    scoreLabel.setVerticalAlignment(JLabel.TOP);
	    scoreLabel.setText(scoreString + intScore);
	    scoreLabel.setForeground(Color.WHITE);
		add(scoreLabel);
		setVisible(true);
	}

	private void createTetrisFrame() {
		tf = newFrame();
		tf.add(this);
		tf.add(gridBackground);
	    tf.setVisible(true);
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
	
	void paintLines (Graphics2D g2d) {
		for (Line line : lineCollection.getLines()) {
			for (Block block : line.blocks) {
				g2d.setColor(block.getColor());
				g2d.fill3DRect(block.getX(), block.getY(), block.getWidth(), block.getHeight(), true);
			}			
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		paintCurrentTetromino(g2d);
		paintLines(g2d);
	}
	
	private void updateScore(int linesCleared) {
		intScore += linesCleared;
		scoreLabel.setText(scoreString + intScore);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		tetrominoCollection.currentTetromino().moveDown(lineCollection.getBlocks());
		if (tetrominoCollection.currentTetromino().shouldTetrominoSet(lineCollection.getBlocks())) {
			for (Block block : tetrominoCollection.currentTetromino().blocks) {
				Line line = lineCollection.lineAt(block.getY());
				line.addBlock(block);
			}
			tetrominoCollection.removeTetromino(tetrominoCollection.currentTetromino());
			updateScore(lineCollection.checkLineStatuses());
		}
		this.repaint();
	}
	
	private class MoveLeft extends AbstractAction {

		private static final long serialVersionUID = 1L;

		MoveLeft() {
		};

		@Override
		public void actionPerformed(ActionEvent e) {
			tetrominoCollection.currentTetromino().changeX(-Block.BLOCKWIDTH, lineCollection.getBlocks());;
		}
	}
	
	private class MoveRight extends AbstractAction {

		private static final long serialVersionUID = 1L;

		MoveRight() {
		};

		@Override
		public void actionPerformed(ActionEvent e) {
			tetrominoCollection.currentTetromino().changeX(Block.BLOCKWIDTH, lineCollection.getBlocks());
		}
	}
	
	private class RotateLeft extends AbstractAction {

		private static final long serialVersionUID = 1L;

		RotateLeft() {
		};

		@Override
		public void actionPerformed(ActionEvent e) {
			tetrominoCollection.currentTetromino().rotateBlocks(-90, lineCollection.getBlocks());
		}
	}
	
	private class RotateRight extends AbstractAction {

		private static final long serialVersionUID = 1L;

		RotateRight() {
		};

		@Override
		public void actionPerformed(ActionEvent e) {
			tetrominoCollection.currentTetromino().rotateBlocks(90, lineCollection.getBlocks());
		}
	}
}
