package tetris;

import java.util.ArrayList;

public class Line {

	ArrayList<Block> blocks = new ArrayList<Block>();
	
	public Line() {
		
	}
	
	public void addBlock(Block b) {
		blocks.add(b);
	}
	
}
