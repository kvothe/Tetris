package jku.se.tetris.control;

import jku.se.tetris.model.GameField;
import jku.se.tetris.model.exception.InvalidActionException;

/**
 * @author Markus Hofmarcher
 * 
 *         Real time controller for playing tetris.
 */
public class RealTimeController implements GameController {
	private static long START_SPEED = 800;

	private GameField gamefield;
	private long speed;

	// ---------------------------------------------------------------------------

	private Thread animator;
	private boolean signalAbort = false;

	// ---------------------------------------------------------------------------

	/**
	 * Create a new real time game controller for a game of tetris.
	 * 
	 * @param gamefield
	 *            the game field model
	 */
	public RealTimeController(GameField gamefield) {
		// --
		if (System.getProperty("jku.se.tetris.speed") != null) {
			START_SPEED = Long.parseLong(System.getProperty("jku.se.tetris.speed"));
		}
		// --
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
						Thread.sleep(speed);
						// --
						gamefield.moveStoneDown();
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
		speed = Math.max(30, (START_SPEED - (long) ((Math.log(newLevel) / Math.log(2) + 1) * 100)));
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
