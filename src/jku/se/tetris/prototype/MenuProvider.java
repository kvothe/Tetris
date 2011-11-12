package jku.se.tetris.prototype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import jku.se.tetris.control.Controller;
import jku.se.tetris.model.GameField;

public class MenuProvider {

	private JFrame frame;
	private GameField gamefield;
	private Controller controller;
	private StatisticsProvider statistics;

	// ---------------------------------------------------------------------

	public MenuProvider(JFrame frame, GameField gamefield, Controller controller, StatisticsProvider statistics) {
		this.frame = frame;
		this.gamefield = gamefield;
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
		// Game -> Reset
		JMenuItem itemPause = new JMenuItem("Pause");
		menuGame.add(itemPause);
		// Game -> Reset
		JMenuItem itemResume = new JMenuItem("Resume");
		menuGame.add(itemResume);
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

		// MenuItem: Statistics -> Show Highscore
		itemHighscore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				statistics.showScoreListDialog();
			}
		});
	}
}