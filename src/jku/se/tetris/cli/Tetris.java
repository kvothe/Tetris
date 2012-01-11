package jku.se.tetris.cli;

import jku.se.tetris.control.Controller;
import jku.se.tetris.model.GameField;
import jku.se.tetris.model.GameFieldImpl;

public class Tetris {
	private static final int GAME_FIELD_WIDTH = 8;
	private static final int GAME_FIELD_HEIGHT = 20;

	// ---------------------------------------------------------------------------

	private GameField gamefield;
	private TextInterface textinterface;
	private Controller controller;

	// ---------------------------------------------------------------------------

	public Tetris() {
		gamefield = new GameFieldImpl(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
		textinterface = new TextInterface();
		// --
		gamefield.addDataChangedListener(textinterface);
	}

	// ---------------------------------------------------------------------------

	public static void main(String[] args) {
		new Tetris();
	}
}
