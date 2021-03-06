package jku.se.tetris.ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import jku.se.tetris.control.GameController;
import jku.se.tetris.sound.MidiPlayer;
import jku.se.tetris.ui.swing.dialogs.RegisterDialog;
import jku.se.tetris.ui.swing.dialogs.SignInDialog;
import jku.se.tetris.ui.swing.dialogs.StatisticsProvider;

public class MenuProvider {

	private JFrame frame;
	private GameController controller;
	private StatisticsProvider statistics;

	// ---------------------------------------------------------------------

	public MenuProvider(JFrame frame, GameController controller, StatisticsProvider statistics) {
		this.frame = frame;
		this.controller = controller;
		this.statistics = statistics;
		// --
		populateMenuBar();
	}

	// ---------------------------------------------------------------------

	private void populateMenuBar() {
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
		// --
		menuGame.addSeparator();
		// Game -> Reset
		JMenuItem itemPause = new JMenuItem("Pause");
		itemPause.setEnabled(false);
		menuGame.add(itemPause);
		// Game -> Reset
		JMenuItem itemResume = new JMenuItem("Resume");
		itemResume.setEnabled(false);
		menuGame.add(itemResume);
		// --
		menuGame.addSeparator();
		// Game -> Exit
		JMenuItem itemExit = new JMenuItem("Exit");
		menuGame.add(itemExit);
		//
		// Statistics
		//
		JMenu menuStatistics = new JMenu("Statistics");
		menuBar.add(menuStatistics);
		// Statistics -> Show Highscore
		JMenuItem itemHighscore = new JMenuItem("Show Highscore...");
		menuStatistics.add(itemHighscore);
		//
		// User
		//
		JMenu menuUser = new JMenu("User");
		menuBar.add(menuUser);
		// --
		JMenuItem itemSignIn = new JMenuItem("Sign In...");
		menuUser.add(itemSignIn);
		// --
		JMenuItem itemSignOut = new JMenuItem("Sign Out");
		menuUser.add(itemSignOut);
		// --
		menuUser.addSeparator();
		// --
		JMenuItem itemRegister = new JMenuItem("Register...");
		menuUser.add(itemRegister);

		//
		// Audio
		//
		JMenu menuAudio = new JMenu("Audio");
		// menuBar.add(menuAudio);
		// --
		JMenuItem itemAudioStart = new JMenuItem("Play Music");
		menuAudio.add(itemAudioStart);
		// --
		JMenuItem itemAudioStop = new JMenuItem("Stop Music");
		menuAudio.add(itemAudioStop);

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
		// MenuItem: Game -> Resume
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
				// --
				MidiPlayer.stopBackgroundMusic();
				MidiPlayer.teardown();
				// --
				frame.dispose();
			}
		});

		// MenuItem: Statistics -> Show Highscore
		itemHighscore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				statistics.showScoreListDialog();
			}
		});

		// MenuItem: User -> SignIn
		itemSignIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SignInDialog dialog = new SignInDialog(frame);
				dialog.open();
			}
		});
		// MenuItem: User -> SignOut
		itemSignOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "You have been signed out.", "Sign Out", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		// MenuItem: User -> Register
		itemRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterDialog dialog = new RegisterDialog(frame);
				dialog.open();
			}
		});

		// MenuItem: Audio -> Play Music
		itemAudioStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MidiPlayer.startBackgroundMusic();
			}
		});

		// MenuItem: Audio -> Play Music
		itemAudioStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MidiPlayer.stopBackgroundMusic();
			}
		});
	}
}
