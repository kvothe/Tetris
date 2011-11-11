package jku.se.tetris.control;

import jku.se.tetris.model.GameDataChangedListener;
import jku.se.tetris.model.GameField;
import jku.se.tetris.model.exception.InvalidActionException;

public class Controller implements GameDataChangedListener {
	private static final long START_SPEED = 900;

	private GameField gamefield;
	private long speed;

	// ---------------------------------------------------------------------------

	private long gameStart;
	private long gameDuration;

	// ---------------------------------------------------------------------------

	private Thread animator;
	private boolean signalAbort = false;

	// ---------------------------------------------------------------------------

	public Controller(GameField gamefield) {
		this.gamefield = gamefield;
		this.speed = START_SPEED;
		// --
		this.gameStart = -1;
		this.gameDuration = -1;
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
					// TODO Auto-generated catch block
					e.printStackTrace();
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

	public long getGameDuration() {
		return gameDuration;
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
		// --
		System.out.println("Speed: " + speed);
	}

	// ---------------------------------------------------------------------------

	@Override
	public void gameStarted() {
		// ignore
	}

	// ---------------------------------------------------------------------------

	@Override
	public void gameOver() {
		stop();
	}
}
