package tetris;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
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
		colors.put('p', new Color(167, 45, 237));
		colors.put('o', new Color(237, 174, 47));
		colors.put('b', new Color(66, 197, 245));
		colors.put('d', new Color(47, 57, 237));
		colors.put('r', new Color(235, 64, 52));
		colors.put('g', new Color(50, 237, 53));
		colors.put('y', new Color(225, 237, 50));
	}
	
	Color tetrominoColor;
	
	int x = 140, y = -80, width = 80, height = 80, dropSpeed = 20, currentLoop = 0;
		
	public Tetromino(String blueprint) {
		createPiece(blueprint);
	}
	
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
	
	public void moveDown() {
		if (currentLoop == dropSpeed) {
			y += 20;
			for (Block block : blocks) {
				block.changeY(20);
			}
			currentLoop = 0;
		} else {
			currentLoop++;
		}
	}
	
	public void changeX(int newX) {
		x += newX;
		for (Block block : blocks) {
			block.changeX(newX);
		}
	}
	
	public Rectangle getBoundingRectangle() {
		return new Rectangle(x, y, width, height);
	}
	
	public void rotateBlocks(int rotationAngle) {
		for (Block block : blocks) {
			Point originalPoint;
			if (rotationAngle > 0) {
				originalPoint = new Point(block.getX() + block.getWidth(), block.getY());
			} else {
				originalPoint = new Point(block.getX(), block.getY() + block.getHeight());
			}
			Point rotatedPoint = rotatePointAroundOrigin(originalPoint, rotationAngle);
			block.setX(rotatedPoint.x);
			block.setY(rotatedPoint.y);
		}
	}
	
	public Point rotatePointAroundOrigin(Point originalPoint, int rotationAngle) {
		Point aroundOrigin = getOriginPoint(originalPoint);
		Point rotatedAroundOrigin = rotatePoint(aroundOrigin, rotationAngle);
		return getOriginalPoint(rotatedAroundOrigin);
	}
	
	private Point getOriginPoint(Point p) {
		int pointX = p.x;
		int pointY = p.y;
		int originX = x + (width/2);
		int originY = y + (height/2);
		int aroundOriginX = pointX - originX;
		int aroundOriginY = originY - pointY;
		return new Point(aroundOriginX, aroundOriginY);
	}
	private Point rotatePoint(Point p, int rotationAngle) {
		int pointX = p.x;
		int pointY = p.y;
        
        double radianAngle = Math.toRadians(rotationAngle); 
 
        int rotatedX = (int) Math.round(((pointX*Math.cos(radianAngle)) - (pointY*Math.sin(radianAngle))));
        int rotatedY = (int) Math.round(((pointY*Math.cos(radianAngle)) + (pointX*Math.sin(radianAngle))));

        return new Point(rotatedX, rotatedY);
	}
	
	private Point getOriginalPoint(Point p) {
		int pointX = p.x;
		int pointY = p.y;
		int originX = x + (width/2);
		int originY = y + (height/2);
		int originalX = pointX + originX;
		int originalY = originY - pointY;
		return new Point(originalX, originalY);
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
