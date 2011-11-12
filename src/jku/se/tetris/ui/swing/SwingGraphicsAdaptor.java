package jku.se.tetris.ui.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

import jku.se.tetris.model.Block;
import jku.se.tetris.model.GraphicsAdaptor;
import jku.se.tetris.model.GraphicsProviderRegistry;
import jku.se.tetris.model.Stone;

public class SwingGraphicsAdaptor implements GraphicsAdaptor {
	private int blocksize;

	public SwingGraphicsAdaptor(int blocksize) {
		this.blocksize = blocksize;
	}

	@Override
	public void drawBlock(Graphics graphics, int x, int y, Color color) {
		// Prettify if possible
		if (graphics instanceof Graphics2D) {
			Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
			// --
			((Graphics2D) graphics).setStroke(stroke);
			((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		// --
		// Rectangle area = g.getClipBounds();
		// --
		graphics.setColor(color);
		graphics.fillRect(x, y, blocksize, blocksize);
		// --
		graphics.setColor(Color.BLACK);
		graphics.drawRect(x, y, blocksize, blocksize);

	}

	// ---------------------------------------------------------------------

	@Override
	public void drawStone(Graphics g, Stone stone) {
		for (Block b : stone.getBlocks()) {
			GraphicsProviderRegistry.getProvider().drawBlock(g, (stone.getX() + b.getX() + 1) * blocksize, (stone.getY() + b.getY() + 1) * blocksize, b.getColor());
		}
	}

	// ---------------------------------------------------------------------

	public static void configureGraphicsContext(Graphics g) {
		// Prettify if possible
		if (g instanceof Graphics2D) {
			Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
			// --
			((Graphics2D) g).setStroke(stroke);
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
	}
}
