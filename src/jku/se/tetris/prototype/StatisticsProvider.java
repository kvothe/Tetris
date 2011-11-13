package jku.se.tetris.prototype;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jku.se.tetris.model.GameDataChangedListener;
import jku.se.tetris.model.GameField;

public class StatisticsProvider implements GameDataChangedListener {

	private GameField gamefield;
	private JFrame gui;

	// ---------------------------------------------------------------------

	private List<ScoreListEntry> scoreList;

	// ---------------------------------------------------------------------

	public StatisticsProvider(GameField gameField) {
		this.gamefield = gameField;
		// --
		scoreList = new ArrayList<StatisticsProvider.ScoreListEntry>();
		// --
		gamefield.addDataChangedListener(this);
	}

	// ---------------------------------------------------------------------

	public void setGui(JFrame gui) {
		this.gui = gui;
	}

	// ---------------------------------------------------------------------

	public void showScoreListDialog() {
		if (gui != null) {
			JDialog dialog = new JDialog(gui);
			dialog.setModal(true);
			dialog.setTitle("Highscore");
			dialog.setLocationRelativeTo(gui);
			dialog.setResizable(false);
			// --
			Container cp = dialog.getContentPane();
			cp.setLayout(new BorderLayout());
			// --
			Container buttons = new Container();
			cp.add(buttons, BorderLayout.NORTH);
			buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
			// --
			JLabel text = new JLabel("   Selection :");
			text.setFont(gui.getFont().deriveFont(20f));
			// --
			JRadioButton btn1 = new JRadioButton("Highest Score");
			JRadioButton btn2 = new JRadioButton("Average Score");
			JRadioButton btn3 = new JRadioButton("Highest Level");
			JRadioButton btn4 = new JRadioButton("Average Level");
			JRadioButton btn5 = new JRadioButton("Average Playtime");
			// --
			ButtonGroup group = new ButtonGroup();
			group.add(btn1);
			group.add(btn2);
			group.add(btn3);
			group.add(btn4);
			group.add(btn5);
			// --
			btn1.setSelected(true);
			// --
			buttons.add(text);
			buttons.add(btn1);
			buttons.add(btn2);
			buttons.add(btn3);
			buttons.add(btn4);
			buttons.add(btn5);
			// --
			Collections.sort(scoreList, new Comparator<ScoreListEntry>() {

				@Override
				public int compare(ScoreListEntry arg0, ScoreListEntry arg1) {
					if (arg0.Score == arg1.Score) {
						return 0;
					} else if (arg0.Score < arg1.Score) {
						return 1;
					} else if (arg0.Score > arg1.Score) {
						return -1;
					}
					return 0;
				}

			});
			// --
			Object[][] data = new Object[scoreList.size()][4];
			for (int i = 0; i < scoreList.size(); i++) {
				ScoreListEntry e = scoreList.get(i);
				// --
				data[i][0] = e.Name;
				data[i][1] = e.Score;
				data[i][2] = e.Level;
				data[i][3] = formatDuration(e.Duration);
			}
			// --
			DefaultTableModel tablemodel = new DefaultTableModel(data, new String[] { "Name", "Score", "Level", "Duration" }) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int col) {
					return false;
				}
			};
			// --
			JTable table = new JTable(tablemodel);
			// --
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(false);
			// --
			cp.add(buttons, BorderLayout.NORTH);
			cp.add(new JScrollPane(table), BorderLayout.CENTER);
			// --
			dialog.pack();
			dialog.setVisible(true);
		}
	}
	// ---------------------------------------------------------------------

	private String formatDuration(long duration) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(duration);
		// --
		int h = c.get(Calendar.HOUR_OF_DAY) - 1;
		int m = c.get(Calendar.MINUTE);
		int s = c.get(Calendar.SECOND);
		// --
		return String.format("%02d:%02d:%02d", h, m, s);
	}

	// ---------------------------------------------------------------------

	@Override
	public void scoreChanged(long newScore) {
	}

	// ---------------------------------------------------------------------

	@Override
	public void levelChanged(int newLevel) {
	}

	// ---------------------------------------------------------------------

	@Override
	public void gameStarted() {
	}

	// ---------------------------------------------------------------------

	@Override
	public void gameOver(final long score, final int level, final long duration) {
		new Thread(new Runnable() {
			public void run() {
				if (gui != null) {
					// ask for name
					String name = JOptionPane.showInputDialog(gui, "Please enter your name");
					// --
					if (name == null || name.length() == 0) {
						name = "---";
					}
					// --
					scoreList.add(new ScoreListEntry(name, score, level, duration));
				}
			}
		}).start();
	}

	// ---------------------------------------------------------------------

	private class ScoreListEntry {
		private String Name;
		private long Score;
		private int Level;
		private long Duration;

		private ScoreListEntry(String name, long score, int level, long duration) {
			this.Name = name;
			this.Score = score;
			this.Level = level;
			this.Duration = duration;
		}
	}
}
