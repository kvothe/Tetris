package jku.se.tetris.model;

import java.awt.Color;

public class Stone {

	// L-Shape
	private static final Block[] SHAPE_L = new Block[] { new Block(0, 0, Color.ORANGE), new Block(0, 1, Color.ORANGE), new Block(0, 2, Color.ORANGE), new Block(1, 2, Color.ORANGE) };
	private static final Block[] SHAPE_L_90 = new Block[] { new Block(0, 0, Color.ORANGE), new Block(1, 0, Color.ORANGE), new Block(2, 0, Color.ORANGE), new Block(0, 1, Color.ORANGE) };
	private static final Block[] SHAPE_L_180 = new Block[] { new Block(0, 0, Color.ORANGE), new Block(1, 0, Color.ORANGE), new Block(1, 1, Color.ORANGE), new Block(1, 2, Color.ORANGE) };
	private static final Block[] SHAPE_L_270 = new Block[] { new Block(0, 1, Color.ORANGE), new Block(1, 1, Color.ORANGE), new Block(2, 1, Color.ORANGE), new Block(2, 0, Color.ORANGE) };
	// J-Shape
	private static final Block[] SHAPE_J = new Block[] { new Block(1, 0, Color.BLUE), new Block(1, 1, Color.BLUE), new Block(1, 2, Color.BLUE), new Block(0, 2, Color.BLUE) };
	private static final Block[] SHAPE_J_90 = new Block[] { new Block(0, 0, Color.BLUE), new Block(0, 1, Color.BLUE), new Block(1, 1, Color.BLUE), new Block(2, 1, Color.BLUE) };
	private static final Block[] SHAPE_J_180 = new Block[] { new Block(0, 0, Color.BLUE), new Block(1, 0, Color.BLUE), new Block(0, 1, Color.BLUE), new Block(0, 2, Color.BLUE) };
	private static final Block[] SHAPE_J_270 = new Block[] { new Block(0, 0, Color.BLUE), new Block(1, 0, Color.BLUE), new Block(2, 0, Color.BLUE), new Block(2, 1, Color.BLUE) };
	// O-Shape
	private static final Block[] SHAPE_O = new Block[] { new Block(0, 0, Color.YELLOW), new Block(0, 1, Color.YELLOW), new Block(1, 0, Color.YELLOW), new Block(1, 1, Color.YELLOW) };
	// S-Shape
	private static final Block[] SHAPE_S = new Block[] { new Block(1, 0, Color.GREEN), new Block(2, 0, Color.GREEN), new Block(0, 1, Color.GREEN), new Block(1, 1, Color.GREEN) };
	private static final Block[] SHAPE_S_90 = new Block[] { new Block(0, 0, Color.GREEN), new Block(0, 1, Color.GREEN), new Block(1, 1, Color.GREEN), new Block(1, 2, Color.GREEN) };
	// Z-Shape
	private static final Block[] SHAPE_Z = new Block[] { new Block(0, 0, Color.RED), new Block(1, 0, Color.RED), new Block(1, 1, Color.RED), new Block(2, 1, Color.RED) };
	private static final Block[] SHAPE_Z_90 = new Block[] { new Block(1, 0, Color.RED), new Block(0, 1, Color.RED), new Block(1, 1, Color.RED), new Block(0, 2, Color.RED) };
	// I-Shape
	private static final Block[] SHAPE_I = new Block[] { new Block(0, 0, Color.CYAN), new Block(1, 0, Color.CYAN), new Block(2, 0, Color.CYAN), new Block(3, 0, Color.CYAN) };
	private static final Block[] SHAPE_I_90 = new Block[] { new Block(1, 0, Color.CYAN), new Block(1, 1, Color.CYAN), new Block(1, 2, Color.CYAN), new Block(1, 3, Color.CYAN) };
	// T-Shape
	private static final Block[] SHAPE_T = new Block[] { new Block(1, 0, Color.PINK), new Block(0, 1, Color.PINK), new Block(1, 1, Color.PINK), new Block(2, 1, Color.PINK) };
	private static final Block[] SHAPE_T_90 = new Block[] { new Block(1, 0, Color.PINK), new Block(1, 1, Color.PINK), new Block(1, 2, Color.PINK), new Block(2, 1, Color.PINK) };
	private static final Block[] SHAPE_T_180 = new Block[] { new Block(0, 1, Color.PINK), new Block(1, 1, Color.PINK), new Block(2, 1, Color.PINK), new Block(1, 2, Color.PINK) };
	private static final Block[] SHAPE_T_270 = new Block[] { new Block(1, 0, Color.PINK), new Block(0, 1, Color.PINK), new Block(1, 1, Color.PINK), new Block(1, 2, Color.PINK) };

	// ---------------------------------------------------------------------------

	private Block[] blocks;
	private EStoneShape shape;
	private int orientation = 0;

	private int x;
	private int y;

	// ---------------------------------------------------------------------------

	Stone() {
		int rnd = (int) (Math.random() * 10) % 7;
		// --
		//@formatter:off
		switch (rnd) {
			case 0:	shape = EStoneShape.I_SHAPE; break;
			case 1:	shape = EStoneShape.O_SHAPE; break;
			case 2:	shape = EStoneShape.T_SHAPE; break;
			case 3:	shape = EStoneShape.J_SHAPE; break;
			case 4:	shape = EStoneShape.L_SHAPE; break;
			case 5:	shape = EStoneShape.S_SHAPE; break;
			case 6:	shape = EStoneShape.Z_SHAPE; break;
			default: shape = EStoneShape.I_SHAPE;
		}
		//@formatter:on
		// --
		this.blocks = createShape(shape);
	}

	// ---------------------------------------------------------------------------

	private Block[] createShape(EStoneShape shape) {
		//@formatter:off
		switch (shape) {
			case I_SHAPE: return SHAPE_I;
			case O_SHAPE: return SHAPE_O;
			case T_SHAPE: return SHAPE_T;
			case S_SHAPE: return SHAPE_S;
			case Z_SHAPE: return SHAPE_Z;
			case L_SHAPE: return SHAPE_L;
			case J_SHAPE: return SHAPE_J;
			default: 	  return SHAPE_I;
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

	public Block[] getBlocks() {
		return blocks;
	}

	// ---------------------------------------------------------------------------

	public EStoneShape getShape() {
		return shape;
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

	private void rotate() {		
		//@formatter:off
		switch (shape) {
			case I_SHAPE:	if (orientation == 90 || orientation == 270) blocks = SHAPE_I_90; 
						  	else blocks = SHAPE_I; 
							break;			
			case T_SHAPE: 	if (orientation == 0) blocks = SHAPE_T;
							else if (orientation == 90) blocks = SHAPE_T_90;
							else if (orientation == 180) blocks = SHAPE_T_180; 
							else if (orientation == 270) blocks = SHAPE_T_270; 
			  			  	break;
			case S_SHAPE:	if (orientation == 90 || orientation == 270) blocks = SHAPE_S_90;
							else blocks = SHAPE_S;							 
							break;
			case Z_SHAPE: 	if (orientation == 90 || orientation == 270) blocks = SHAPE_Z_90;
							else blocks = SHAPE_Z;							 
							break;
			case L_SHAPE: 	if (orientation == 0) blocks = SHAPE_L;
							else if (orientation == 90) blocks = SHAPE_L_90;
							else if (orientation == 180) blocks = SHAPE_L_180; 
							else if (orientation == 270) blocks = SHAPE_L_270; 
							break;
			case J_SHAPE: 	if (orientation == 0) blocks = SHAPE_J;
							else if (orientation == 90) blocks = SHAPE_J_90;
							else if (orientation == 180) blocks = SHAPE_J_180; 
							else if (orientation == 270) blocks = SHAPE_J_270; 
							break;	
		}
		//@formatter:on
	}
}
