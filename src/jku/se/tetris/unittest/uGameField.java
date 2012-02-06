package jku.se.tetris.unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import jku.se.tetris.model.Block;
import jku.se.tetris.model.GameField;
import jku.se.tetris.model.GameFieldImpl;
import jku.se.tetris.model.exception.InvalidActionException;
import jku.se.tetris.model.stones.ShapeO;

import org.junit.Before;
import org.junit.Test;

public class uGameField {

	private static GameField gamefield;
	private static int GAMEFIELD_WIDTH = 8;
	private static int GAMEFIELD_HEIGHT = 20;

	@Before
	public void setUp() throws Exception {
		// Create a new game field before every test to ensure equal terms
		gamefield = new GameFieldImpl(GAMEFIELD_WIDTH, GAMEFIELD_HEIGHT);
	}

	@Test
	public void testGetWidth() {
		assertEquals(GAMEFIELD_WIDTH, gamefield.getWidth());
	}

	@Test
	public void testGetHeight() {
		assertEquals(GAMEFIELD_HEIGHT, gamefield.getHeight());
	}

	@Test
	public void testNewGame() {
		assertNull(gamefield.getCurrentStone());
		gamefield.newGame();
		assertNotNull(gamefield.getCurrentStone());
	}

	@Test
	public void testGetScore() {
		assertEquals(0, gamefield.getScore());
	}

	@Test
	public void testGetLevel() {
		assertEquals(1, gamefield.getLevel());
	}

	@Test
	public void testGetGameDuration() {
		assertEquals(0, gamefield.getGameDuration());
	}

	@Test
	public void testGetBlocks() {
		Block[][] blocks = gamefield.getBlocks();
		// --
		assertEquals(GAMEFIELD_HEIGHT, blocks.length);
		if (GAMEFIELD_HEIGHT > 0) {
			assertEquals(GAMEFIELD_WIDTH, blocks[0].length);
		}
	}

	@Test
	public void testGetCurrentStone() {
		gamefield.configure(true, false, false);
		// --
		assertNull(gamefield.getCurrentStone());
		// --
		gamefield.newGame();
		assertEquals(ShapeO.class, gamefield.getCurrentStone().getClass());
	}

	@Test
	public void testGetNextStone() {
		gamefield.configure(true, false, false);
		// --
		assertNull(gamefield.getNextStone());
		// --
		gamefield.newGame();
		assertEquals(ShapeO.class, gamefield.getNextStone().getClass());
	}

	@Test(expected = InvalidActionException.class)
	public void testRotateStoneClockwiseInvalidState() throws InvalidActionException {
		gamefield.rotateStoneClockwise();
	}

	@Test(expected = InvalidActionException.class)
	public void testRotateStoneCounterClockwiseInvalidState() throws InvalidActionException {
		gamefield.rotateStoneCounterClockwise();
	}

	@Test
	public void testMoveStoneLeft() throws InvalidActionException {
		gamefield.configure(true, true, false);
		// --
		gamefield.newGame(); // a new game has to be started to move a stone
		assertEquals(3, gamefield.getCurrentStone().getX());
		gamefield.moveStoneLeft();
		assertEquals(2, gamefield.getCurrentStone().getX());
		// --
		gamefield.moveStoneLeft();
		gamefield.moveStoneLeft();
		assertEquals(0, gamefield.getCurrentStone().getX());
		// --
		gamefield.moveStoneLeft();
		assertEquals(0, gamefield.getCurrentStone().getX());
	}

	@Test(expected = InvalidActionException.class)
	public void testMoveStoneLeftInvalidState() throws InvalidActionException {
		gamefield.moveStoneLeft();
	}

	@Test
	public void testMoveStoneRight() throws InvalidActionException {
		gamefield.configure(true, true, false);
		// --
		gamefield.newGame(); // a new game has to be started to move a stone
		assertEquals(3, gamefield.getCurrentStone().getX());
		gamefield.moveStoneRight();
		assertEquals(4, gamefield.getCurrentStone().getX());
		// --
		gamefield.moveStoneRight();
		gamefield.moveStoneRight();
		assertEquals(6, gamefield.getCurrentStone().getX());
		// --
		gamefield.moveStoneRight();
		assertEquals(6, gamefield.getCurrentStone().getX());
	}

	@Test(expected = InvalidActionException.class)
	public void testMoveStoneRightInvalidState() throws InvalidActionException {
		gamefield.moveStoneRight();
	}

	@Test
	public void testMoveStoneDown() throws InvalidActionException {
		gamefield.configure(true, false, false);
		// --
		gamefield.newGame(); // a new game has to be started to move a stone
		assertEquals(0, gamefield.getCurrentStone().getY());
		gamefield.moveStoneDown();
		assertEquals(1, gamefield.getCurrentStone().getY());
		// --
		for (int i = 1; i < GAMEFIELD_HEIGHT - 2; i++) {
			gamefield.moveStoneDown();
		}
		assertEquals(18, gamefield.getCurrentStone().getY());
		// After this move a next stone has to be at the top of the field
		gamefield.moveStoneDown();
		assertEquals(0, gamefield.getCurrentStone().getY());
	}

	@Test(expected = InvalidActionException.class)
	public void testMoveStoneDownInvalidState() throws InvalidActionException {
		gamefield.moveStoneDown();
	}

	@Test
	public void testMoveStoneToBottom() throws InvalidActionException {
		gamefield.configure(true, false, false);
		// --
		gamefield.newGame(); // a new game has to be started to move a stone
		assertEquals(0, gamefield.getCurrentStone().getY());
		gamefield.moveStoneToBottom();
		// After this move a next stone has to be at the top of the field
		assertEquals(0, gamefield.getCurrentStone().getY());
	}

	@Test(expected = InvalidActionException.class)
	public void testMoveStoneToBottomInvalidState() throws InvalidActionException {
		gamefield.moveStoneToBottom();
	}

	@Test
	public void testRowComplete() throws InvalidActionException {
		gamefield.configure(true, false, false);
		// --
		gamefield.newGame();
		gamefield.moveStoneToBottom();
		// --
		assertNotNull(gamefield.getBlocks()[GAMEFIELD_HEIGHT - 1][0]);
		assertNotNull(gamefield.getBlocks()[GAMEFIELD_HEIGHT - 1][1]);
		assertNotNull(gamefield.getBlocks()[GAMEFIELD_HEIGHT - 2][0]);
		assertNotNull(gamefield.getBlocks()[GAMEFIELD_HEIGHT - 2][1]);
		// --
		gamefield.moveStoneRight();
		gamefield.moveStoneRight();
		gamefield.moveStoneToBottom();
		gamefield.moveStoneRight();
		gamefield.moveStoneRight();
		gamefield.moveStoneRight();
		gamefield.moveStoneRight();
		gamefield.moveStoneToBottom();
		gamefield.moveStoneRight();
		gamefield.moveStoneRight();
		gamefield.moveStoneRight();
		gamefield.moveStoneRight();
		gamefield.moveStoneRight();
		gamefield.moveStoneRight();
		gamefield.moveStoneToBottom();
		// --
		assertNull(gamefield.getBlocks()[GAMEFIELD_HEIGHT - 1][0]);
		assertNull(gamefield.getBlocks()[GAMEFIELD_HEIGHT - 1][1]);
		assertNull(gamefield.getBlocks()[GAMEFIELD_HEIGHT - 2][0]);
		assertNull(gamefield.getBlocks()[GAMEFIELD_HEIGHT - 2][1]);
	}
}
