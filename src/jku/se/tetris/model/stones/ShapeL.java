package jku.se.tetris.model.stones;

import jku.se.tetris.model.Block;

public class ShapeL extends Stone {

	// L-Shape
	private static final Block[] SHAPE_L = new Block[] { new Block(0, 0, ORANGE), new Block(0, 1, ORANGE), new Block(0, 2, ORANGE), new Block(1, 2, ORANGE) };
	private static final Block[] SHAPE_L_90 = new Block[] { new Block(0, 0, ORANGE), new Block(1, 0, ORANGE), new Block(2, 0, ORANGE), new Block(0, 1, ORANGE) };
	private static final Block[] SHAPE_L_180 = new Block[] { new Block(0, 0, ORANGE), new Block(1, 0, ORANGE), new Block(1, 1, ORANGE), new Block(1, 2, ORANGE) };
	private static final Block[] SHAPE_L_270 = new Block[] { new Block(0, 1, ORANGE), new Block(1, 1, ORANGE), new Block(2, 1, ORANGE), new Block(2, 0, ORANGE) };

	// ---------------------------------------------------------------------------
	
	@Override
	Block[] createShape() {
		return SHAPE_L.clone();
	}

	// ---------------------------------------------------------------------------
	
	@Override
	void rotate() {
		//@formatter:off
		if (orientation == 0) blocks = SHAPE_L.clone();
		else if (orientation == 90) blocks = SHAPE_L_90.clone();
		else if (orientation == 180) blocks = SHAPE_L_180.clone(); 
		else if (orientation == 270) blocks = SHAPE_L_270.clone();
		//@formatter:on
	}
}
