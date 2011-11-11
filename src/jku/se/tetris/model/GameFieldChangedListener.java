package jku.se.tetris.model;

import java.util.EventListener;

public interface GameFieldChangedListener extends EventListener {
	public void scoreChanged(long oldScore, long newScore);

	public void newStone(Stone newStone);
	public void announceNextStone(Stone nextStone);

	public void stoneMoved(int x, int y, int xOld, int yOld);
	public void blocksChanged(Block[][] blocks);
}
