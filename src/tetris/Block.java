package tetris;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Block {
	
	
	Color blockColor;
	
	static final int BLOCKHEIGHT = 30;
	static final int BLOCKWIDTH = 30;
	
	int x, y;

	public Block(Point startingPoint, Color color) {
		x = startingPoint.x;
		y = startingPoint.y;
		blockColor = color;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(BLOCKWIDTH, BLOCKHEIGHT);
	}
	
	public int getWidth() {
		return BLOCKWIDTH;
	}
	
	public int getHeight() {
		return BLOCKHEIGHT;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int newX) {
		x = newX;
	}
	
	public void setY(int newY) {
		y = newY;
	}
	
	public void changeX(int newX) {
		x += newX;
	}
	
	public void changeY(int newY) {
		y += newY;
	}
	
	public Color getColor() {
		return blockColor;
	}
}
