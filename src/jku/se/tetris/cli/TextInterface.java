package jku.se.tetris.cli;

import java.io.PrintStream;

import jku.se.tetris.model.GameDataChangedListener;
import jku.se.tetris.model.GameEventListener;

public class TextInterface implements GameDataChangedListener, GameEventListener {

	private PrintStream out;

	// ---------------------------------------------------------------------------

	public TextInterface() {
		out = System.out;
	}

	// ---------------------------------------------------------------------------

	private void println(String s) {
		out.println(s);
	}

	// ---------------------------------------------------------------------------

	@Override
	public void stoneMovedLeft() {
		println("Stone moved to left");
	}

	@Override
	public void stoneMovedRight() {
		println("Stone moved to right");
	}

	@Override
	public void stoneRotated() {
		println("Stone rotated");
	}

	@Override
	public void stoneCollision() {
		println("Stone collided with another stone");
	}

	@Override
	public void stoneAtBottom() {
		println("Stone at bottom");

	}

	@Override
	public void rowComplete(int[] rows) {
		String rowString = "";
		// --
		for (int i = 0; i < rows.length; i++) {
			//@formatter:off
			if (i > 0) rowString += ", ";			
			rowString += String.valueOf(rows[i]);
			//@formatter:on
		}
		// --
		println("The row" + (rows.length > 1 ? "s " : " ") + rowString + (rows.length > 1 ? "have" : "has") + " been completed");
	}

	@Override
	public void scoreChanged(long newScore) {
		println("Score: " + newScore);
	}

	@Override
	public void levelChanged(int newLevel) {
		println("Level: " + newLevel);
	}

	@Override
	public void gameStarted() {
		println("");
		println("-----------------------------------------");
		println("Game Start!");
		println("-----------------------------------------");
	}

	@Override
	public void gameOver(long score, int level, long duration) {
		println("Game Over!");
		println("-----------------------------------------");
		println("Final Score: " + score);
		println("-----------------------------------------");
	}
}
