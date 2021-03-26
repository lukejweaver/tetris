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
		int maxBlockNum = Board.PANELWIDTH/Block.BLOCKWIDTH;
		return blocks.size() == maxBlockNum;
	}
	
	public int getY() {
		return y;
	}
	
	public void moveDown() {
		y += Block.BLOCKHEIGHT;
		for (Block block : blocks) {
			block.changeY(Block.BLOCKHEIGHT);
		}
	}
	
}
