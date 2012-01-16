package jku.se.tetris.model;

import java.util.ArrayList;

import jku.se.tetris.model.exception.InvalidActionException;
import jku.se.tetris.model.stones.Stone;
import jku.se.tetris.model.stones.StoneFactory;
import jku.se.tetris.sound.MidiPlayer;

/**
 * @author Markus Hofmarcher
 * 
 *         Implementation of a Tetris game field model.
 */
public class GameFieldImpl implements GameField {
	/**
	 * Defines the max level.
	 */
	public static final int MAX_LEVEL = 100;

	/**
	 * Defines the level threshold.
	 */
	public static int LEVEL_THRESHOLD = 10000;

	// ---------------------------------------------------------------------------

	private int width;
	private int height;

	// ---------------------------------------------------------------------------

	private long score;
	private int level;

	// ---------------------------------------------------------------------------

	private boolean backToBack = false;

	// ---------------------------------------------------------------------------

	private Block[][] blocks;

	// ---------------------------------------------------------------------------

	private Stone activeStone;
	private Stone nextStone;

	// ---------------------------------------------------------------------------

	private ArrayList<GameFieldChangedListener> fieldListeners;
	private ArrayList<GameDataChangedListener> dataListeners;
	private ArrayList<GameEventListener> eventListeners;

	// ---------------------------------------------------------------------------

	private EGameState gameState;

	// ---------------------------------------------------------------------------

	private long gameStart = 0;
	private long gameDuration = 0;

	// ---------------------------------------------------------------------------

	/**
	 * Create a new Tetris GameField implementation with the specified width and
	 * height.
	 * 
	 * @param width
	 *            width in blocks
	 * @param height
	 *            height in blocks
	 */
	public GameFieldImpl(int width, int height) {
		this.width = width;
		this.height = height;
		// --
		score = 0;
		level = 1;
		// --
		blocks = new Block[height][width];
		// --
		fieldListeners = new ArrayList<GameFieldChangedListener>(1);
		dataListeners = new ArrayList<GameDataChangedListener>(1);
		eventListeners = new ArrayList<GameEventListener>(1);
		// --
		gameState = EGameState.INITIALIZED;
		// --
		if (System.getProperty("jku.se.tetris.threshold") != null) {
			LEVEL_THRESHOLD = Integer.parseInt(System.getProperty("jku.se.tetris.threshold"));
		}
	}

	// ---------------------------------------------------------------------------

	private boolean audioOn = true;
	private boolean newStoneInCenter = true;

	// ---------------------------------------------------------------------------

	@Override
	public void configure(boolean squareBlocksOnly, boolean newStoneInCenter, boolean audioOn) {
		this.audioOn = audioOn;
		this.newStoneInCenter = newStoneInCenter;
		// --
		StoneFactory.configure(squareBlocksOnly);
	}

	// ---------------------------------------------------------------------------

	@Override
	public int getWidth() {
		return width;
	}

	// ---------------------------------------------------------------------------

	@Override
	public int getHeight() {
		return height;
	}

	// ---------------------------------------------------------------------------

	@Override
	public void newGame() {
		score = 0;
		level = 1;
		// --
		blocks = new Block[height][width];
		// --
		gameState = EGameState.PLAYING;
		// --
		gameStart = System.currentTimeMillis();
		gameDuration = 0;
		// --
		notifyGameStarted();
		// --
		notifyScoreChanged();
		notifyLevelChanged();
		notifyBlocksChanged();
		// --
		if (audioOn) {
			MidiPlayer.resetBackgroundMusic();
			MidiPlayer.startBackgroundMusic();
		}
		// --
		try {
			newStone();
		} catch (InvalidActionException e) {
			// ignore
		}
	}

	// ---------------------------------------------------------------------------

	private void checkState() throws InvalidActionException {
		if (gameState != EGameState.PLAYING) {
			throw new InvalidActionException("action not allowed");
		}
	}

	// ---------------------------------------------------------------------------

	private void newStone() throws InvalidActionException {
		checkState();
		// --
		if (nextStone != null) {
			activeStone = nextStone;
			nextStone = StoneFactory.getStone();
		} else {
			activeStone = StoneFactory.getStone();
			nextStone = StoneFactory.getStone();
		}
		// --
		if (newStoneInCenter) {
			//
			// Position new stone at the center of the top row
			//
			int offset = activeStone.getWidth() > 1 ? -1 : 0;
			offset = activeStone.getWidth() > 3 ? -2 : offset;
			// --
			activeStone.move((width / 2) + offset, 0);
		} else {
			//
			// Position new stone at the top left corner of the field
			//
			activeStone.move(0, 0);
		}
		// --
		if (checkCollision()) {
			gameState = EGameState.GAMEOVER;
			gameDuration = System.currentTimeMillis() - gameStart;
			// --
			if (audioOn) {
				MidiPlayer.stopBackgroundMusic();
			}
			// --
			notifyGameOver();
			return;
		}
		// --
		notifyStoneAdded();
		// --
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
	public long getGameDuration() {
		return gameDuration;
	}

	// ---------------------------------------------------------------------------

	public Block[][] getBlocks() {
		return this.blocks;
	}

	// ---------------------------------------------------------------------------

	@Override
	public Stone getCurrentStone() {
		return this.activeStone;
	}

	// ---------------------------------------------------------------------------

	@Override
	public Stone getNextStone() {
		return nextStone;
	}

	// ---------------------------------------------------------------------------

	@Override
	public void rotateStoneClockwise() throws InvalidActionException {
		checkState();
		// --
		synchronized (activeStone) {
			activeStone.rotateClockwise();
			// --
			if (checkCollision()) {
				activeStone.rotateCounterClockwise();
			} else {
				notifyStoneMoved(activeStone.getX(), activeStone.getY());
				notifyStoneRotatedClockwise();
			}
		}
	}

	@Override
	public void rotateStoneCounterClockwise() throws InvalidActionException {
		checkState();
		// --
		synchronized (activeStone) {
			this.activeStone.rotateCounterClockwise();
			// --
			if (checkCollision()) {
				activeStone.rotateClockwise();
			} else {
				notifyStoneMoved(activeStone.getX(), activeStone.getY());
				notifyStoneRotatedCounterClockwise();
			}
		}
	}

	// ---------------------------------------------------------------------------

	@Override
	public void moveStoneLeft() throws InvalidActionException {
		checkState();
		// --
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
				notifyStoneMovedLeft();
			}
		}
	}

	// ---------------------------------------------------------------------------

	@Override
	public void moveStoneRight() throws InvalidActionException {
		checkState();
		// --
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
				notifyStoneMovedRight();
			}
		}
	}

	// ---------------------------------------------------------------------------

	@Override
	public void moveStoneDown() throws InvalidActionException {
		checkState();
		// --
		synchronized (activeStone) {
			int x = activeStone.getX();
			int y = activeStone.getY();
			// --
			activeStone.move(x, y + 1);
			// if this move leads to a collision -> undo move and add to fixed
			// blocks
			if (checkCollision()) {
				activeStone.move(x, y);
				// --
				if (activeStone.getY() + activeStone.getHeight() == height) {
					notifyStoneAtBottom();
				} else {
					notifyStoneCollision();
				}
				// --
				stoneToBlocks();
				newStone();
			} else {
				notifyStoneMoved(x, y);
			}
		}
	}

	// ---------------------------------------------------------------------------

	@Override
	public void moveStoneToBottom() throws InvalidActionException {
		checkState();
		// --
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
			calculateScoreFreefall(activeStone.getY() - y);
			// --
			if (activeStone.getY() + activeStone.getHeight() == height) {
				notifyStoneAtBottom();
			} else {
				notifyStoneCollision();
			}
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

	@Override
	public void addGameEventListener(GameEventListener listener) {
		if (!eventListeners.contains(listener)) {
			eventListeners.add(listener);
		}
	}

	@Override
	public void removeGameEventListener(GameEventListener listener) {
		if (eventListeners.contains(listener)) {
			eventListeners.remove(listener);
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
		checkForFullRows();
	}

	// ---------------------------------------------------------------------------

	private boolean checkCollision() {
		for (Block b : activeStone.getBlocks()) {
			int x = b.getX() + activeStone.getX();
			int y = b.getY() + activeStone.getY();
			// --
			if (y >= height) {
				return true;
			}
			if (x >= width) {
				return true;
			}
			if (x < 0) {
				return true;
			}
			// --
			if (blocks[y][x] != null) {
				return true;
			}
		}
		return false;
	}

	// ---------------------------------------------------------------------------

	private void checkForFullRows() {
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
		if (tmpRows.size() > 0) {
			// --
			for (int row : tmpRows) {
				for (int i = row - 1; i >= 0; i--) {
					for (int j = 0; j < width; j++) {
						blocks[i + 1][j] = blocks[i][j];
					}
				}
			}
			// --
			calculateScore(tmpRows.size());
			// --
			int[] rows = new int[tmpRows.size()];
			for (int r = 0; r < tmpRows.size(); r++) {
				rows[r] = tmpRows.get(r) + 1;
			}
			// --
			notifyRowComplete(rows);
		}
	}

	// ---------------------------------------------------------------------------

	private void calculateScore(int removedRowCount) {
		if (removedRowCount > 0) {
			//@formatter:off
			switch (removedRowCount) {
				case 1: score += (100 * level); backToBack = false; break;
				case 2: score += (300 * level); backToBack = false; break;
				case 3: score += (500 * level); backToBack = false; break;
				case 4:
					if (backToBack) score += (800 * level) / 2;
					score += (800 * level); 
					backToBack = true; 
					break;
			}		
			//@formatter:on			
			notifyScoreChanged();
			calculateLevel();
		}
	}

	// ---------------------------------------------------------------------------

	private void calculateScoreFreefall(int distance) {
		score += 2 * distance;
		// --
		notifyScoreChanged();
		calculateLevel();
	}

	// ---------------------------------------------------------------------------

	private void calculateLevel() {
		int tmpLevel = (int) (score / LEVEL_THRESHOLD) + 1;
		// --
		if (tmpLevel != this.level) {
			if (tmpLevel <= MAX_LEVEL) {
				this.level = tmpLevel;
			} else {
				this.level = MAX_LEVEL;
			}
			notifyLevelChanged();
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
	private void notifyLevelChanged() {
		for (GameDataChangedListener l : dataListeners) {
			l.levelChanged(level);
		}
	}
	private void notifyGameStarted() {
		for (GameDataChangedListener l : dataListeners) {
			l.gameStarted();
		}
	}
	private void notifyGameOver() {
		for (GameDataChangedListener l : dataListeners) {
			l.gameOver(score, level, gameDuration);
		}
	}

	// ---------------------------------------------------------------------------

	private void notifyStoneMovedLeft() {
		for (GameEventListener l : eventListeners) {
			l.stoneMovedLeft();
		}
	}

	private void notifyStoneMovedRight() {
		for (GameEventListener l : eventListeners) {
			l.stoneMovedRight();
		}
	}

	private void notifyStoneAtBottom() {
		for (GameEventListener l : eventListeners) {
			l.stoneAtBottom();
		}
	}

	private void notifyStoneCollision() {
		for (GameEventListener l : eventListeners) {
			l.stoneCollision();
		}
	}

	private void notifyStoneRotatedClockwise() {
		for (GameEventListener l : eventListeners) {
			l.stoneRotatedClockwise();
		}
	}

	private void notifyStoneRotatedCounterClockwise() {
		for (GameEventListener l : eventListeners) {
			l.stoneRotatedCounterClockwise();
		}
	}

	private void notifyRowComplete(int[] rows) {
		for (GameEventListener l : eventListeners) {
			l.rowComplete(rows);
		}
	}
}
