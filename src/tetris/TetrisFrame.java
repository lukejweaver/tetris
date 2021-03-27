package tetris;

import java.awt.Color;

import javax.swing.JFrame;

public class TetrisFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public TetrisFrame() {
		super("Tetris");
		// We want it to close when we press the X on the JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(360, 628);
		// Centers the frame
		setLocationRelativeTo(null);
		// Sets background to nice gray color (Subject to change)
		getContentPane().setBackground(new Color(54, 54, 54));
		// Obviously we do not want other people to mess with our sizing!!!
		setResizable(false);
		// After setting it all up, now we show it!
//		setVisible(true);
	}
	
}
