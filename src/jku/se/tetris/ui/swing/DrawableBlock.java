package jku.se.tetris.ui.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

import javax.swing.JComponent;

public class DrawableBlock extends JComponent {
	private static final long serialVersionUID = -2635080897505277177L;

	// ---------------------------------------------------------------------------

	private int x;
	private int y;
	private int size;
	private Color color;

	// ---------------------------------------------------------------------------

	public DrawableBlock(int x, int y, int size, Color color) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
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
		g.fillRect(x, y, size, size);
		// --
		g.setColor(Color.BLACK);
		g.drawRect(x, y, size, size);
	}
}
