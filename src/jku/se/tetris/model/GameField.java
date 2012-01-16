package jku.se.tetris.model;

import jku.se.tetris.model.exception.InvalidActionException;
import jku.se.tetris.model.stones.Stone;

/**
 * @author Markus Hofmarcher
 * 
 *         This interface defines the basic functions of a Tetris game field
 *         implementation.
 */
public interface GameField {
	/**
	 * Configure the game field (had to be added for convenience reasons for
	 * homework assignment #5)
	 * 
	 * @param newStoneInCenter
	 *            true if a new stone should be created in the center of the top
	 *            row, false if a new stone should be created in the top left
	 *            corner of the field.
	 * @param audioOn
	 *            true if audio should be enabled, false otherwise.
	 */
	public void configure(boolean squareBlockOnly, boolean newStoneInCenter, boolean audioOn);

	/**
	 * @return The width of the game field.
	 */
	public int getWidth();
	/**
	 * @return The height of the game field.
	 */
	public int getHeight();

	/**
	 * @return The current score.
	 */
	public long getScore();
	/**
	 * @return The current level.
	 */
	public int getLevel();
	/**
	 * @return The current duration of the game in milliseconds.
	 */
	public long getGameDuration();

	/**
	 * @return The current stone.
	 */
	public Stone getCurrentStone();
	/**
	 * @return The next stone.
	 */
	public Stone getNextStone();

	/**
	 * @return An array of blocks on the field with exception of the blocks from
	 *         the current stone.
	 */
	public Block[][] getBlocks();

	/**
	 * Start a new game of tetris.
	 */
	public void newGame();

	/**
	 * Rotate the current stone clockwise.
	 * 
	 * @throws InvalidActionException
	 *             if the game is in a state where actions are not allowed
	 */
	public void rotateStoneClockwise() throws InvalidActionException;
	/**
	 * Rotate the current stone counter-clockwise.
	 * 
	 * @throws InvalidActionException
	 *             if the game is in a state where actions are not allowed
	 */
	public void rotateStoneCounterClockwise() throws InvalidActionException;

	/**
	 * Move the current stone to the left.
	 * 
	 * @throws InvalidActionException
	 *             if the game is in a state where actions are not allowed
	 */
	public void moveStoneLeft() throws InvalidActionException;
	/**
	 * Move the current stone to the right.
	 * 
	 * @throws InvalidActionException
	 *             if the game is in a state where actions are not allowed
	 */
	public void moveStoneRight() throws InvalidActionException;
	/**
	 * Move the current stone down one block.
	 * 
	 * @throws InvalidActionException
	 *             if the game is in a state where actions are not allowed
	 */
	public void moveStoneDown() throws InvalidActionException;
	/**
	 * Move the current stone to the bottom of the field or to the bottom most
	 * position possible if the bottom is blocked with blocks.
	 * 
	 * @throws InvalidActionException
	 *             if the game is in a state where actions are not allowed
	 */
	public void moveStoneToBottom() throws InvalidActionException;

	/**
	 * Add a new GameFieldChangedListener.
	 * 
	 * @param listener
	 *            a GameFieldChangedListener
	 */
	public void addFieldChangedListener(GameFieldChangedListener listener);
	/**
	 * Add a new GameFieldChangedListener.
	 * 
	 * @param listener
	 *            the GameFieldChangedListener to be removed
	 */
	public void removeFieldChangedListener(GameFieldChangedListener listener);

	/**
	 * Add a new GameDataChangedListener.
	 * 
	 * @param listener
	 *            a GameDataChangedListener
	 */
	public void addDataChangedListener(GameDataChangedListener listener);
	/**
	 * Remove the specified GameDataChangedListener.
	 * 
	 * @param listener
	 *            the GameDataChangedListener to be removed
	 */
	public void removeDataChangedListener(GameDataChangedListener listener);

	/**
	 * Add a new GameEventListener.
	 * 
	 * @param listener
	 *            a GameEventListener
	 */
	public void addGameEventListener(GameEventListener listener);
	/**
	 * Add a new GameEventListener.
	 * 
	 * @param listener
	 *            the GameEventListener to be removed
	 */
	public void removeGameEventListener(GameEventListener listener);
}
