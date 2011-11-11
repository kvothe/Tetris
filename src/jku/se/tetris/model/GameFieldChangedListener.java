package jku.se.tetris.model;

import java.util.EventListener;

public interface GameFieldChangedListener extends EventListener {
	public void scoreChanged(long oldScore, long newScore);

	public void stoneAdded(Stone newStone);
	public void announceNextStone(Stone nextStone);

	public void stoneMoved(Stone old, Stone now);
	public void blocksChanged(GameField gameField);
}
