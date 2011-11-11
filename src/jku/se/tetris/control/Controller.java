package jku.se.tetris.control;

import jku.se.tetris.model.GameField;

public class Controller {
	private static final int START_SPEED = 1000;

	private GameField gamefield;

	private int level;
	private int speed;

	public Controller(GameField gamefield) {
		this.gamefield = gamefield;
		this.level = 1;
		this.speed = START_SPEED;
	}

	public void start() {
		gamefield.newStone();
		// --
		Thread animator = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

			}

		});
		// --
		animator.start();
	}
}
