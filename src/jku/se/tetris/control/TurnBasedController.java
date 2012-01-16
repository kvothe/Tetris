package jku.se.tetris.control;

import jku.se.tetris.model.GameField;
import jku.se.tetris.model.exception.InvalidActionException;

/**
 * @author Markus Hofmarcher
 * 
 *         A very simple game controller for a game of Tetris. This controller
 *         executes one move after the other. A basic list of moves is hardcoded
 *         and after those moves were executed moves are determined randomly.
 */
public class TurnBasedController implements GameController {
	private static final int MOVE_NOTHING = 0;
	private static final int MOVE_LEFT = 1;
	private static final int MOVE_RIGHT = 2;
	private static final int MOVE_DOWN = 3;
	private static final int MOVE_ROTATE = 4;

	// ---------------------------------------------------------------------------

	//@formatter:off
	private int[] MOVE_LIST = new int[] {
			MOVE_NOTHING, MOVE_RIGHT, MOVE_LEFT, MOVE_ROTATE, MOVE_DOWN, // every possible move
			MOVE_RIGHT, MOVE_RIGHT, MOVE_DOWN,
			MOVE_RIGHT, MOVE_RIGHT, MOVE_RIGHT, MOVE_RIGHT, MOVE_DOWN,
			MOVE_RIGHT, MOVE_RIGHT, MOVE_RIGHT, MOVE_RIGHT, MOVE_RIGHT, MOVE_RIGHT, MOVE_DOWN, // FULL ROW
			MOVE_DOWN, MOVE_DOWN, MOVE_DOWN, MOVE_DOWN,	MOVE_DOWN, MOVE_DOWN, MOVE_DOWN, MOVE_DOWN, MOVE_DOWN, MOVE_DOWN // end game
	};
	//@formatter:on

	// ---------------------------------------------------------------------------

	private GameField gamefield;

	// ---------------------------------------------------------------------------

	private boolean abort = false;

	// ---------------------------------------------------------------------------

	private int currentMove = 0;
	private int MOVE_INTERVAL = 5;

	// ---------------------------------------------------------------------------

	/**
	 * Create a new turn based game controller for a game of tetris.
	 * 
	 * @param gamefield
	 *            the game field model
	 */
	public TurnBasedController(GameField gamefield) {
		this.gamefield = gamefield;
		this.gamefield.addDataChangedListener(this);
	}

	// ---------------------------------------------------------------------------

	public void start() {
		currentMove = 0;
		abort = false;
		// --
		gamefield.newGame();
		// --
		while (!abort) {
			int move = getNextMove();
			// --
			try {
				//@formatter:off
				switch(move){
					case MOVE_NOTHING: 	/* do nothing */					break;
					case MOVE_LEFT: 	gamefield.moveStoneLeft(); 			break;
					case MOVE_RIGHT: 	gamefield.moveStoneRight();			break;
					case MOVE_DOWN: 	gamefield.moveStoneToBottom(); 		break;
					case MOVE_ROTATE: 	gamefield.rotateStoneClockwise();	break;
					default:		 	gamefield.moveStoneRight();			break;
				}
				//@formatter:on
				// --
				Thread.sleep(MOVE_INTERVAL);
			} catch (InterruptedException e) {
				stop();
			} catch (InvalidActionException e) {
				stop();
			}
		}
	}

	// ---------------------------------------------------------------------------

	public void pause() {
		// not implemented
	}

	// ---------------------------------------------------------------------------

	public void resume() {
		// not implemented
	}

	// ---------------------------------------------------------------------------

	public void stop() {
		abort = true;
	}

	// ---------------------------------------------------------------------------

	private int getNextMove() {
		if (currentMove < MOVE_LIST.length) {
			return MOVE_LIST[currentMove++];
		} else {
			return (int) (Math.random() * 10) % 5;
		}
	}

	// ---------------------------------------------------------------------------

	@Override
	public void gameOver(long score, int level, long duration) {
		stop();
	}

	// ---------------------------------------------------------------------------

	@Override
	public void gameStarted() {
	}

	@Override
	public void scoreChanged(long newScore) {
	}

	@Override
	public void levelChanged(int newLevel) {
	}
}
