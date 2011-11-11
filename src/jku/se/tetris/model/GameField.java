package jku.se.tetris.model;

import jku.se.tetris.model.exception.InvalidActionException;

public interface GameField {
	public void newGame();
	public void newStone() throws InvalidActionException;

	public long getScore();
	public int getLevel();

	public Stone getCurrentStone();
	public Stone getNextStone();

	public Block[][] getBlocks();

	public void rotateStoneClockwise() throws InvalidActionException;
	public void rotateStoneCounterClockwise() throws InvalidActionException;

	public void moveStoneLeft() throws InvalidActionException;
	public void moveStoneRight() throws InvalidActionException;
	public void moveStoneDown() throws InvalidActionException;
	public void moveStoneToBottom() throws InvalidActionException;

	public void addFieldChangedListener(GameFieldChangedListener listener);
	public void removeFieldChangedListener(GameFieldChangedListener listener);

	public void addDataChangedListener(GameDataChangedListener listener);
	public void removeDataChangedListener(GameDataChangedListener listener);
}
