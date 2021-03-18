package tetris;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Block {
	
	int width = 20, height = 20, x, y;
	
	Color blockColor;

	public Block(Point startingPoint, Color color) {
		x = startingPoint.x;
		y = startingPoint.y;
		blockColor = color;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(width, height);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
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
