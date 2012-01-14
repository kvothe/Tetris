package jku.se.tetris.ui.swing.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JComponent;

import jku.se.tetris.model.GameDataChangedListener;
import jku.se.tetris.ui.swing.SwingGraphicsAdaptor;

public class JScoreBoard extends JComponent implements GameDataChangedListener {
	private static final long serialVersionUID = -5112749912250732996L;

	// ---------------------------------------------------------------------

	private long score;
	private int level;

	// ---------------------------------------------------------------------

	private int width;
	private int height;
	private int borderInset;

	// ---------------------------------------------------------------------

	private Color colorBackground;
	private Color colorForeground;
	private Color colorText;

	// ---------------------------------------------------------------------

	public JScoreBoard(int width, int height, int borderInset, Color bg, Color fg, Color txt) {
		this.width = width;
		this.height = height;
		this.borderInset = borderInset;
		// --
		colorBackground = bg;
		colorForeground = fg;
		colorText = txt;
		// --
		setPreferredSize(new Dimension(width, height));
	}

	// ---------------------------------------------------------------------

	@Override
	protected void paintComponent(Graphics g) {
		SwingGraphicsAdaptor.configureGraphicsContext(g);
		// --
		g.setColor(colorBackground);
		g.fillRect(0, 0, width, height);
		g.setColor(colorForeground);
		g.drawRect(borderInset, borderInset, width - borderInset * 2, height - borderInset * 2);
		// --
		g.setColor(colorText);
		// --
		SwingGraphicsAdaptor.setFontStyle(g, Font.BOLD, 18);
		drawStringCenter(g, "Score: " + score, 20);
		// --
		SwingGraphicsAdaptor.setFontStyle(g, Font.BOLD, 18);
		drawStringCenter(g, "Level: " + level, 45);
	}

	// ---------------------------------------------------------------------

	private void drawStringCenter(Graphics g, String string, int yOffset) {
		FontMetrics fm = g.getFontMetrics();
		int ascent = fm.getAscent();
		// --
		int strWidth = fm.stringWidth(string);
		// --
		g.drawString(string, (width - strWidth) / 2, yOffset + ascent);
	}

	// ---------------------------------------------------------------------

	@Override
	public void scoreChanged(long newScore) {
		this.score = newScore;
		repaint();
	}

	@Override
	public void levelChanged(int newLevel) {
		this.level = newLevel;
		repaint();
	}

	// ---------------------------------------------------------------------

	@Override
	public void gameStarted() {
		this.score = 0;
		this.level = 1;
		// --
		repaint();
	}

	@Override
	public void gameOver(long score, int level, long duration) {
		// TODO bold
	}

}
