package jku.se.tetris.control;

import jku.se.tetris.model.GameDataChangedListener;

/**
 * @author Markus Hofmarcher
 * 
 * Interface for controller implementations
 *
 */
public interface GameController extends GameDataChangedListener {
	
	/**
	 * Start a new game.
	 */
	public void start();
	
	/**
	 * Pause the current game.
	 */
	public void pause();
	
	/**
	 * Resume the current game.
	 */
	public void resume();
	
	/**
	 * Stop the current game.
	 */
	public void stop();
}
