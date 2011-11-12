package jku.se.tetris.prototype;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import jku.se.tetris.control.Controller;
import jku.se.tetris.model.GameDataChangedListener;
import jku.se.tetris.model.GameField;
import jku.se.tetris.model.GameFieldImpl;
import jku.se.tetris.model.GraphicsProviderRegistry;
import jku.se.tetris.model.exception.InvalidActionException;
import jku.se.tetris.ui.swing.JGameField;
import jku.se.tetris.ui.swing.JNextStoneAnnouncer;
import jku.se.tetris.ui.swing.JScoreBoard;
import jku.se.tetris.ui.swing.JStopwatch;
import jku.se.tetris.ui.swing.SwingGraphicsAdaptor;

public class Tetris implements GameDataChangedListener {
	private static final int BLOCK_SIZE = 30;
	private static final int GAME_FIELD_WIDTH = 10;
	private static final int GAME_FIELD_HEIGHT = 20;

	// ---------------------------------------------------------------------------

	// private static final Color BACKGROUND_COLOR = Color.BLACK;
	private static final Color BACKGROUND_COLOR = new Color(32, 47, 70);
	private static final Color BORDER_COLOR = Color.WHITE;
	private static final Color TEXT_COLOR = Color.WHITE;

	// ---------------------------------------------------------------------------

	private static final String WINDOW_TITLE = "Tetris @ JKU";

	// ---------------------------------------------------------------------------

	private GameField gamefield;
	private JGameField view;
	private Controller controller;

	// ---------------------------------------------------------------------------

	private JFrame frame;

	// ---------------------------------------------------------------------------

	public Tetris() {
		gamefield = new GameFieldImpl(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
		view = new JGameField(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT, BLOCK_SIZE, BACKGROUND_COLOR, BORDER_COLOR);
		controller = new Controller(gamefield);
		// --
		gamefield.addFieldChangedListener(view);
		gamefield.addDataChangedListener(view);
		gamefield.addDataChangedListener(this);
		// --
		GraphicsProviderRegistry.setProvider(new SwingGraphicsAdaptor(BLOCK_SIZE));
		// --
		show();
	}

	// ---------------------------------------------------------------------------

	public void show() {
		frame = createFrame();
		frame.setLocation(100, 100);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}

	// ---------------------------------------------------------------------

	private JFrame createFrame() {
		// Frame with default close handler
		final JFrame frame = new JFrame(WINDOW_TITLE);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		//
		// Menu Bar
		//
		new MenuProvider(frame, gamefield, controller);

		//
		// Playing field
		//
		Container cp = frame.getContentPane();
		cp.setBackground(BACKGROUND_COLOR);
		cp.setLayout(new BorderLayout());
		cp.add(view, BorderLayout.WEST);
		view.setDoubleBuffered(true);

		Container hud = new Container();
		hud.setLayout(new BorderLayout(BLOCK_SIZE, BLOCK_SIZE));

		//
		// Next Stone Announcer
		//
		JNextStoneAnnouncer announcer = new JNextStoneAnnouncer(6, 6, BLOCK_SIZE, BACKGROUND_COLOR, BORDER_COLOR);
		gamefield.addFieldChangedListener(announcer);
		gamefield.addDataChangedListener(announcer);
		hud.add(announcer, BorderLayout.NORTH);

		//
		// Scoreboard
		//
		JScoreBoard scoreboard = new JScoreBoard((int) announcer.getPreferredSize().getWidth(), 90, BLOCK_SIZE / 2, BACKGROUND_COLOR, BORDER_COLOR, TEXT_COLOR);
		gamefield.addDataChangedListener(scoreboard);
		hud.add(scoreboard, BorderLayout.CENTER);

		//
		// Stopwatch
		//
		JStopwatch stopwatch = new JStopwatch((int) announcer.getPreferredSize().getWidth(), 60, BLOCK_SIZE / 2, BACKGROUND_COLOR, BORDER_COLOR, TEXT_COLOR);
		gamefield.addDataChangedListener(stopwatch);
		hud.add(stopwatch, BorderLayout.SOUTH);

		// --
		cp.add(hud, BorderLayout.EAST);

		// --
		return frame;
	}

	// ---------------------------------------------------------------------------

	private KeyListener keyListener = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.isActionKey()) {
				try {
					//@formatter:off
			switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:	gamefield.moveStoneLeft();			break;
				case KeyEvent.VK_RIGHT: gamefield.moveStoneRight();			break;
				case KeyEvent.VK_UP: 	gamefield.rotateStoneClockwise();	break;
				case KeyEvent.VK_DOWN: 	gamefield.moveStoneToBottom();		break;
			}
			//@formatter:on
				} catch (InvalidActionException exc) {
					exc.printStackTrace(); // TODO improve handling
				}
			}
		}
	};

	// ---------------------------------------------------------------------------

	@Override
	public void gameStarted() {
		frame.removeKeyListener(keyListener);
		frame.addKeyListener(keyListener);
	}

	@Override
	public void gameOver(long score, int level, long duration) {
		frame.removeKeyListener(keyListener);
	}

	@Override
	public void scoreChanged(long newScore) {
		// nothing to do
	}

	@Override
	public void levelChanged(int newLevel) {
		// nothing to do
	}

	// ---------------------------------------------------------------------------

	public static void main(String[] args) {
		new Tetris();
	}
}
