package jku.se.tetris.model.stones;

/**
 * @author Markus Hofmarcher
 * 
 *         This factory can create Tetris stones.
 */
public class StoneFactory {
	private static boolean squareBlocksOnly = false;

	// ---------------------------------------------------------------------------

	/**
	 * Set this factory to only produce square stones. If set to false random
	 * stones are produced.
	 */
	public static void configure(boolean squareBlocksOnly) {
		StoneFactory.squareBlocksOnly = squareBlocksOnly;
	}

	// ---------------------------------------------------------------------------

	/**
	 * @return A new Tetris stone.
	 */
	public static Stone getStone() {
		if (squareBlocksOnly) {
			return new ShapeO();
		} else {
			int rnd = (int) (Math.random() * 10) % 7;
			// --
			//@formatter:off
			switch (rnd) {
				case 0:	return new ShapeI();
				case 1:	return new ShapeO();
				case 2:	return new ShapeT();
				case 3:	return new ShapeJ();
				case 4:	return new ShapeL();
				case 5:	return new ShapeS();
				case 6:	return new ShapeZ();
				default: return new ShapeO();
			}
			//@formatter:on
		}
	}
}
