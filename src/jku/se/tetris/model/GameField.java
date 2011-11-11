package jku.se.tetris.model;

public interface GameField {
	public void newGame();
	public void newStone();

	public long getScore();
	public int getLevel();

	public Stone getCurrentStone();
	public Stone getNextStone();

	public Block[][] getBlocks();

	public void rotateStoneClockwise();
	public void rotateStoneCounterClockwise();

	public void moveStoneLeft();
	public void moveStoneRight();
	public void moveStoneDown();
	public void moveStoneToBottom();

	public void addFieldChangedListener(GameFieldChangedListener listener);
	public void removeFieldChangedListener(GameFieldChangedListener listener);

	public void addDataChangedListener(GameDataChangedListener listener);
	public void removeDataChangedListener(GameDataChangedListener listener);	
}
