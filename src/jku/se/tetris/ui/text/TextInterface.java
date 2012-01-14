package jku.se.tetris.ui.text;

import java.io.PrintStream;

import jku.se.tetris.control.GameController;
import jku.se.tetris.model.GameDataChangedListener;
import jku.se.tetris.model.GameEventListener;
import jku.se.tetris.model.GameField;
import jku.se.tetris.ui.UserInterface;

/**
 * @author Markus Hofmarcher
 * 
 *         Implementation of a textual user interface for tetris.
 */
public class TextInterface implements UserInterface, GameDataChangedListener, GameEventListener {

	private PrintStream out;

	// ---------------------------------------------------------------------------

	private GameController controller;

	// ---------------------------------------------------------------------------

	/**
	 * Create a new textual user interface.
	 * 
	 * @param gamefield
	 *            the game field model which is to be represented by this UI
	 *            implementation
	 * @param controller
	 *            the controller class which controls this game
	 */
	public TextInterface(GameField gamefield, GameController controller) {
		this.controller = controller;
		// --
		gamefield.addDataChangedListener(this);
		gamefield.addGameEventListener(this);
		// --
		out = System.out;
	}

	// ---------------------------------------------------------------------------

	private void println(String s) {
		out.println(s);
	}

	// ---------------------------------------------------------------------------

	@Override
	public void beginn() {
		controller.start();
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
	public void stoneRotatedClockwise() {
		println("Stone rotated");
	}

	@Override
	public void stoneRotatedCounterClockwise() {
		// ignore
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
			if (i > 0 && !(i == rows.length-1)) rowString += ", ";
			if (i == rows.length-1) rowString += " and ";
			rowString += String.valueOf(rows[i]);
			//@formatter:on
		}
		// --
		println("The row" + (rows.length > 1 ? "s " : " ") + rowString + (rows.length > 1 ? " have" : " has") + " been completed");
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
		println("-----------------------------------------");
		println("Game Over! Final Score: " + score);
		println("-----------------------------------------");
	}
}
