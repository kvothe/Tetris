package jku.se.tetris.model;

import java.util.EventListener;

import jku.se.tetris.model.stones.Stone;

/**
 * @author Markus Hofmarcher
 * 
 *         Listener interface for game field changed events.
 */
public interface GameFieldChangedListener extends EventListener {
	/**
	 * This event is called when a new stone is added to the game field.
	 * 
	 * @param newStone
	 *            the new stone
	 */
	public void newStone(Stone newStone);
	/**
	 * This event is called when the next stone is announced.
	 * 
	 * @param newStone
	 *            the next stone to be added after the current one is placed
	 */
	public void announceNextStone(Stone nextStone);

	/**
	 * This event is called when the current stone moved.
	 * 
	 * @param stone
	 *            the current stone
	 * @param xOld
	 *            the old x-coordinates of the stone
	 * @param yOld
	 *            the old y-coordinates of the stone
	 */
	public void stoneMoved(Stone stone, int xOld, int yOld);
	/**
	 * This event is called when the current blocks have been changed (except
	 * the blocks of the current stone).
	 * 
	 * @param blocks
	 *            the array of blocks representing the current state of the
	 *            game.
	 */
	public void blocksChanged(Block[][] blocks);
}
