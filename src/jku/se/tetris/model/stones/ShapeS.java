package jku.se.tetris.model.stones;

import jku.se.tetris.model.Block;

public class ShapeS extends Stone {

	// S-Shape
	private static final Block[] SHAPE_S = new Block[] { new Block(1, 0, GREEN), new Block(2, 0, GREEN), new Block(0, 1, GREEN), new Block(1, 1, GREEN) };
	private static final Block[] SHAPE_S_90 = new Block[] { new Block(0, 0, GREEN), new Block(0, 1, GREEN), new Block(1, 1, GREEN), new Block(1, 2, GREEN) };

	// ---------------------------------------------------------------------------
	
	@Override
	Block[] createShape() {
		return SHAPE_S.clone();
	}

	// ---------------------------------------------------------------------------
	
	@Override
	void rotate() {
		//@formatter:off
		if (orientation == 90 || orientation == 270) blocks = SHAPE_S_90.clone();
		else blocks = SHAPE_S.clone();
		//@formatter:on
	}
}
