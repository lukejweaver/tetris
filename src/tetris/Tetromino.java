package tetris;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Tetromino {
	
	ArrayList<Block> blocks = new ArrayList<Block>();
	
	final static char NOBLOCK = '-';
	final static char BLOCK = '+';
	final static char RETURN = '/';
	final static char ROTATIONBLOCK= '*';
	
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
	
	HashMap<Block, Point> projectedBlockPoints = new HashMap<Block, Point>();
	
	Block rotationBlock;
	
	Color tetrominoColor;
	
	int x = 140, y = -80, width = 80, height = 80, dropSpeed = 10, currentLoop = 0, blockContact = 0;
		
	public Tetromino(String blueprint) {
		createPiece(blueprint);
	}
	
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
	
	public boolean shouldTetrominoSet(ArrayList<Block> placedBlocks) {
		boolean isAtBottom = (y + height) == Board.PANELHEIGHT;
		boolean isBlockBelow = false;
		for (Block block : blocks) {
			for (Block placedBlock : placedBlocks) {
				if (block.getX() == placedBlock.getX() && block.getY() + block.getHeight() == placedBlock.getY()) {
					isBlockBelow = true;
				}
			}
		}
		if (isAtBottom || isBlockBelow) {
			blockContact += 1;
		} else {
			blockContact = 0;
		}
		return blockContact == 50;
	}
	
	public void moveDown(ArrayList<Block> placedBlocks) {
		if (currentLoop == dropSpeed) {
			for (Block block : blocks) {
				Point newPoint = new Point(block.getX(), block.getY() + block.getHeight());
				projectedBlockPoints.put(block, newPoint);
			}
			if(!isColliding(placedBlocks)) {
				y += Block.BLOCKHEIGHT;
				moveBlocks();
			}
			currentLoop = 0;
		} else {
			currentLoop++;
		}
	}
	
	public void changeX(int newX, ArrayList<Block> placedBlocks) {
		x += newX;
		for (Block block : blocks) {
			Point newPoint = new Point(block.getX() + newX, block.getY());
			projectedBlockPoints.put(block, newPoint);
		}
		if(!isColliding(placedBlocks)) {
			moveBlocks();
		}
	}
	
	public Rectangle getBoundingRectangle() {
		recalculateBoundingRectangle();
		return new Rectangle(x, y, width, height);
	}
	
	public void rotateBlocks(int rotationAngle, ArrayList<Block> placedBlocks) {
		for (Block block : blocks) {
			Point originalPoint;
			if (rotationAngle > 0) {
				originalPoint = new Point(block.getX() + block.getWidth(), block.getY());
			} else {
				originalPoint = new Point(block.getX(), block.getY() + block.getHeight());
			}
			Point rotatedPoint = rotatePointAroundOrigin(originalPoint, rotationAngle);
			projectedBlockPoints.put(block, rotatedPoint);
		}
		recalculateBoundingRectangle();
		if(!isColliding(placedBlocks)) {
			moveBlocks();
		}
	}
	
	public boolean isColliding(ArrayList<Block> placedBlocks) {
		boolean isColliding = false;
		for (Entry<Block, Point> projectedBlockPoint : projectedBlockPoints.entrySet()) {
			int projectedX = projectedBlockPoint.getValue().x;
			int projectedY = projectedBlockPoint.getValue().y;
			if (projectedX >= Board.PANELWIDTH || projectedX < 0 || projectedY >= Board.PANELHEIGHT) {
				isColliding = true;
				break;
			}
			for (Block placedBlock : placedBlocks) {
				if (projectedX == placedBlock.getX() && projectedY == placedBlock.getY()) {
					isColliding = true;
					break;
				}
			}
			if (isColliding) { break; }
		}
		return isColliding;
	}
	
	private void moveBlocks() {
		for (Entry<Block, Point> projectedBlockPoint : projectedBlockPoints.entrySet()) {
			int projectedX = projectedBlockPoint.getValue().x;
			int projectedY = projectedBlockPoint.getValue().y;
			projectedBlockPoint.getKey().setX(projectedX);
			projectedBlockPoint.getKey().setY(projectedY);
		}
	}
	
	private Point rotatePointAroundOrigin(Point originalPoint, int rotationAngle) {
		Point aroundOrigin = getOriginPoint(originalPoint);
		Point rotatedAroundOrigin = rotatePoint(aroundOrigin, rotationAngle);
		return getOriginalPoint(rotatedAroundOrigin);
	}
	
	private Point getOriginPoint(Point p) {
		int pointX = p.x;
		int pointY = p.y;
		int originX = originPoint().x;
		int originY = originPoint().y;
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
		int originX = originPoint().x;
		int originY = originPoint().y;
		int originalX = pointX + originX;
		int originalY = originY - pointY;
		return new Point(originalX, originalY);
	}
	
	private void recalculateBoundingRectangle() {
		int lowestY = 0, highestY = 0, leftMostX = 0, rightMostX = 0, blockWidth = 0, blockHeight = 0;
		boolean first = true;
		for (Block block : blocks) {
			int blockX = block.getX();
			int blockY = block.getY();
			
			if (first) {
				lowestY = blockY;
				highestY = blockY;
				leftMostX = blockX;
				rightMostX = blockX;
				blockWidth = block.getWidth();
				blockHeight = block.getHeight();
				first = false;
				continue;
			}

			if (blockX > rightMostX) {
				rightMostX = blockX;
			} else if (blockX < leftMostX) {
				leftMostX = blockX;
			}
			
			if (blockY > lowestY) {
				lowestY = blockY;
			} else if (blockY < highestY) {
				highestY = blockY;
			}
		}
		
		y = highestY;
		x = leftMostX;
		width = (rightMostX + blockWidth) - leftMostX;
		height = (lowestY + blockHeight) - highestY;
	}
	
	private Point originPoint() {
		int originX, originY;
		if (rotationBlock == null) {
			originX = x + (width/2);
			originY = y + (height/2);
		}
		else {
			originX = rotationBlock.getX() + (rotationBlock.getWidth()/2);
			originY = rotationBlock.getY() + (rotationBlock.getHeight()/2);
		}
		return new Point(originX, originY);
	}
	
	private void createPiece(String blueprint) {
		int startingX = (int) (Math.round((Board.PANELWIDTH/2)/Block.BLOCKWIDTH) * Block.BLOCKWIDTH - (Block.BLOCKWIDTH*2));
		int x = startingX, y = -Block.BLOCKHEIGHT*4;
		for (int i = 0; i < blueprint.length(); i++){
		    char blueprintCharacter = blueprint.charAt(i);   
		    switch (blueprintCharacter) {
		    // Increment x by 20		    
		    case NOBLOCK: x += Block.BLOCKWIDTH;
		    			  break;
		    // Reset x increment y by 20		    
		    case RETURN: x = startingX;
		    			 y += Block.BLOCKHEIGHT;
		    			 break;
		    // Create block at x, y and increment x by 20
		    case BLOCK: blocks.add(new Block(new Point(x, y), tetrominoColor));
		    			x += Block.BLOCKWIDTH;
		    			break;
		    // Create block at x, y and increment x by 20
		    case ROTATIONBLOCK: rotationBlock = new Block(new Point(x, y), tetrominoColor);
		    					blocks.add(rotationBlock);
				    			x += Block.BLOCKWIDTH;
				    			break;
		    // Sets color for the tetromino		    			
		    default: tetrominoColor = colors.get(blueprintCharacter);
		    		 break;
		    }
		}
	}
}
