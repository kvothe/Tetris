package jku.se.tetris.model.exception;

public class InvalidActionException extends Exception {
	private static final long serialVersionUID = -7859667456651302675L;

	public InvalidActionException(String message) {
		super(message);
	}
	public InvalidActionException(String message, Throwable cause) {
		super(message, cause);
	}
}
