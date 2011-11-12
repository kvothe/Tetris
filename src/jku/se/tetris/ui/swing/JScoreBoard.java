package jku.se.tetris.ui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JComponent;

import jku.se.tetris.model.GameDataChangedListener;

public class JScoreBoard extends JComponent implements GameDataChangedListener {

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
		setFontStyle(g, Font.BOLD, 18);
		drawStringCenter(g, "Score: " + score, 20);
		// --
		setFontStyle(g, Font.BOLD, 18);
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

	private void drawStringLeft(Graphics g, String string, int yOffset) {
		FontMetrics fm = g.getFontMetrics();
		int ascent = fm.getAscent();
		int fHeight = ascent + fm.getDescent();
		// --
		int strWidth = fm.stringWidth(string);
		// --
		g.drawString(string, 10, yOffset + ascent);
	}

	// ---------------------------------------------------------------------

	private void setFontStyle(Graphics g, int style, int size) {
		Font f = g.getFont();
		g.setFont(f.deriveFont(style, size));
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
	public void gameOver() {
		// TODO bold
	}

}
