package jku.se.tetris.model;

import java.util.EventListener;

public interface GameDataChangedListener extends EventListener {
	public void scoreChanged(long newScore);
	public void levelChanged(int newLevel);
	public void gameOver();
}
