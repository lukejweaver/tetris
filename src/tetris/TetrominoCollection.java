package tetris;

import java.util.ArrayList;
import java.util.Random;

public class TetrominoCollection implements BlockBlueprints {
	
	ArrayList<Tetromino> tetrominos = new ArrayList<Tetromino>();
	
	public TetrominoCollection() {}
	
	public ArrayList<Tetromino> getTetrominos() {
		return tetrominos;
	}
	
	public Tetromino currentTetromino() {
		if (tetrominos.isEmpty()) {
			generateTetromino();
		}
		return tetrominos.get(0);
	}
	
	public void removeTetromino(Tetromino tetromino) {
		tetrominos.remove(tetromino);
	}
	
	private void generateTetromino() {
		Tetromino tetromino = new Tetromino(generateBlockBlueprint());
		tetrominos.add(tetromino);
	}
	
	private String generateBlockBlueprint() {
		Random rand = new Random();
		//	Random.nextInt() generates a random number from 0 (inclusive) 
		//	  to the given integer (exclusive)
		int randomIndex = rand.nextInt(blueprints.size());
		return blueprints.get(randomIndex);
	}
}
