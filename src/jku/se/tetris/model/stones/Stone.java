package jku.se.tetris.model.stones;

import java.awt.Color;

import jku.se.tetris.model.Block;

public abstract class Stone {

	public static final Color CYAN = new Color(0, 240, 240);
	public static final Color BLUE = new Color(0, 0, 240);
	public static final Color ORANGE = new Color(240, 160, 0);
	public static final Color YELLOW = new Color(240, 240, 0);
	public static final Color GREEN = new Color(0, 240, 0);
	public static final Color PURPLE = new Color(160, 0, 240);
	public static final Color RED = new Color(240, 0, 0);

	// ---------------------------------------------------------------------------

	protected Block[] blocks;
	protected int orientation = 0;

	private int x;
	private int y;

	// ---------------------------------------------------------------------------

	Stone() {
		this.blocks = createShape();
	}

	// ---------------------------------------------------------------------------

	abstract Block[] createShape();

	// ---------------------------------------------------------------------------
	private boolean isValid = true;

	public void setDirty() {
		isValid = false;
	}
	public boolean isValid() {
		return isValid;
	}

	// ---------------------------------------------------------------------------

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	// ---------------------------------------------------------------------------

	public int getWidth() {
		int width = 0;
		// --
		for (Block b : blocks) {
			width = Math.max(width, b.getX());
		}
		// --
		return width + 1;
	}

	public int getHeight() {
		int height = 0;
		// --
		for (Block b : blocks) {
			height = Math.max(height, b.getY());
		}
		// --
		return height + 1;
	}

	// ---------------------------------------------------------------------------

	public Block[] getBlocks() {
		return blocks;
	}

	// ---------------------------------------------------------------------------

	public synchronized void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// ---------------------------------------------------------------------------

	public synchronized void rotateClockwise() {
		orientation = (orientation + 90) % 360;
		// --
		rotate();
	}

	// ---------------------------------------------------------------------------

	public synchronized void rotateCounterClockwise() {
		orientation = (orientation - 90) % 360;
		// --
		if (orientation < 0) {
			orientation = 360 - Math.abs(orientation);
		}
		// --
		rotate();
	}

	// ---------------------------------------------------------------------------

	abstract void rotate();
}
