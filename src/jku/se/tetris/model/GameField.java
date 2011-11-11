package jku.se.tetris.model;

public interface GameField {
	public long getScore();

	public Stone getCurrentStone();
	public Stone getNextStone();

	public Block[][] getBlocks();

	public void rotateStoneClockwise();
	public void rotateStoneCounterClockwise();

	public void moveStoneLeft();
	public void moveStoneRight();
	public void moveStoneToBottom();

	public void addFieldChangedListener(GameFieldChangedListener listener);
	public void removeFieldChangedListener(GameFieldChangedListener listener);
}
