package jku.se.tetris.unittest;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import jku.se.tetris.model.GameField;
import jku.se.tetris.model.GameFieldImpl;
import jku.se.tetris.ui.text.TextInterface;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class uTextInterface {

	private static String LINE_SEPARATOR = System.getProperty("line.separator");

	// ---------------------------------------------------------------------------

	private static TextInterface textui;
	private static ByteArrayOutputStream stdout;
	private static PrintStream systemout;

	// ---------------------------------------------------------------------------

	private static String getStdOut() throws IOException {
		stdout.flush();
		return stdout.toString();
	}

	// ---------------------------------------------------------------------------

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Redirect System.out
		systemout = System.out;
		stdout = new ByteArrayOutputStream();
		System.setOut(new PrintStream(stdout));
		//
		// For testing the user interface a gamefield is required
		//
		GameField gamefield = new GameFieldImpl(8, 20);
		gamefield.configure(true, false, false);
		// --
		textui = new TextInterface(gamefield, null);
	}

	// ---------------------------------------------------------------------------

	@AfterClass
	public static void teardown() throws Exception {
		// Restore System.out
		System.setOut(systemout);
	}

	// ---------------------------------------------------------------------------

	@Before
	public void beforeTest() throws Exception {
		// Flush and reset output stream to ensure an empty buffer before every
		// test
		stdout.flush();
		stdout.reset();
	}

	// ---------------------------------------------------------------------------

	@Test
	public void testBeginn() {
		// This case could only be tested with a gamefield and a controller
	}

	@Test
	public void testStoneMovedLeft() throws IOException {
		textui.stoneMovedLeft();
		// --
		assertEquals("Stone moved to left" + LINE_SEPARATOR, getStdOut());
	}

	@Test
	public void testStoneMovedRight() throws IOException {
		textui.stoneMovedRight();
		// --
		assertEquals("Stone moved to right" + LINE_SEPARATOR, getStdOut());
	}

	@Test
	public void testStoneRotatedClockwise() throws IOException {
		textui.stoneRotatedClockwise();
		// --
		assertEquals("Stone rotated" + LINE_SEPARATOR, getStdOut());
	}

	@Test
	public void testStoneRotatedCounterClockwise() throws IOException {
		textui.stoneRotatedCounterClockwise();
		// --
		assertEquals("", getStdOut());
	}

	@Test
	public void testStoneCollision() throws IOException {
		textui.stoneCollision();
		// --
		assertEquals("Stone collided with another stone" + LINE_SEPARATOR, getStdOut());
	}

	@Test
	public void testStoneAtBottom() throws IOException {
		textui.stoneAtBottom();
		// --
		assertEquals("Stone at bottom" + LINE_SEPARATOR, getStdOut());
	}

	@Test
	public void testRowComplete() throws IOException {
		textui.rowComplete(new int[] { 1 });
		assertEquals("The row 1 has been completed" + LINE_SEPARATOR, getStdOut());
		// this test case fails due to an error in the implementation
		// --
		textui.rowComplete(new int[] { 1, 2 });
		assertEquals("The row 1 and 2 have been completed" + LINE_SEPARATOR, getStdOut());
		// --
		textui.rowComplete(new int[] { 1, 2, 3 });
		assertEquals("The row 1, 2 and 3 have been completed" + LINE_SEPARATOR, getStdOut());
	}
	@Test
	public void testScoreChanged() throws IOException {
		textui.scoreChanged(1);
		// --
		assertEquals("Score: 1" + LINE_SEPARATOR, getStdOut());
	}

	@Test
	public void testLevelChanged() throws IOException {
		textui.levelChanged(1);
		// --
		assertEquals("Level: 1" + LINE_SEPARATOR, getStdOut());
	}

	@Test
	public void testGameStarted() throws IOException {
		textui.gameStarted();
		// --
		assertEquals(LINE_SEPARATOR + "-----------------------------------------" + LINE_SEPARATOR + "Game Start!" + LINE_SEPARATOR + "-----------------------------------------" + LINE_SEPARATOR,
				getStdOut());
	}

	@Test
	public void testGameOver() throws IOException {
		textui.gameOver(1, 1, 1);
		// --
		assertEquals("-----------------------------------------" + LINE_SEPARATOR + "Game Over! Final Score: 1" + LINE_SEPARATOR + "-----------------------------------------" + LINE_SEPARATOR,
				getStdOut());
	}
}
