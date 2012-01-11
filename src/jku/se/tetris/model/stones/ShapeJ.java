package jku.se.tetris.model.stones;

import jku.se.tetris.model.Block;

public class ShapeJ extends Stone {

	// J-Shape
	private static final Block[] SHAPE_J = new Block[] { new Block(1, 0, BLUE), new Block(1, 1, BLUE), new Block(1, 2, BLUE), new Block(0, 2, BLUE) };
	private static final Block[] SHAPE_J_90 = new Block[] { new Block(0, 0, BLUE), new Block(0, 1, BLUE), new Block(1, 1, BLUE), new Block(2, 1, BLUE) };
	private static final Block[] SHAPE_J_180 = new Block[] { new Block(0, 0, BLUE), new Block(1, 0, BLUE), new Block(0, 1, BLUE), new Block(0, 2, BLUE) };
	private static final Block[] SHAPE_J_270 = new Block[] { new Block(0, 0, BLUE), new Block(1, 0, BLUE), new Block(2, 0, BLUE), new Block(2, 1, BLUE) };

	// ---------------------------------------------------------------------------
	
	@Override
	Block[] createShape() {
		return SHAPE_J.clone();
	}

	// ---------------------------------------------------------------------------
	
	@Override
	void rotate() {
		//@formatter:off
		if (orientation == 0) blocks = SHAPE_J.clone();
		else if (orientation == 90) blocks = SHAPE_J_90.clone();
		else if (orientation == 180) blocks = SHAPE_J_180.clone(); 
		else if (orientation == 270) blocks = SHAPE_J_270.clone();
		//@formatter:on
	}
}
