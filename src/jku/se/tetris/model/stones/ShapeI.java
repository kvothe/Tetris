package jku.se.tetris.model.stones;

import jku.se.tetris.model.Block;

public class ShapeI extends Stone {

	// I-Shape
	private static final Block[] SHAPE_I = new Block[] { new Block(0, 0, CYAN), new Block(1, 0, CYAN), new Block(2, 0, CYAN), new Block(3, 0, CYAN) };
	private static final Block[] SHAPE_I_90 = new Block[] { new Block(1, 0, CYAN), new Block(1, 1, CYAN), new Block(1, 2, CYAN), new Block(1, 3, CYAN) };

	// ---------------------------------------------------------------------------

	@Override
	Block[] createShape() {
		return SHAPE_I.clone();
	}

	// ---------------------------------------------------------------------------

	@Override
	void rotate() {
		//@formatter:off
		if (orientation == 90 || orientation == 270) blocks = SHAPE_I_90.clone(); 
	  	else blocks = SHAPE_I.clone(); 
		//@formatter:on
	}
}
