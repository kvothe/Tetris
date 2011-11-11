package jku.se.tetris.model;

import java.awt.Color;

public class Block {
	private int x;
	private int y;

	// ---------------------------------------------------------------------------

	private Color color;

	// ---------------------------------------------------------------------------

	public Block(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	// ---------------------------------------------------------------------------

	public void setColor(Color color) {
		this.color = color;
	}

	// ---------------------------------------------------------------------------

	public void setX(int x) {
		this.x = x;
	}

	// ---------------------------------------------------------------------------

	public void setY(int y) {
		this.y = y;
	}

	// ---------------------------------------------------------------------------

	public Color getColor() {
		return color;
	}

	// ---------------------------------------------------------------------------

	public int getX() {
		return x;
	}

	// ---------------------------------------------------------------------------

	public int getY() {
		return y;
	}

	// ---------------------------------------------------------------------------

	// @Override
	// public void draw(int xOrigin, int yOrigin) {
	// if (GraphicsProviderRegistry.hasProvider()) {
	// GraphicsProviderRegistry.getProvider().drawBlock(xOrigin + getX(),
	// yOrigin + getY(), getColor());
	// }
	// }
}
