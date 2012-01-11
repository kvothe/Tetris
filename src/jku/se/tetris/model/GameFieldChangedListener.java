package jku.se.tetris.model;

import java.util.EventListener;

import jku.se.tetris.model.stones.Stone;

public interface GameFieldChangedListener extends EventListener {
	public void newStone(Stone newStone);
	public void announceNextStone(Stone nextStone);

	public void stoneMoved(Stone stone, int xOld, int yOld);
	public void blocksChanged(Block[][] blocks);
}
