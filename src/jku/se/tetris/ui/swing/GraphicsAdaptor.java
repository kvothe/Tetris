package jku.se.tetris.ui.swing;

import java.awt.Color;
import java.awt.Graphics;

import jku.se.tetris.model.stones.Stone;

public interface GraphicsAdaptor {
	public void drawBlock(Graphics g, int x, int y, Color color);
	public void drawStone(Graphics g, Stone stone);
}
