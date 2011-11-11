package jku.se.tetris.ui.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

import javax.swing.JComponent;

import jku.se.tetris.model.Block;
import jku.se.tetris.model.GameDataChangedListener;
import jku.se.tetris.model.GameFieldChangedListener;
import jku.se.tetris.model.GraphicsProviderRegistry;
import jku.se.tetris.model.Stone;

public class SwingGameField extends JComponent implements GameFieldChangedListener, GameDataChangedListener {
	private static final long serialVersionUID = 7822821719800517942L;

	// ---------------------------------------------------------------------

	private int width;
	private int height;
	private int blocksize;

	private Color color = Color.BLACK;
	private Color border = Color.WHITE;

	// ---------------------------------------------------------------------

	public SwingGameField(int width, int height, int blocksize) {
		this.width = width;
		this.height = height;
		this.blocksize = blocksize;
		// --
		this.setPreferredSize(new Dimension((width + 2) * blocksize, (height + 2) * blocksize));
	}

	// ---------------------------------------------------------------------
	//
	// JComponent
	//
	// ---------------------------------------------------------------------

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Prettify if possible
		if (g instanceof Graphics2D) {
			Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
			// --
			((Graphics2D) g).setStroke(stroke);
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		// --
		g.setColor(color);
		g.fillRect(0, 0, (width + 2) * blocksize, (height + 2) * blocksize);
		// --
		g.setColor(border);
		int offset = blocksize / 2;
		g.drawRect(offset, offset, width * blocksize + offset * 2, height * blocksize + offset * 2);
		g.drawRect(blocksize - 1, blocksize - 1, width * blocksize + 2, height * blocksize + 2);
		// --
		if (blocks != null) {
			for (int row = 0; row < blocks.length; row++) {
				for (int b = 0; b < blocks[row].length; b++) {
					if (blocks[row][b] != null) {
						int x = blocksize * (b + 1);
						int y = blocksize * (row + 1);
						Color color = blocks[row][b].getColor();
						// --
						GraphicsProviderRegistry.getProvider().drawBlock(g, x, y, color);
					}
				}
			}
		}
		// --
		if (stone != null) {
			for (Block b : stone.getBlocks()) {
				GraphicsProviderRegistry.getProvider().drawBlock(g, (stone.getX() + b.getX() + 1) * blocksize, (stone.getY() + b.getY() + 1) * blocksize, b.getColor());
			}
		}
		// --
		repaint(g.getClipBounds());
	}
	// ---------------------------------------------------------------------

	@Override
	public void newStone(Stone stone) {
		this.stone = stone;
		repaint();
	}

	@Override
	public void announceNextStone(Stone nextStone) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stoneMoved(Stone stone, int xOld, int yOld) {
		this.stone = stone;
		repaint();
	}

	private Stone stone;
	private Block[][] blocks;

	@Override
	public void blocksChanged(Block[][] blocks) {
		this.blocks = blocks;
		repaint();
	}

	@Override
	public void scoreChanged(long newScore) {
		// TODO Auto-generated method stub

	}

	@Override
	public void levelChanged(int newLevel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub

	}

}
