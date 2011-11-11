package jku.se.tetris.model;

import java.util.ArrayList;

public class GameFieldImpl implements GameField {
	private int width;
	private int height;

	// ---------------------------------------------------------------------------

	private long score;
	private int level;

	// ---------------------------------------------------------------------------

	private Block[][] blocks;

	// ---------------------------------------------------------------------------

	private Stone activeStone;
	private Stone nextStone;

	// ---------------------------------------------------------------------------

	private ArrayList<GameFieldChangedListener> fieldListeners;
	private ArrayList<GameDataChangedListener> dataListeners;

	// ---------------------------------------------------------------------------

	public GameFieldImpl(int width, int height) {
		this.width = width;
		this.height = height;
		// --
		score = 0;
		level = 1;
		// --
		blocks = new Block[height][width];
		// --
		fieldListeners = new ArrayList<GameFieldChangedListener>();
		dataListeners = new ArrayList<GameDataChangedListener>();
	}

	// ---------------------------------------------------------------------------

	@Override
	public void newGame() {
		score = 0;
		level = 1;
		// --
		blocks = new Block[height][width];
		// --
		notifyScoreChanged();
		notifyBlocksChanged();
		newStone();
	}

	// ---------------------------------------------------------------------------

	@Override
	public void newStone() {
		activeStone = new Stone();
		// --
		activeStone.move((width / 2) + (activeStone.getWidth() > 1 ? -1 : 0), 0);
		// --
		if (checkCollision()) {
			notifyGameOver();
			return;
		}
		// --
		notifyStoneAdded();
		// --
		nextStone = new Stone();
		notifyAnnounceNextStone();
	}
	// ---------------------------------------------------------------------------

	@Override
	public long getScore() {
		return this.score;
	}

	// ---------------------------------------------------------------------------

	@Override
	public int getLevel() {
		return this.level;
	}

	// ---------------------------------------------------------------------------

	@Override
	public Block[][] getBlocks() {
		return blocks.clone();
	}

	// ---------------------------------------------------------------------------

	@Override
	public Stone getCurrentStone() {
		return this.activeStone;
	}

	// ---------------------------------------------------------------------------

	@Override
	public Stone getNextStone() {
		return null;
	}

	// ---------------------------------------------------------------------------

	@Override
	public void rotateStoneClockwise() {
		synchronized (activeStone) {
			this.activeStone.rotateClockwise();
		}
	}

	@Override
	public void rotateStoneCounterClockwise() {
		synchronized (activeStone) {
			this.activeStone.rotateCounterClockwise();
		}
	}

	// ---------------------------------------------------------------------------

	@Override
	public void moveStoneLeft() {
		synchronized (activeStone) {
			int x = activeStone.getX();
			int y = activeStone.getY();
			// --
			activeStone.move(x - 1, y);
			// --
			if (checkCollision()) {
				activeStone.move(x, y);
			} else {
				notifyStoneMoved(x, y);
			}
		}
	}

	// ---------------------------------------------------------------------------

	@Override
	public void moveStoneRight() {
		synchronized (activeStone) {
			int x = activeStone.getX();
			int y = activeStone.getY();
			// --
			activeStone.move(x + 1, y);
			// --
			if (checkCollision()) {
				activeStone.move(x, y);
			} else {
				notifyStoneMoved(x, y);
			}
		}
	}

	// ---------------------------------------------------------------------------

	@Override
	public void moveStoneDown() {
		synchronized (activeStone) {
			int x = activeStone.getX();
			int y = activeStone.getY();
			// --
			activeStone.move(x, y + 1);
			// if this move leads to a collision -> undo move and add to fixed
			// blocks
			if (checkCollision()) {
				activeStone.move(x, y);
				stoneToBlocks();
				newStone();
			} else {
				notifyStoneMoved(x, y);
			}
		}
	}

	// ---------------------------------------------------------------------------

	@Override
	public void moveStoneToBottom() {
		synchronized (activeStone) {
			int x = activeStone.getX();
			int y = activeStone.getY();
			// --
			for (int i = 1; i <= height; i++) {
				activeStone.move(x, y + i);
				if (checkCollision()) {
					activeStone.move(x, y + (i - 1));
					break;
				}
			}
			// --
			notifyStoneMoved(x, y);
			// --
			stoneToBlocks();
			newStone();
		}
	}
	// ---------------------------------------------------------------------------

	@Override
	public void addFieldChangedListener(GameFieldChangedListener listener) {
		if (!fieldListeners.contains(listener)) {
			fieldListeners.add(listener);
		}
	}

	@Override
	public void removeFieldChangedListener(GameFieldChangedListener listener) {
		if (fieldListeners.contains(listener)) {
			fieldListeners.remove(listener);
		}

	}

	@Override
	public void addDataChangedListener(GameDataChangedListener listener) {
		if (!dataListeners.contains(listener)) {
			dataListeners.add(listener);
		}
	}

	@Override
	public void removeDataChangedListener(GameDataChangedListener listener) {
		if (dataListeners.contains(listener)) {
			dataListeners.remove(listener);
		}

	}

	// ---------------------------------------------------------------------------

	private void stoneToBlocks() {
		for (Block b : activeStone.getBlocks()) {
			blocks[activeStone.getY() + b.getY()][activeStone.getX() + b.getX()] = b;
		}
		// --
		notifyBlocksChanged();
		// --
		int[] full = checkForFullRows();
		// --
		if (full.length > 0) {
			System.out.println("full rows: " + full.length);
			// --
			for (int row : full) {
				removeRow(row);
			}
		}
	}

	// ---------------------------------------------------------------------------

	private boolean checkCollision() {
		for (Block b : activeStone.getBlocks()) {
			int x = b.getX() + activeStone.getX();
			int y = b.getY() + activeStone.getY();
			// --
			if (y == height) {
				System.out.println("height");
				return true;
			}
			if (x == width) {
				System.out.println("width");
				return true;
			}
			if (x == -1) {
				System.out.println("x==-1");
				return true;
			}
			// --
			if (blocks[y][x] != null) {
				return true;
			}
			// TODO
		}
		return false;
	}

	// ---------------------------------------------------------------------------

	private int[] checkForFullRows() {
		ArrayList<Integer> tmpRows = new ArrayList<Integer>();
		// --
		for (int i = 0; i < height; i++) {
			boolean full = true;
			// --
			for (int j = 0; j < width; j++) {
				if (blocks[i][j] == null) {
					full = false;
					break;
				}
			}
			// --
			if (full) {
				tmpRows.add(i);
			}
		}
		// --
		int[] result = new int[tmpRows.size()];
		for (int i = 0; i < tmpRows.size(); i++) {
			result[i] = tmpRows.get(i);
		}
		// --
		return result;
	}

	// ---------------------------------------------------------------------------

	private void removeRow(int row) {
		for (int i = row - 1; i >= 0; i--) {
			for (int j = 0; j < width; j++) {
				blocks[i + 1][j] = blocks[i][j];
			}
		}
	}

	// ---------------------------------------------------------------------------

	private void notifyAnnounceNextStone() {
		for (GameFieldChangedListener l : fieldListeners) {
			l.announceNextStone(nextStone);
		}
	}
	private void notifyStoneAdded() {
		for (GameFieldChangedListener l : fieldListeners) {
			l.newStone(activeStone);
		}
	}
	private void notifyStoneMoved(int xOld, int yOld) {
		for (GameFieldChangedListener l : fieldListeners) {
			l.stoneMoved(activeStone, xOld, yOld);
		}
	}
	private void notifyBlocksChanged() {
		for (GameFieldChangedListener l : fieldListeners) {
			l.blocksChanged(blocks);
		}
	}

	// ---------------------------------------------------------------------------

	private void notifyScoreChanged() {
		for (GameDataChangedListener l : dataListeners) {
			l.scoreChanged(score);
		}
	}
	private void notifLevelChanged() {
		for (GameDataChangedListener l : dataListeners) {
			l.levelChanged(level);
		}
	}
	private void notifyGameOver() {
		for (GameDataChangedListener l : dataListeners) {
			l.gameOver();
		}
	}
}
