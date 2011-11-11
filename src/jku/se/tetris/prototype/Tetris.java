package jku.se.tetris.prototype;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import jku.se.tetris.model.GameField;
import jku.se.tetris.model.GameFieldImpl;
import jku.se.tetris.ui.swing.SwingGameField;

public class Tetris {
	private static final int BLOCK_SIZE = 30;
	private static final int GAME_FIELD_WIDTH = 10;
	private static final int GAME_FIELD_HEIGHT = 20;

	// ---------------------------------------------------------------------------

	private static final String WINDOW_TITLE = "Tetris @ JKU";

	// ---------------------------------------------------------------------------

	private GameField gamefield;
	private SwingGameField view;

	// ---------------------------------------------------------------------------

	private JFrame frame;

	// ---------------------------------------------------------------------------

	public Tetris() {
		gamefield = new GameFieldImpl(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
		view = new SwingGameField(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT, BLOCK_SIZE);
		// --
		gamefield.addFieldChangedListener(view);
		// --
		show();
	}

	// ---------------------------------------------------------------------------

	public void show() {
		frame = createFrame();
		frame.setLocation(100, 100);
		frame.pack();
		frame.setVisible(true);
	}

	// ---------------------------------------------------------------------

	private JFrame createFrame() {
		// Frame with default close handler
		JFrame frame = new JFrame(WINDOW_TITLE);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		//
		// Menu Bar
		//
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		// File
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		// File -> Reset
		JMenuItem itemReset = new JMenuItem("Reset");
		menuFile.add(itemReset);
		// File -> Exit
		JMenuItem itemExit = new JMenuItem("Exit");
		menuFile.add(itemExit);
		// Edit
		JMenu menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);

		//
		// Playing field
		//
		Container cp = frame.getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(view, BorderLayout.CENTER);

		// --
		return frame;
	}

	// ---------------------------------------------------------------------------

	private void startGame() {
		// TODO;
	}

	// ---------------------------------------------------------------------------

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tetris tetris = new Tetris();
		tetris.startGame();
	}

}
