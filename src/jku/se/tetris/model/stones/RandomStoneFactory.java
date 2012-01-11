package jku.se.tetris.model.stones;

public class RandomStoneFactory {
	public static Stone getStone() {
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
