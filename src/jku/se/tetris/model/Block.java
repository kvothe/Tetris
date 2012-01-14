package jku.se.tetris.model;

import java.awt.Color;

/**
 * @author Markus Hofmarcher
 * 
 *         Basic block implementation.
 */
public class Block {
	private int x;
	private int y;

	// ---------------------------------------------------------------------------

	private Color color;

	// ---------------------------------------------------------------------------

	/**
	 * Create a new block.
	 * 
	 * @param x
	 *            the x-coordinate of this block relative to its parent.
	 * @param y
	 *            the y-coordinate of this block relative to its parent.
	 * @param color
	 *            the color of this block.
	 */
	public Block(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	// ---------------------------------------------------------------------------

	/**
	 * Set the color of this block.
	 * 
	 * @param color
	 *            the new color.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	// ---------------------------------------------------------------------------

	/**
	 * Set the x-coordinate of this block relative to its parent.
	 */
	public void setX(int x) {
		this.x = x;
	}

	// ---------------------------------------------------------------------------

	/**
	 * Set the y-coordinate of this block relative to its parent.
	 */
	public void setY(int y) {
		this.y = y;
	}

	// ---------------------------------------------------------------------------

	/**
	 * @return The current color of this block.
	 */
	public Color getColor() {
		return color;
	}

	// ---------------------------------------------------------------------------

	/**
	 * @return The x-coordinate of this block relative to its parent.
	 */
	public int getX() {
		return x;
	}

	// ---------------------------------------------------------------------------

	/**
	 * @return The y-coordinate of this block relative to its parent.
	 */
	public int getY() {
		return y;
	}
}
