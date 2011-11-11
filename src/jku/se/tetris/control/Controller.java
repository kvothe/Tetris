package jku.se.tetris.control;

import jku.se.tetris.model.GameDataChangedListener;
import jku.se.tetris.model.GameField;

public class Controller implements GameDataChangedListener {
	private static final long START_SPEED = 100;

	private GameField gamefield;
	private long speed;

	private long gameStart;
	private long gameDuration;

	private boolean signalAbort = false;

	private Thread animator;

	public Controller(GameField gamefield) {
		this.gamefield = gamefield;
		this.speed = START_SPEED;
		// --
		this.gameStart = -1;
		this.gameDuration = -1;
		// --
		this.gamefield.addDataChangedListener(this);
	}

	public void start() {
		stop();
		// --
		signalAbort = false;
		gamefield.newGame();
		// --
		animator = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (!signalAbort) {
						gamefield.moveStoneDown();
						// --
						Thread.sleep(speed);
					}
				} catch (InterruptedException e) {
					// ignore
				}
				// --
				gameDuration = System.currentTimeMillis() - gameStart;
			}

		}, "Tetris Animator");
		// --
		gameStart = System.currentTimeMillis();
		// --
		animator.start();
	}

	public void pause() {
		signalAbort = true; // TODO implement pause instead of abort
	}

	public void stop() {
		signalAbort = true;
		// --
		if (animator != null && animator.isAlive()) {
			animator.interrupt();
			// --
			while (animator.isAlive()) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public long getGameDuration() {
		return gameDuration;
	}

	@Override
	public void scoreChanged(long newScore) {
		// TODO Auto-generated method stub

	}

	@Override
	public void levelChanged(int newLevel) {
		speed = START_SPEED - (newLevel * 50);

	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub

	}
}
