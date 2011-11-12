package jku.se.tetris.model;

import java.awt.Color;

public class Stone {

	private static final Color CYAN = new Color(0, 240, 240);
	private static final Color BLUE = new Color(0, 0, 240);
	private static final Color ORANGE = new Color(240, 160, 0);
	private static final Color YELLOW = new Color(240, 240, 0);
	private static final Color GREEN = new Color(0, 240, 0);
	private static final Color PURPLE = new Color(160, 0, 240);
	private static final Color RED = new Color(240, 0, 0);

	// L-Shape
	private static final Block[] SHAPE_L = new Block[] { new Block(0, 0, ORANGE), new Block(0, 1, ORANGE), new Block(0, 2, ORANGE), new Block(1, 2, ORANGE) };
	private static final Block[] SHAPE_L_90 = new Block[] { new Block(0, 0, ORANGE), new Block(1, 0, ORANGE), new Block(2, 0, ORANGE), new Block(0, 1, ORANGE) };
	private static final Block[] SHAPE_L_180 = new Block[] { new Block(0, 0, ORANGE), new Block(1, 0, ORANGE), new Block(1, 1, ORANGE), new Block(1, 2, ORANGE) };
	private static final Block[] SHAPE_L_270 = new Block[] { new Block(0, 1, ORANGE), new Block(1, 1, ORANGE), new Block(2, 1, ORANGE), new Block(2, 0, ORANGE) };
	// J-Shape
	private static final Block[] SHAPE_J = new Block[] { new Block(1, 0, BLUE), new Block(1, 1, BLUE), new Block(1, 2, BLUE), new Block(0, 2, BLUE) };
	private static final Block[] SHAPE_J_90 = new Block[] { new Block(0, 0, BLUE), new Block(0, 1, BLUE), new Block(1, 1, BLUE), new Block(2, 1, BLUE) };
	private static final Block[] SHAPE_J_180 = new Block[] { new Block(0, 0, BLUE), new Block(1, 0, BLUE), new Block(0, 1, BLUE), new Block(0, 2, BLUE) };
	private static final Block[] SHAPE_J_270 = new Block[] { new Block(0, 0, BLUE), new Block(1, 0, BLUE), new Block(2, 0, BLUE), new Block(2, 1, BLUE) };
	// O-Shape
	private static final Block[] SHAPE_O = new Block[] { new Block(0, 0, YELLOW), new Block(0, 1, YELLOW), new Block(1, 0, YELLOW), new Block(1, 1, YELLOW) };
	// S-Shape
	private static final Block[] SHAPE_S = new Block[] { new Block(1, 0, GREEN), new Block(2, 0, GREEN), new Block(0, 1, GREEN), new Block(1, 1, GREEN) };
	private static final Block[] SHAPE_S_90 = new Block[] { new Block(0, 0, GREEN), new Block(0, 1, GREEN), new Block(1, 1, GREEN), new Block(1, 2, GREEN) };
	// Z-Shape
	private static final Block[] SHAPE_Z = new Block[] { new Block(0, 0, RED), new Block(1, 0, RED), new Block(1, 1, RED), new Block(2, 1, RED) };
	private static final Block[] SHAPE_Z_90 = new Block[] { new Block(1, 0, RED), new Block(0, 1, RED), new Block(1, 1, RED), new Block(0, 2, RED) };
	// I-Shape
	private static final Block[] SHAPE_I = new Block[] { new Block(0, 0, CYAN), new Block(1, 0, CYAN), new Block(2, 0, CYAN), new Block(3, 0, CYAN) };
	private static final Block[] SHAPE_I_90 = new Block[] { new Block(1, 0, CYAN), new Block(1, 1, CYAN), new Block(1, 2, CYAN), new Block(1, 3, CYAN) };
	// T-Shape
	private static final Block[] SHAPE_T = new Block[] { new Block(1, 0, PURPLE), new Block(0, 1, PURPLE), new Block(1, 1, PURPLE), new Block(2, 1, PURPLE) };
	private static final Block[] SHAPE_T_90 = new Block[] { new Block(1, 0, PURPLE), new Block(1, 1, PURPLE), new Block(1, 2, PURPLE), new Block(2, 1, PURPLE) };
	private static final Block[] SHAPE_T_180 = new Block[] { new Block(0, 1, PURPLE), new Block(1, 1, PURPLE), new Block(2, 1, PURPLE), new Block(1, 2, PURPLE) };
	private static final Block[] SHAPE_T_270 = new Block[] { new Block(1, 0, PURPLE), new Block(0, 1, PURPLE), new Block(1, 1, PURPLE), new Block(1, 2, PURPLE) };

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
			case I_SHAPE: return SHAPE_I.clone();
			case O_SHAPE: return SHAPE_O.clone();
			case T_SHAPE: return SHAPE_T.clone();
			case S_SHAPE: return SHAPE_S.clone();
			case Z_SHAPE: return SHAPE_Z.clone();
			case L_SHAPE: return SHAPE_L.clone();
			case J_SHAPE: return SHAPE_J.clone();
			default: 	  return SHAPE_I.clone();
		}
		//@formatter:on
	}

	// ---------------------------------------------------------------------------
	private boolean isValid = true;

	public void setDirty() {
		isValid = false;
	}
	public boolean isValid() {
		return isValid;
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
		return width + 1;
	}

	public int getHeight() {
		int height = 0;
		// --
		for (Block b : blocks) {
			height = Math.max(height, b.getY());
		}
		// --
		return height + 1;
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
			case I_SHAPE:	if (orientation == 90 || orientation == 270) blocks = SHAPE_I_90.clone(); 
						  	else blocks = SHAPE_I.clone(); 
							break;			
			case T_SHAPE: 	if (orientation == 0) blocks = SHAPE_T.clone();
							else if (orientation == 90) blocks = SHAPE_T_90.clone();
							else if (orientation == 180) blocks = SHAPE_T_180.clone(); 
							else if (orientation == 270) blocks = SHAPE_T_270.clone(); 
			  			  	break;
			case S_SHAPE:	if (orientation == 90 || orientation == 270) blocks = SHAPE_S_90.clone();
							else blocks = SHAPE_S.clone();							 
							break;
			case Z_SHAPE: 	if (orientation == 90 || orientation == 270) blocks = SHAPE_Z_90.clone();
							else blocks = SHAPE_Z.clone();							 
							break;
			case L_SHAPE: 	if (orientation == 0) blocks = SHAPE_L.clone();
							else if (orientation == 90) blocks = SHAPE_L_90.clone();
							else if (orientation == 180) blocks = SHAPE_L_180.clone(); 
							else if (orientation == 270) blocks = SHAPE_L_270.clone(); 
							break;
			case J_SHAPE: 	if (orientation == 0) blocks = SHAPE_J.clone();
							else if (orientation == 90) blocks = SHAPE_J_90.clone();
							else if (orientation == 180) blocks = SHAPE_J_180.clone(); 
							else if (orientation == 270) blocks = SHAPE_J_270.clone(); 
							break;	
		}
		//@formatter:on
	}
}
