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
		for (Line line : lines) {
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
	
	private ArrayList<Block> getBlocks() {
		ArrayList<Block> allBlocks = new ArrayList<Block>();
		for (Line line : lines) {
			allBlocks.addAll(line.blocks);
		}
		return allBlocks;	
	}
	
	private void updateScore(int linesCleared) {
		intScore += linesCleared;
		scoreLabel.setText(scoreString + intScore);
	}
	
	private void checkLineStatuses() {
		ArrayList<Line> linesToRemove = new ArrayList<Line>();
		for (Line line : lines) {
			if (line.isLineFull()) {
				linesToRemove.add(line);
			}
		}
		lines.removeAll(linesToRemove);

		updateScore(linesToRemove.size());
		
		ArrayList<Line> linesToMoveDown = new ArrayList<Line>();
		for (Line line : linesToRemove) {
			linesToMoveDown.addAll(linesAbove(line.getY()));
		}
		
		linesToMoveDown.forEach((line) -> line.moveDown());
		
		if (!linesAbove(0).isEmpty()) {
			System.out.println("You lose sucker");
		}
	}
	
	private ArrayList<Line> linesAbove(int yCoord) {
		ArrayList<Line> linesToMoveDown = new ArrayList<Line>();
		for (Line line : lines) {
			if (line.getY() < yCoord) {
				linesToMoveDown.add(line);
			}
		}
		
		return linesToMoveDown;
	}
	
	private Line lineAt(int yCoord) {
		Line lineAtY = null;
		for (Line line : lines) {
			if (line.getY() == yCoord) {				
				lineAtY = line;
				break;
			}
		}
		
		if (lineAtY == null) {
			lineAtY = new Line(yCoord);
			lines.add(lineAtY);
		}
		
		return lineAtY;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		tetrominoCollection.currentTetromino().moveDown(getBlocks());
		if (tetrominoCollection.currentTetromino().shouldTetrominoSet(getBlocks())) {
			for (Block block : tetrominoCollection.currentTetromino().blocks) {
				Line line = lineAt(block.getY());
				line.addBlock(block);
			}
			tetrominoCollection.removeTetromino(tetrominoCollection.currentTetromino());
			checkLineStatuses();
		}
		this.repaint();
	}
	
	private class MoveLeft extends AbstractAction {

		private static final long serialVersionUID = 1L;

		MoveLeft() {
		};

		@Override
		public void actionPerformed(ActionEvent e) {
			tetrominoCollection.currentTetromino().changeX(-Block.BLOCKWIDTH, getBlocks());;
		}
	}
	
	private class MoveRight extends AbstractAction {

		private static final long serialVersionUID = 1L;

		MoveRight() {
		};

		@Override
		public void actionPerformed(ActionEvent e) {
			tetrominoCollection.currentTetromino().changeX(Block.BLOCKWIDTH, getBlocks());
		}
	}
	
	private class RotateLeft extends AbstractAction {

		private static final long serialVersionUID = 1L;

		RotateLeft() {
		};

		@Override
		public void actionPerformed(ActionEvent e) {
			tetrominoCollection.currentTetromino().rotateBlocks(-90, getBlocks());
		}
	}
	
	private class RotateRight extends AbstractAction {

		private static final long serialVersionUID = 1L;

		RotateRight() {
		};

		@Override
		public void actionPerformed(ActionEvent e) {
			tetrominoCollection.currentTetromino().rotateBlocks(90, getBlocks());
		}
	}
}
