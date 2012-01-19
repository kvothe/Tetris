package jku.se.tetris.unittest;

import static org.junit.Assert.assertEquals;
import jku.se.tetris.control.GameController;
import jku.se.tetris.control.TurnBasedController;
import jku.se.tetris.model.GameField;
import jku.se.tetris.model.GameFieldImpl;

import org.junit.Before;
import org.junit.Test;

public class uTurnBasedController {

	private static GameField gamefield;
	private static GameController controller;

	@Before
	public void setUp() throws Exception {
		gamefield = new GameFieldImpl(8, 20);
		gamefield.configure(true, false, false);
		controller = new TurnBasedController(gamefield);
	}

	@Test
	public void testStart() {
		assertEquals(0, gamefield.getScore());
		controller.start();
		assertEquals(624, gamefield.getScore());
	}
}
