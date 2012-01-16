package jku.se.tetris;

import jku.se.tetris.control.GameController;
import jku.se.tetris.control.RealTimeController;
import jku.se.tetris.control.TurnBasedController;
import jku.se.tetris.model.GameField;
import jku.se.tetris.model.GameFieldImpl;
import jku.se.tetris.ui.UserInterface;
import jku.se.tetris.ui.swing.SwingInterface;
import jku.se.tetris.ui.text.TextInterface;

public class Tetris {
	private static final int GAME_FIELD_WIDTH = 8;
	private static final int GAME_FIELD_HEIGHT = 20;
	private static final int BLOCK_SIZE = 30;

	// ---------------------------------------------------------------------------

	private GameField gamefield;
	private UserInterface userinterface;
	private GameController controller;

	// ---------------------------------------------------------------------------

	/**
	 * Create and start a new game of Tetris.
	 * 
	 * @param textInterface
	 *            if true a textual user interface is used, a nice graphical
	 *            user interface is used otherwise
	 * @param width
	 *            the width of the game field in blocks
	 * @param height
	 *            the height of the game field in blocks
	 * @param blocksize
	 *            the size of the blocks (only relevant for graphical user
	 *            interfaces)
	 */
	public Tetris(boolean textInterface, int width, int height, int blocksize) {
		gamefield = new GameFieldImpl(width, height);
		// --
		if (textInterface) {
			//
			// Configure the gamefield for special behavior in accordance to assignment #5
			//
			gamefield.configure(true, false, false);
			// --
			controller = new TurnBasedController(gamefield);
			userinterface = new TextInterface(gamefield, controller);
		} else {
			controller = new RealTimeController(gamefield);
			userinterface = new SwingInterface(gamefield, controller, blocksize);
		}
		// --
		userinterface.beginn();
	}

	// ---------------------------------------------------------------------------

	public static void main(String[] args) {
		boolean textInterface = true;
		int width = GAME_FIELD_WIDTH;
		int height = GAME_FIELD_HEIGHT;
		int blocksize = BLOCK_SIZE;
		// --
		for (String a : args) {
			if (a.equalsIgnoreCase("gui")) {
				textInterface = false;
			} else if (a.startsWith("width=")) {
				width = Integer.parseInt(a.substring("width=".length()));
			} else if (a.startsWith("height=")) {
				height = Integer.parseInt(a.substring("height=".length()));
			} else if (a.startsWith("blocksize=")) {
				blocksize = Integer.parseInt(a.substring("blocksize=".length()));
			}
		}
		// --
		new Tetris(textInterface, width, height, blocksize);
	}
}
