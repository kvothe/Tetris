package jku.se.tetris.model;

import java.util.EventListener;

/**
 * @author Markus Hofmarcher
 * 
 *         Listener interface for game data changed events.
 */
public interface GameDataChangedListener extends EventListener {
	/**
	 * This event is called when the game score changed.
	 * 
	 * @param newScore
	 *            the new score
	 */
	public void scoreChanged(long newScore);
	/**
	 * This event is called when the game level changed.
	 * 
	 * @param newScore
	 *            the new level
	 */
	public void levelChanged(int newLevel);
	/**
	 * This event is called when a new game is started.
	 */
	public void gameStarted();
	/**
	 * This event is called when the game ends.
	 * 
	 * @param score
	 *            the final score
	 * @param level
	 *            the final level
	 * @param duration
	 *            the duration of the game
	 */
	public void gameOver(long score, int level, long duration);
}
