package jku.se.tetris.model.exception;

public class InvalidShapeException extends Exception {
	private static final long serialVersionUID = 3595590096303017323L;

	public InvalidShapeException(String message) {
		super(message);
	}
	public InvalidShapeException(String message, Throwable cause) {
		super(message, cause);
	}
}
