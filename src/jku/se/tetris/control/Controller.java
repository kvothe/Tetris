package jku.se.tetris.control;

import jku.se.tetris.model.GameDataChangedListener;
import jku.se.tetris.model.GameField;
import jku.se.tetris.model.exception.InvalidActionException;

public class Controller implements GameDataChangedListener {
	private static final long START_SPEED = 900;

	private GameField gamefield;
	private long speed;

	// ---------------------------------------------------------------------------

	private Thread animator;
	private boolean signalAbort = false;

	// ---------------------------------------------------------------------------

	public Controller(GameField gamefield) {
		this.gamefield = gamefield;
		this.speed = START_SPEED;
		// --
		this.gamefield.addDataChangedListener(this);
	}

	// ---------------------------------------------------------------------------

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
				} catch (InvalidActionException e) {
					// ignore
				}
			}

		}, "Tetris Animator");
		// --
		animator.start();
	}

	// ---------------------------------------------------------------------------

	public void pause() {
		signalAbort = true; // TODO implement pause instead of abort
	}

	public void resume() {
		// TODO
	}

	// ---------------------------------------------------------------------------

	public void stop() {
		signalAbort = true;
		// --
		if (animator != null && animator.isAlive()) {
			animator.interrupt();
		}
	}

	// ---------------------------------------------------------------------------

	@Override
	public void scoreChanged(long newScore) {
		// ignore
	}

	// ---------------------------------------------------------------------------

	@Override
	public void levelChanged(int newLevel) {
		speed = START_SPEED - (long) ((Math.log(newLevel) / Math.log(2) + 1) * 100);
	}

	// ---------------------------------------------------------------------------

	@Override
	public void gameStarted() {
		// ignore
	}

	// ---------------------------------------------------------------------------

	@Override
	public void gameOver(long score, int level, long duration) {
		stop();
	}
}
