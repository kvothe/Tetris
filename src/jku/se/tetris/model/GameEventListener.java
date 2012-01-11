package jku.se.tetris.model;

import java.util.EventListener;

public interface GameEventListener extends EventListener {
	public void stoneMovedLeft();
	public void stoneMovedRight();
	public void stoneRotated();
	
	public void stoneCollision();
	public void stoneAtBottom();
	
	public void rowComplete(int[] rows);
}
