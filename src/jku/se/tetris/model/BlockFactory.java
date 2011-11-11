package jku.se.tetris.model;

import java.awt.Color;

public class BlockFactory {
	public static Block createBlock(int x, int y, Color color) {
		return new Block(x, y, color);
	}
}
