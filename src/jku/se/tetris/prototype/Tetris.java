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
import jku.se.tetris.model.GameField;
import jku.se.tetris.model.GameFieldImpl;
import jku.se.tetris.model.GraphicsProviderRegistry;
import jku.se.tetris.ui.swing.SwingGameField;
import jku.se.tetris.ui.swing.SwingGraphicsAdaptor;

public class Tetris {
	private static final int BLOCK_SIZE = 30;
	private static final int GAME_FIELD_WIDTH = 11;
	private static final int GAME_FIELD_HEIGHT = 20;

	// ---------------------------------------------------------------------------

	private static final String WINDOW_TITLE = "Tetris @ JKU";

	// ---------------------------------------------------------------------------

	private GameField gamefield;
	private SwingGameField view;
	private Controller controller;

	// ---------------------------------------------------------------------------

	private JFrame frame;

	// ---------------------------------------------------------------------------

	public Tetris() {
		gamefield = new GameFieldImpl(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
		view = new SwingGameField(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT, BLOCK_SIZE);
		controller = new Controller(gamefield);
		// --
		gamefield.addFieldChangedListener(view);
		gamefield.addDataChangedListener(view);
		// --		
		GraphicsProviderRegistry.setProvider(new SwingGraphicsAdaptor(BLOCK_SIZE));
		// --
		show();
		// --
		registerKeyBindings();
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
		JMenuItem itemStart = new JMenuItem("Start");
		menuFile.add(itemStart);
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
		itemStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.start();
			}
		});
		// --
		return frame;
	}

	// ---------------------------------------------------------------------------

	private void registerKeyBindings() {
		frame.addKeyListener(new KeyListener() {

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
					//@formatter:off
					switch (e.getKeyCode()) {
						case KeyEvent.VK_LEFT:	gamefield.moveStoneLeft();			break;
						case KeyEvent.VK_RIGHT: gamefield.moveStoneRight();			break;
						case KeyEvent.VK_UP: 	gamefield.rotateStoneClockwise();	break;
						case KeyEvent.VK_DOWN: 	gamefield.moveStoneToBottom();		break;
					}
					//@formatter:on
				}
			}
		});
	}

	// ---------------------------------------------------------------------------

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tetris tetris = new Tetris();
	}

}
