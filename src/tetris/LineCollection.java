package tetris;

import java.util.ArrayList;

public class LineCollection {
	
	ArrayList<Line> lines = new ArrayList<Line>();
	
	public LineCollection() {
		
	}
	
	public void add(Line line) {
		lines.add(line);
	}
	
	public ArrayList<Block> getBlocks() {
		ArrayList<Block> allBlocks = new ArrayList<Block>();
		for (Line line : lines) {
			allBlocks.addAll(line.blocks);
		}
		return allBlocks;
	}
	
	public ArrayList<Line> getLines() {
		return lines;
	}
	
	public int checkLineStatuses() {
		ArrayList<Line> linesToRemove = new ArrayList<Line>();
		for (Line line : lines) {
			if (line.isLineFull()) {
				linesToRemove.add(line);
			}
		}
		lines.removeAll(linesToRemove);
		
		ArrayList<Line> linesToMoveDown = new ArrayList<Line>();
		for (Line line : linesToRemove) {
			linesToMoveDown.addAll(linesAbove(line.getY()));
		}
		
		linesToMoveDown.forEach((line) -> line.moveDown());
		
		if (!linesAbove(0).isEmpty()) {
			System.out.println("You lose sucker");
		}
		
		return linesToRemove.size();
	}
	
	public ArrayList<Line> linesAbove(int yCoord) {
		ArrayList<Line> linesToMoveDown = new ArrayList<Line>();
		for (Line line : lines) {
			if (line.getY() < yCoord) {
				linesToMoveDown.add(line);
			}
		}
		
		return linesToMoveDown;
	}
	
	public Line lineAt(int yCoord) {
		Line lineAtY = null;
		for (Line line : lines) {
			if (line.getY() == yCoord) {				
				lineAtY = line;
				break;
			}
		}
		
		if (lineAtY == null) {
			lineAtY = new Line(yCoord);
			lines.add(lineAtY);
		}
		
		return lineAtY;
	}
}
