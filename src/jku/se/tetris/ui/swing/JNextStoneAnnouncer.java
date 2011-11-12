package jku.se.tetris.ui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import jku.se.tetris.model.Block;
import jku.se.tetris.model.GameDataChangedListener;
import jku.se.tetris.model.GameFieldChangedListener;
import jku.se.tetris.model.GraphicsProviderRegistry;
import jku.se.tetris.model.Stone;

public class JNextStoneAnnouncer extends JComponent implements GameFieldChangedListener, GameDataChangedListener {

	private int width;
	private int height;
	private int blocksize;

	// ---------------------------------------------------------------------

	private Color colorBackground;
	private Color colorForeground;

	// ---------------------------------------------------------------------

	private Stone nextStone;

	// ---------------------------------------------------------------------

	public JNextStoneAnnouncer(int width, int height, int blocksize, Color bg, Color fg) {
		this.width = width;
		this.height = height;
		this.blocksize = blocksize;
		// --
		colorBackground = bg;
		colorForeground = fg;
		// --
		setPreferredSize(new Dimension(width * blocksize, height * blocksize));
	}

	// ---------------------------------------------------------------------

	@Override
	protected void paintComponent(Graphics g) {
		SwingGraphicsAdaptor.configureGraphicsContext(g);
		// --
		g.setColor(colorBackground);
		g.fillRect(0, 0, width * blocksize, height * blocksize);
		// --
		g.setColor(colorForeground);
		int offset = blocksize / 2;
		g.drawRect(offset, offset, (width * blocksize) - (offset * 2), (height * blocksize) - (offset * 2));
		// --
		if (nextStone == null) {
			// clear -> do nothing
		} else {
			GraphicsProviderRegistry.getProvider().drawStone(g, nextStone);
		}
	}

	// ---------------------------------------------------------------------

	@Override
	public void announceNextStone(Stone nextStone) {
		this.nextStone = nextStone;
		repaint();
	}

	// ---------------------------------------------------------------------

	@Override
	public void newStone(Stone newStone) {
	}

	@Override
	public void stoneMoved(Stone stone, int xOld, int yOld) {
	}

	@Override
	public void blocksChanged(Block[][] blocks) {
	}

	// ---------------------------------------------------------------------

	@Override
	public void scoreChanged(long newScore) {
		// TODO Auto-generated method stub

	}

	@Override
	public void levelChanged(int newLevel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameOver() {
		nextStone = null;
		repaint();
	}
}
