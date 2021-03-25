package tetris;

import java.util.ArrayList;

public class Line {

	ArrayList<Block> blocks = new ArrayList<Block>();
	
	int y;
	
	public Line(int yCoord) {
		y = yCoord;
	}
	
	public void addBlock(Block b) {
		blocks.add(b);
	}
	
	public boolean isLineFull() {
		int maxBlockNum = 360/20;
		return blocks.size() == maxBlockNum;
	}
	
	public int getY() {
		return y;
	}
	
	public void moveDown() {
		y += 20;
		System.out.println(blocks.size());
		for (Block block : blocks) {
			block.changeY(20);
		}
	}
	
}
