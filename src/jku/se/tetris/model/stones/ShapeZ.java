package jku.se.tetris.model.stones;

import jku.se.tetris.model.Block;

public class ShapeZ extends Stone {

	// Z-Shape
	private static final Block[] SHAPE_Z = new Block[] { new Block(0, 0, RED), new Block(1, 0, RED), new Block(1, 1, RED), new Block(2, 1, RED) };
	private static final Block[] SHAPE_Z_90 = new Block[] { new Block(1, 0, RED), new Block(0, 1, RED), new Block(1, 1, RED), new Block(0, 2, RED) };

	// ---------------------------------------------------------------------------
	
	@Override
	Block[] createShape() {
		return SHAPE_Z.clone();
	}

	// ---------------------------------------------------------------------------
	
	@Override
	void rotate() {
		//@formatter:off
		if (orientation == 90 || orientation == 270) blocks = SHAPE_Z_90.clone();
		else blocks = SHAPE_Z.clone();
		//@formatter:on
	}
}
