package tetris;

import java.awt.Point;
import java.awt.Rectangle;

public class Block {
	
	int width = 20, height = 20, x, y;

	public Block(Point p) {
		x = p.x;
		y = p.y;
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
		x += newX;
	}
	
	public void setY(int newY) {
		y += newY;
	}
	
}
