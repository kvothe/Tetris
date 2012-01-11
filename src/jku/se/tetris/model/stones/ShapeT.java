package jku.se.tetris.model.stones;

import jku.se.tetris.model.Block;

public class ShapeT extends Stone {

	// T-Shape
	private static final Block[] SHAPE_T = new Block[] { new Block(1, 0, PURPLE), new Block(0, 1, PURPLE), new Block(1, 1, PURPLE), new Block(2, 1, PURPLE) };
	private static final Block[] SHAPE_T_90 = new Block[] { new Block(1, 0, PURPLE), new Block(1, 1, PURPLE), new Block(1, 2, PURPLE), new Block(2, 1, PURPLE) };
	private static final Block[] SHAPE_T_180 = new Block[] { new Block(0, 1, PURPLE), new Block(1, 1, PURPLE), new Block(2, 1, PURPLE), new Block(1, 2, PURPLE) };
	private static final Block[] SHAPE_T_270 = new Block[] { new Block(1, 0, PURPLE), new Block(0, 1, PURPLE), new Block(1, 1, PURPLE), new Block(1, 2, PURPLE) };

	// ---------------------------------------------------------------------------
	
	@Override
	Block[] createShape() {
		return SHAPE_T.clone();
	}

	// ---------------------------------------------------------------------------
	
	@Override
	void rotate() {
		//@formatter:off
		if (orientation == 0) blocks = SHAPE_T.clone();
		else if (orientation == 90) blocks = SHAPE_T_90.clone();
		else if (orientation == 180) blocks = SHAPE_T_180.clone(); 
		else if (orientation == 270) blocks = SHAPE_T_270.clone();
		//@formatter:on
	}
}
