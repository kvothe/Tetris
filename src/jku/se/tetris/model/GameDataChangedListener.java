package jku.se.tetris.model;

import java.util.EventListener;

public interface GameDataChangedListener extends EventListener {
	public void scoreChanged(long newScore);
	public void levelChanged(int newLevel);
	public void gameStarted();
	public void gameOver(long score, int level, long duration);
}
