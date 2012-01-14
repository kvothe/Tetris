package jku.se.tetris.sound;

import java.io.InputStream;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

/**
 * @author Markus Hofmarcher
 * 
 *         Basic implementation of a Midi Sequencer for background music.
 */
public class MidiPlayer {
	private static Sequencer backgroundSequencer;

	static {
		try {
			InputStream audioStream = MidiPlayer.class.getResourceAsStream("Tetris_Background.mid");
			// --
			backgroundSequencer = MidiSystem.getSequencer();
			backgroundSequencer.open();
			backgroundSequencer.setSequence(MidiSystem.getSequence(audioStream));
			backgroundSequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);

		} catch (Throwable e) {
			// --
		}
	}

	/**
	 * Start background music.
	 */
	public static void startBackgroundMusic() {
		try {
			if (backgroundSequencer != null && backgroundSequencer.isOpen() && !backgroundSequencer.isRunning()) {
				backgroundSequencer.start();
			}
		} catch (Throwable e) {
			// --
		}
	}

	/**
	 * Reset the audio loop to the beginning.
	 */
	public static void resetBackgroundMusic() {
		try {
			if (backgroundSequencer != null && backgroundSequencer.isOpen()) {
				backgroundSequencer.setMicrosecondPosition(0);
			}
		} catch (Throwable e) {
			// --
		}
	}

	/**
	 * Stop the background music.
	 */
	public static void stopBackgroundMusic() {
		try {
			if (backgroundSequencer != null && backgroundSequencer.isOpen() && backgroundSequencer.isRunning()) {
				backgroundSequencer.stop();
			}
		} catch (Throwable e) {
			// --
		}
	}

	/**
	 * Release resources.
	 */
	public static void teardown() {
		try {
			if (backgroundSequencer != null) {
				backgroundSequencer.close();
			}
		} catch (Throwable e) {
			// --
		}
	}
}
