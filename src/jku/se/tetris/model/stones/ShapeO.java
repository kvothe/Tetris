package jku.se.tetris.model.stones;

import jku.se.tetris.model.Block;

public class ShapeO extends Stone {

	// O-Shape
	private static final Block[] SHAPE_O = new Block[] { new Block(0, 0, YELLOW), new Block(0, 1, YELLOW), new Block(1, 0, YELLOW), new Block(1, 1, YELLOW) };

	// ---------------------------------------------------------------------------

	@Override
	Block[] createShape() {
		return SHAPE_O.clone();
	}

	// ---------------------------------------------------------------------------

	@Override
	void rotate() {
		// for this shape do nothing
	}
}
