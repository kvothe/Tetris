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

public class JGameField extends JComponent implements GameFieldChangedListener, GameDataChangedListener {
	private static final long serialVersionUID = 7822821719800517942L;

	// ---------------------------------------------------------------------

	private int width;
	private int height;
	private int blocksize;

	private Color backgroundColor;
	private Color borderColor;

	// ---------------------------------------------------------------------

	private Stone stone;
	private Block[][] blocks;
	private boolean gameOver = false;

	// ---------------------------------------------------------------------

	public JGameField(int width, int height, int blocksize, Color bg, Color border) {
		this.width = width;
		this.height = height;
		this.blocksize = blocksize;
		// --
		this.backgroundColor = bg;
		this.borderColor = border;
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
		g.setColor(backgroundColor);
		g.fillRect(0, 0, (width + 2) * blocksize, (height + 2) * blocksize);
		// --
		g.setColor(borderColor);
		int offset = blocksize / 2;
		g.drawRect(offset, offset, width * blocksize + offset * 2, height * blocksize + offset * 2);
		g.drawRect(blocksize - 3, blocksize - 3, width * blocksize + 6, height * blocksize + 6);
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
			GraphicsProviderRegistry.getProvider().drawStone(g, stone);
		}
		// --
		if (gameOver) {
			g.setColor(backgroundColor);
			g.fillRect(blocksize * 2, height / 2 - 2 * blocksize, width - 2 * blocksize, blocksize * 4);
			g.setColor(borderColor);
			g.drawRect(blocksize * 2, height / 2 - 2 * blocksize, width - 2 * blocksize, blocksize * 4);
			// --
			g.drawString("GAME OVER", blocksize * 4, height / 2);
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

	@Override
	public void blocksChanged(Block[][] blocks) {
		this.blocks = blocks;
		repaint();
	}

	@Override
	public void scoreChanged(long newScore) {
		// TODO Auto-generated method stub
		System.out.println("Score: " + newScore);
	}

	@Override
	public void levelChanged(int newLevel) {
		// TODO Auto-generated method stub
		System.out.println("Level: " + newLevel);
	}

	@Override
	public void gameStarted() {
		gameOver = false;
	}

	@Override
	public void gameOver() {
		gameOver = true;
		repaint();
	}

}
