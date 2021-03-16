package tetris;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Tetromino {
	
	ArrayList<Block> blocks = new ArrayList<Block>();
	
	final static char NOBLOCK = '-';
	final static char BLOCK = '+';
	final static char RETURN = '/';
	
	final static HashMap<Character, Color> colors;
	static {
		colors = new HashMap<Character, Color>();
		colors.put('p', Color.MAGENTA);
		colors.put('o', Color.ORANGE);
		colors.put('b', new Color(66, 197, 245));
		colors.put('d', Color.BLUE);
		colors.put('r', Color.RED);
		colors.put('g', Color.GREEN);
		colors.put('y', Color.YELLOW);
	}
	
	Color tetrominoColor;
		
	public Tetromino(String blueprint) {
		createPiece(blueprint);
	}
	
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
	
	private void createPiece(String blueprint) {
		int startingX = 140;
		int x = startingX, y = -80;
		for (int i = 0; i < blueprint.length(); i++){
		    char blueprintCharacter = blueprint.charAt(i);   
		    switch (blueprintCharacter) {
		    // Increment x by 20		    
		    case NOBLOCK: x += 20;
		    			  break;
		    // Reset x increment y by 20		    
		    case RETURN: x = startingX;
		    			 y += 20;
		    			 break;
		    // Create block at x, y and increment x by 20
		    case BLOCK: blocks.add(new Block(new Point(x, y), tetrominoColor));
		    			x += 20;
		    			break;
		    // Sets color for the tetromino		    			
		    default: tetrominoColor = colors.get(blueprintCharacter);
		    		 break;
		    }
		}
	}

}
