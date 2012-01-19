package jku.se.tetris.unittest;

import static org.junit.Assert.assertEquals;
import jku.se.tetris.model.stones.ShapeO;
import jku.se.tetris.model.stones.StoneFactory;

import org.junit.Test;

public class uStoneFactory {

	@Test
	public void testConfigure() {
		StoneFactory.configure(true);
		assertEquals(ShapeO.class, StoneFactory.getStone().getClass());
		//
		// the other case can not be tested reliably as there is a random
		// generator involved
		//
	}
}
