package jku.se.tetris.model;

import java.awt.Color;

import jku.se.tetris.model.exception.InvalidShapeException;
import jku.se.tetris.ui.swing.Drawable;

public class Stone implements Drawable {

	// L-Shape
	private static final Block[] SHAPE_L = new Block[] { new Block(0, 0, Color.RED), new Block(0, 1, Color.RED), new Block(0, 2, Color.RED), new Block(1, 2, Color.RED) };
	// J-Shape
	private static final Block[] SHAPE_J = new Block[] { new Block(1, 0, Color.RED), new Block(1, 1, Color.RED), new Block(1, 2, Color.RED), new Block(0, 2, Color.RED) };
	// O-Shape
	private static final Block[] SHAPE_O = new Block[] { new Block(0, 0, Color.RED), new Block(0, 1, Color.RED), new Block(1, 0, Color.RED), new Block(1, 1, Color.RED) };
	// S-Shape
	private static final Block[] SHAPE_S = new Block[] { new Block(1, 0, Color.RED), new Block(2, 0, Color.RED), new Block(0, 1, Color.RED), new Block(1, 1, Color.RED) };
	// Z-Shape
	private static final Block[] SHAPE_Z = new Block[] { new Block(0, 0, Color.RED), new Block(1, 0, Color.RED), new Block(1, 1, Color.RED), new Block(1, 2, Color.RED) };
	// I-Shape
	private static final Block[] SHAPE_I = new Block[] { new Block(0, 0, Color.RED), new Block(0, 1, Color.RED), new Block(0, 2, Color.RED), new Block(0, 3, Color.RED) };
	// T-Shape
	private static final Block[] SHAPE_T = new Block[] { new Block(1, 0, Color.RED), new Block(0, 1, Color.RED), new Block(1, 1, Color.RED), new Block(2, 1, Color.RED) };

	// ---------------------------------------------------------------------------

	private Block[] blocks;
	private EStoneShape shape;

	private int x;
	private int y;

	// ---------------------------------------------------------------------------

	private Stone(EStoneShape shape) {
		this.shape = shape;

		//@formatter:off
		switch (shape) {
			case I_SHAPE: blocks = SHAPE_I; break;
			case O_SHAPE: blocks = SHAPE_O; break;
			case T_SHAPE: blocks = SHAPE_T; break;
			case S_SHAPE: blocks = SHAPE_S; break;
			case Z_SHAPE: blocks = SHAPE_Z; break;
			case L_SHAPE: blocks = SHAPE_L; break;
			case J_SHAPE: blocks = SHAPE_J; break;			
		}
		//@formatter:on
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
		return width;
	}

	public int getHeight() {
		int height = 0;
		// --
		for (Block b : blocks) {
			height = Math.max(height, b.getY());
		}
		// --
		return height;
	}

	// ---------------------------------------------------------------------------

	public Block[] getShape() {
		return blocks;
	}

	// ---------------------------------------------------------------------------

	public void rotateClockwise() {
		// TODO
	}

	public void rotateCounterClockwise() {
		// TODO
	}

	// ---------------------------------------------------------------------------

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// ---------------------------------------------------------------------------

	@Override
	public void draw(int xOrigin, int yOrigin) {
		for (Block b : blocks) {
			b.draw(this.x + xOrigin, this.y + yOrigin);
		}
	}
}
