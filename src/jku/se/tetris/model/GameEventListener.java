package jku.se.tetris.model;

import java.util.EventListener;

/**
 * @author Markus Hofmarcher
 * 
 *         Convenience listener interface for game events.
 */
public interface GameEventListener extends EventListener {
	/**
	 * This event is called if the current stone is moved to the left.
	 */
	public void stoneMovedLeft();
	/**
	 * This event is called if the current stone is moved to the right.
	 */
	public void stoneMovedRight();
	/**
	 * This event is called if the current stone is rotated clockwise.
	 */
	public void stoneRotatedClockwise();
	/**
	 * This event is called if the current stone is rotated counter-clockwise.
	 */
	public void stoneRotatedCounterClockwise();

	/**
	 * This event is called if the current stone collided with another stone.
	 */
	public void stoneCollision();
	/**
	 * This event is called if the current stone is at the bottom of the game
	 * field.
	 */
	public void stoneAtBottom();

	/**
	 * This event is called when one or more rows are complete and therefore
	 * removed from the game field.
	 * 
	 * @param rows
	 *            an array containing the removed rows numbers
	 */
	public void rowComplete(int[] rows);
}
