package jku.se.tetris.prototype;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import jku.se.tetris.control.Controller;
import jku.se.tetris.model.GameDataChangedListener;
import jku.se.tetris.model.GameField;
import jku.se.tetris.model.GameFieldImpl;
import jku.se.tetris.model.GraphicsProviderRegistry;
import jku.se.tetris.model.exception.InvalidActionException;
import jku.se.tetris.ui.swing.JGameField;
import jku.se.tetris.ui.swing.SwingGraphicsAdaptor;

public class Tetris implements GameDataChangedListener {
	private static final int BLOCK_SIZE = 30;
	private static final int GAME_FIELD_WIDTH = 11;
	private static final int GAME_FIELD_HEIGHT = 20;

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
		view = new JGameField(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT, BLOCK_SIZE);
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
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		// Game
		JMenu menuGame = new JMenu("Game");
		menuBar.add(menuGame);
		// Game -> Reset
		JMenuItem itemStart = new JMenuItem("Start");
		menuGame.add(itemStart);
		// Game -> Reset
		JMenuItem itemPause = new JMenuItem("Pause");
		menuGame.add(itemPause);
		// Game -> Reset
		JMenuItem itemResume = new JMenuItem("Resume");
		menuGame.add(itemResume);
		// Game -> Exit
		JMenuItem itemExit = new JMenuItem("Exit");
		menuGame.add(itemExit);
		// Edit
		JMenu menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);

		//
		// Playing field
		//
		Container cp = frame.getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(view, BorderLayout.CENTER);

		// MenuItem: Game -> Start
		itemStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.start();
			}
		});
		// MenuItem: Game -> Pause
		itemPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.pause();
			}
		});
		// MenuItem: Game -> Pause
		itemResume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.resume();
			}
		});
		// MenuItem: Game -> Exit
		itemExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.stop();
				frame.dispose();
			}
		});
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
		frame.addKeyListener(keyListener);
	}

	@Override
	public void gameOver() {
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
