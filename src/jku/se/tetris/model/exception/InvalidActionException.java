package jku.se.tetris.model.exception;

/**
 * @author Markus Hofmarcher
 * 
 *         Exception which should be thrown if an action is invalid.
 */
public class InvalidActionException extends Exception {
	private static final long serialVersionUID = -7859667456651302675L;

	public InvalidActionException(String message) {
		super(message);
	}
	public InvalidActionException(String message, Throwable cause) {
		super(message, cause);
	}
}
