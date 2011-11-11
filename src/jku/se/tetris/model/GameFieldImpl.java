package jku.se.tetris.model;

import java.util.ArrayList;

public class GameFieldImpl implements GameField {
	private int width;
	private int height;

	// ---------------------------------------------------------------------------

	private long score;
	private int level;

	// ---------------------------------------------------------------------------

	private Block[][] gameField;

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
		gameField = new Block[height][width];
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
		gameField = new Block[height][width];
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
		notifyStoneAdded();
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
		return gameField.clone();
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
			if (x > 0) {
				activeStone.move(x - 1, y);
			}
			// --
			notifyStoneMoved(x, y);
		}
	}

	// ---------------------------------------------------------------------------

	@Override
	public void moveStoneRight() {
		synchronized (activeStone) {
			int x = activeStone.getX();
			int y = activeStone.getY();
			// --
			if (x < width - 1) {
				activeStone.move(x + 1, y);
			}
			// --
			notifyStoneMoved(x, y);
		}
	}

	// ---------------------------------------------------------------------------

	@Override
	public void moveStoneDown() {
		synchronized (activeStone) {
			int x = activeStone.getX();
			int y = activeStone.getY();
			// --
			// TODO check possible collision
			if (y + activeStone.getHeight() < height - 1) {
				activeStone.move(x, y + 1);
			} else {
				stoneToBlocks();
				newStone();
			}
			// --
			notifyStoneMoved(x, y);
		}
	}

	// ---------------------------------------------------------------------------

	@Override
	public void moveStoneToBottom() {
		synchronized (activeStone) {
			// TODO Auto-generated method stub
			// get bottom position from collision detection
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
			gameField[activeStone.getY() + b.getY()][activeStone.getX() + b.getX()] = b;
		}
		// --
		notifyBlocksChanged();
	}

	// ---------------------------------------------------------------------------

	private boolean checkCollision() {
		for (Block b : activeStone.getBlocks()) {
			int x = b.getX() + activeStone.getX();
			int y = b.getY() + activeStone.getY();
			// --
			// TODO
		}
		return false;
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
			l.blocksChanged(gameField);
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
