package jku.se.tetris.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;

import javax.swing.JComponent;

import jku.se.tetris.ui.swing.Drawable;

public class Block extends JComponent implements Drawable {
	public static int BLOCK_SIZE = 10;

	// ---------------------------------------------------------------------------

	protected int x;
	protected int y;

	protected int xOrigin = 0;
	protected int yOrigin = 0;

	// ---------------------------------------------------------------------------

	private Color color;

	// ---------------------------------------------------------------------------

	public Block(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	// ---------------------------------------------------------------------------

	public void setColor(Color color) {
		this.color = color;
		repaint();
	}

	// ---------------------------------------------------------------------------

	@Override
	protected void paintComponent(Graphics g) {
		// Prettify if possible
		if (g instanceof Graphics2D) {
			Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
			// --
			((Graphics2D) g).setStroke(stroke);
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		// --
		// Rectangle area = g.getClipBounds();
		// --
		g.setColor(color);
		g.fillRect(xOrigin + x, yOrigin + y, BLOCK_SIZE, BLOCK_SIZE);
		// --
		g.setColor(Color.BLACK);
		g.drawRect(xOrigin + x, yOrigin + y, BLOCK_SIZE, BLOCK_SIZE);
	}

	// ---------------------------------------------------------------------------

	@Override
	public void draw(int xOrigin, int yOrigin) {
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
		// --
		repaint();
	}
}
