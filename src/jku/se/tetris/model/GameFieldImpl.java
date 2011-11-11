package jku.se.tetris.model;

import java.util.ArrayList;

public class GameFieldImpl implements GameField {
	private int width;
	private int height;

	// ---------------------------------------------------------------------------

	private long score;

	// ---------------------------------------------------------------------------

	private Block[][] gameField;

	// ---------------------------------------------------------------------------

	private Stone activeStone;
	private Stone nextStone;

	// ---------------------------------------------------------------------------

	private ArrayList<GameFieldChangedListener> listeners;

	// ---------------------------------------------------------------------------

	public GameFieldImpl(int width, int height) {
		this.width = width;
		this.height = height;
		// --
		score = 0;
		// --
		gameField = new Block[height][width];
		// --
		listeners = new ArrayList<GameFieldChangedListener>();
	}

	// ---------------------------------------------------------------------------

	@Override
	public void newStone() {
		activeStone = new Stone();
		activeStone.move((width / 2) + (activeStone.getWidth() / 2), 0);
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
		this.activeStone.rotateClockwise();
	}

	@Override
	public void rotateStoneCounterClockwise() {
		this.activeStone.rotateCounterClockwise();
	}

	// ---------------------------------------------------------------------------

	@Override
	public void moveStoneLeft() {
		int x = activeStone.getX();
		int y = activeStone.getY();
		// --
		if (x > 0) {
			activeStone.move(x - 1, y);
		}
	}

	// ---------------------------------------------------------------------------

	@Override
	public void moveStoneRight() {
		int x = activeStone.getX();
		int y = activeStone.getY();
		// --
		if (x < width - 1) {
			activeStone.move(x + 1, y);
		}
	}

	// ---------------------------------------------------------------------------

	@Override
	public void moveStoneToBottom() {
		// TODO Auto-generated method stub
		// get bottom position from collision detection
	}

	// ---------------------------------------------------------------------------

	@Override
	public void addFieldChangedListener(GameFieldChangedListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	@Override
	public void removeFieldChangedListener(GameFieldChangedListener listener) {
		if (listeners.contains(listener)) {
			listeners.remove(listener);
		}

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
		for (GameFieldChangedListener l : listeners) {
			l.announceNextStone(nextStone);
		}
	}
	private void notifyStoneAdded() {
		for (GameFieldChangedListener l : listeners) {
			l.newStone(activeStone);
		}
	}
	private void notifyScoreChanged(int oldScore) {
		for (GameFieldChangedListener l : listeners) {
			l.scoreChanged(oldScore, score);
		}
	}
	private void notifyStoneMoved(int xOld, int yOld) {
		for (GameFieldChangedListener l : listeners) {
			l.stoneMoved(activeStone.getX(), activeStone.getY(), xOld, yOld);
		}
	}
	private void notifyBlocksChanged() {
		for (GameFieldChangedListener l : listeners) {
			l.blocksChanged(gameField);
		}
	}
}
