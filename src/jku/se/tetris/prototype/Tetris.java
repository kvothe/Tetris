package jku.se.tetris.prototype;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
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
	private StatisticsProvider statistics;

	// ---------------------------------------------------------------------------

	private JFrame frame;
	private JFrame menue;
	private JFrame regFrame;

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
		statistics = new StatisticsProvider(gamefield);
		//--
		menue = createMenueFrame();
		//--
		regFrame = createregFrame();
		// --
		frame = createFrame();
		// --
		statistics.setGui(frame);
		// --
		GraphicsProviderRegistry.setProvider(new SwingGraphicsAdaptor(BLOCK_SIZE));
		// --
		showmenue();
		//--
		
	}

	
	private void showmenue() {
		menue.setLocation(100, 100);
		menue.pack();
		menue.setSize(540, 700);
		menue.setResizable(false);
		menue.setVisible(true);
		regFrame.setVisible(false);
		
	}

	private JFrame createMenueFrame() {
		final JFrame frame = new JFrame(WINDOW_TITLE);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Container cp = frame.getContentPane();
		cp.setLayout(null);
		
		JButton onlineSpielen = new JButton(" Online Spielen ");
		
		onlineSpielen.setBounds(100, 50, 340, 150);
		cp.add(onlineSpielen);
		
		JButton spielen = new JButton(" Spielen ");
	
		spielen.setBounds(100, 250, 340, 150);
		cp.add(spielen);
		
		JButton exit = new JButton(" Statistik ");

		exit.setBounds(100, 450, 340, 150);
		cp.add(exit);
		
		onlineSpielen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showregFrame();
			}
		});
		
		spielen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				show();
				controller.start();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				statistics.showScoreListDialog();
			}
		});
		return frame;
	}

	
	
	private JFrame createregFrame() {
		final JFrame frame = new JFrame(WINDOW_TITLE);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Container cp = frame.getContentPane();
		cp.setLayout(null);
		
		JTextField benutzernameinput = new JTextField();
		JTextField passwortinput = new JTextField();
		
		JLabel anmelden = new JLabel("Anmelden: ");
		anmelden.setBounds(100, 95,200, 25);
		cp.add(anmelden);
		JLabel benutzername = new JLabel("Benutzername");
		JLabel passwort = new JLabel("Passwort");
		benutzername.setBounds(100, 125,200, 25);
		cp.add(benutzername);
		passwort.setBounds(100, 175, 200, 25);
		cp.add(passwort);
		benutzernameinput.setBounds(100, 150,200, 25);
		benutzernameinput.setToolTipText("Benutzername");
		cp.add(benutzernameinput);
		passwortinput.setBounds(100, 200, 200, 25);
		passwortinput.setToolTipText("Passwort");
		cp.add(passwortinput);
		
		JTextField neubenutzerinput = new JTextField();
		JTextField neupasswortinput = new JTextField();
		JTextField neupasswortcorrinput = new JTextField();
		
		JLabel reg = new JLabel("Registrieren: ");
		reg.setBounds(100, 345,200, 25);
		cp.add(reg);
		
		JLabel neubenutzername = new JLabel("Benutzername");
		JLabel neupasswort = new JLabel("Passwort");
		JLabel neupasswortcorr = new JLabel("Passwort");
		
		neubenutzerinput.setBounds(100, 400,200, 25);
		cp.add(neubenutzerinput);
		neubenutzerinput.setToolTipText("Benutzername");
		neupasswortinput.setBounds(100, 450, 200, 25);
		neupasswortinput.setToolTipText("Passwort");
		cp.add(neupasswortinput);
		neubenutzername.setBounds(100, 375,200, 25);
		
		cp.add(neubenutzername);
		neupasswort.setBounds(100, 475, 200, 25);
		neupasswortcorr.setBounds(100, 425, 200, 25);
		cp.add(neupasswortcorr);
		cp.add(neupasswort);
		neupasswortcorrinput.setBounds(100, 500, 200, 25);
		neupasswortcorrinput.setToolTipText("Passwort");
		cp.add(neupasswortcorrinput);
		
		JButton login = new JButton("Login");
		login.setBounds(100, 225, 200, 25);
		cp.add(login);
		
		JButton registrieren = new JButton("Registrieren");
		registrieren.setBounds(100, 525, 200, 25);
		cp.add(registrieren);
		
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				show();
				controller.start();
			}
		});
		registrieren.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showmenue();
			}
		});
		
		
		return frame;
	}
	// ---------------------------------------------------------------------------
	
	public void show() {
		frame.setLocation(100, 100);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		menue.setVisible(false);
		regFrame.setVisible(false);
	}

	// ---------------------------------------------------------------------------
	
	public void showregFrame() {
		regFrame.setLocation(100, 100);
		regFrame.pack();
		regFrame.setSize(540, 700);
		regFrame.setResizable(false);
		regFrame.setVisible(true);
		menue.setVisible(false);
	}

	// ---------------------------------------------------------------------

	private JFrame createFrame() {
		// Frame with default close handler
		final JFrame frame = new JFrame(WINDOW_TITLE);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		//
		// Menu Bar
		//
		new MenuProvider(frame, gamefield, controller, statistics);

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
		}

		@Override
		public void keyReleased(KeyEvent e) {
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
