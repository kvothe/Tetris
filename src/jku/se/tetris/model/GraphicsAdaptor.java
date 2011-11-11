package jku.se.tetris.model;

import java.awt.Color;

public interface GraphicsAdaptor {
	public void drawBlock(int x, int y, Color color);
	public void drawStone(int x, int y, Color color);
}
