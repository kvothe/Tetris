package jku.se.tetris.model;

import java.awt.Color;

public class Stone implements Drawable {

	// L-Shape
	private static final Block[] SHAPE_L = new Block[] { new Block(0, 0, Color.RED), new Block(0, 1, Color.RED), new Block(0, 2, Color.RED), new Block(1, 2, Color.RED) };
	private static final Block[] SHAPE_L_90 = new Block[] { new Block(0, 0, Color.RED), new Block(0, 1, Color.RED), new Block(0, 2, Color.RED), new Block(1, 2, Color.RED) };
	private static final Block[] SHAPE_L_180 = new Block[] { new Block(0, 0, Color.RED), new Block(0, 1, Color.RED), new Block(0, 2, Color.RED), new Block(1, 2, Color.RED) };
	private static final Block[] SHAPE_L_270 = new Block[] { new Block(0, 0, Color.RED), new Block(0, 1, Color.RED), new Block(0, 2, Color.RED), new Block(1, 2, Color.RED) };
	// J-Shape
	private static final Block[] SHAPE_J = new Block[] { new Block(1, 0, Color.RED), new Block(1, 1, Color.RED), new Block(1, 2, Color.RED), new Block(0, 2, Color.RED) };
	private static final Block[] SHAPE_J_90 = new Block[] { new Block(1, 0, Color.RED), new Block(1, 1, Color.RED), new Block(1, 2, Color.RED), new Block(0, 2, Color.RED) };
	private static final Block[] SHAPE_J_180 = new Block[] { new Block(1, 0, Color.RED), new Block(1, 1, Color.RED), new Block(1, 2, Color.RED), new Block(0, 2, Color.RED) };
	private static final Block[] SHAPE_J_270 = new Block[] { new Block(1, 0, Color.RED), new Block(1, 1, Color.RED), new Block(1, 2, Color.RED), new Block(0, 2, Color.RED) };
	// O-Shape
	private static final Block[] SHAPE_O = new Block[] { new Block(0, 0, Color.RED), new Block(0, 1, Color.RED), new Block(1, 0, Color.RED), new Block(1, 1, Color.RED) };
	// S-Shape
	private static final Block[] SHAPE_S = new Block[] { new Block(1, 0, Color.RED), new Block(2, 0, Color.RED), new Block(0, 1, Color.RED), new Block(1, 1, Color.RED) };
	private static final Block[] SHAPE_S_90 = new Block[] { new Block(1, 0, Color.RED), new Block(2, 0, Color.RED), new Block(0, 1, Color.RED), new Block(1, 1, Color.RED) };
	// Z-Shape
	private static final Block[] SHAPE_Z = new Block[] { new Block(0, 0, Color.RED), new Block(1, 0, Color.RED), new Block(1, 1, Color.RED), new Block(1, 2, Color.RED) };
	private static final Block[] SHAPE_Z_90 = new Block[] { new Block(0, 0, Color.RED), new Block(1, 0, Color.RED), new Block(1, 1, Color.RED), new Block(1, 2, Color.RED) };
	// I-Shape
	private static final Block[] SHAPE_I = new Block[] { new Block(0, 0, Color.RED), new Block(0, 1, Color.RED), new Block(0, 2, Color.RED), new Block(0, 3, Color.RED) };
	private static final Block[] SHAPE_I_90 = new Block[] { new Block(0, 0, Color.RED), new Block(0, 1, Color.RED), new Block(0, 2, Color.RED), new Block(0, 3, Color.RED) };
	// T-Shape
	private static final Block[] SHAPE_T = new Block[] { new Block(1, 0, Color.RED), new Block(0, 1, Color.RED), new Block(1, 1, Color.RED), new Block(2, 1, Color.RED) };
	private static final Block[] SHAPE_T_90 = new Block[] { new Block(1, 0, Color.RED), new Block(0, 1, Color.RED), new Block(1, 1, Color.RED), new Block(2, 1, Color.RED) };
	private static final Block[] SHAPE_T_180 = new Block[] { new Block(1, 0, Color.RED), new Block(0, 1, Color.RED), new Block(1, 1, Color.RED), new Block(2, 1, Color.RED) };
	private static final Block[] SHAPE_T_270 = new Block[] { new Block(1, 0, Color.RED), new Block(0, 1, Color.RED), new Block(1, 1, Color.RED), new Block(2, 1, Color.RED) };

	// ---------------------------------------------------------------------------

	private Block[] blocks;
	private EStoneShape shape;
	private int orientation = 0;

	private int x;
	private int y;

	// ---------------------------------------------------------------------------

	Stone() {
		EStoneShape shape;
		int rnd = (int) (Math.random()) % 7;
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

	private Stone(EStoneShape shape) {
		this.shape = shape;
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

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// ---------------------------------------------------------------------------

	public void rotateClockwise() {
		orientation = (orientation += 90) % 360;
		// --
		rotate();
	}

	// ---------------------------------------------------------------------------

	public void rotateCounterClockwise() {
		orientation = (orientation -= 90) % 360;
		// --
		rotate();
	}

	// ---------------------------------------------------------------------------

	private void rotate() {
		//@formatter:off
		switch (shape) {
			case I_SHAPE:	if (orientation == 90) blocks = SHAPE_I_90; 
						  	else blocks = SHAPE_I; 
							break;			
			case T_SHAPE: 	if (orientation == 0) blocks = SHAPE_T;
							if (orientation == 90) blocks = SHAPE_T_90;
							if (orientation == 180) blocks = SHAPE_T_180; 
							if (orientation == 270) blocks = SHAPE_T_270; 
			  			  	break;
			case S_SHAPE:	if (orientation == 90) blocks = SHAPE_S_90;
							else blocks = SHAPE_S;							 
							break;
			case Z_SHAPE: 	if (orientation == 90) blocks = SHAPE_Z_90;
							else blocks = SHAPE_Z;							 
							break;
			case L_SHAPE: 	if (orientation == 0) blocks = SHAPE_L;
							if (orientation == 90) blocks = SHAPE_L_90;
							if (orientation == 180) blocks = SHAPE_L_180; 
							if (orientation == 270) blocks = SHAPE_L_270; 
							break;
			case J_SHAPE: 	if (orientation == 0) blocks = SHAPE_J;
							if (orientation == 90) blocks = SHAPE_J_90;
							if (orientation == 180) blocks = SHAPE_J_180; 
							if (orientation == 270) blocks = SHAPE_J_270; 
							break;	
		}
		//@formatter:on
	}

	// ---------------------------------------------------------------------------

	@Override
	public void draw(int xOrigin, int yOrigin) {
		for (Block b : getBlocks()) {
			b.draw(getX(), getY());
		}
	}
}
