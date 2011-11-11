package jku.se.tetris.model;

import java.awt.Color;
import java.awt.Graphics;

public interface GraphicsAdaptor {
	public void drawBlock(Graphics g, int x, int y, Color color);	
}
