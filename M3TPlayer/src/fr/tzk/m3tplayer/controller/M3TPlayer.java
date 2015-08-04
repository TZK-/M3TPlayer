package fr.tzk.m3tplayer.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import fr.tzk.m3tplayer.enumerations.PlayerAction;
import fr.tzk.m3tplayer.interfaces.PlayerInteraction;
import fr.tzk.m3tplayer.model.Library;
import fr.tzk.m3tplayer.model.Music;
import fr.tzk.m3tplayer.utils.MusicParser;

/**
 * This class is a wrapper of JLayer Javazoom AdavancedPlayer.
 */
public class M3TPlayer {

	/**
	 * The application's name
	 */
	public final static String APP_NAME = "M3TPlayer";

	/**
	 * The JLayer Javazoom player
	 */
	private AdvancedPlayer player;

	/**
	 * The player's thread used to play music
	 */
	private PlayerThread thread;

	/**
	 * The library
	 */
	private final Library library;

	/**
	 * The current music. At the start of the application, it will set to
	 * <tt>null</tt> or to the first music stored into the library (if there is
	 * one).
	 */
	private Music currentMusic;

	/**
	 * The player state. <tt>true</tt> if the player is running a media.
	 */
	private boolean isPlaying;

	/**
	 * The PlayerInteraction interface
	 */
	private final PlayerInteraction playerInteraction;

	/**
	 * Initiliazes the player and loads the library.
	 */
	public M3TPlayer(PlayerInteraction playerInteraction) {
		this.player = null;
		this.thread = null;
		this.library = new Library();
		this.playerInteraction = playerInteraction;

		this.library.importMusic(MusicParser.getFormattedMusicFromPath("c.mp3"));
		this.library.importMusic(MusicParser.getFormattedMusicFromPath("b.mp3"));
		this.library.importMusic(MusicParser.getFormattedMusicFromPath("a.mp3"));

		this.isPlaying = false;

		if (this.library.getLibrarySize() == 0)
			this.currentMusic = null;
		else
			this.currentMusic = this.library.getMusic(0);
	}

	/**
	 * Plays the current music
	 */
	public void play() {
		if (this.isPlaying)
			this.stop(false);
		InputStream is;
		try {
			is = new FileInputStream(this.currentMusic.getFile());
			this.isPlaying = true;
			this.player = new AdvancedPlayer(is);
			this.player.setPlayBackListener(new PlaybackListener() {

				@Override
				public void playbackFinished(PlaybackEvent e) {
					super.playbackFinished(e);
					M3TPlayer.this.next();
				}

				@Override
				public void playbackStarted(PlaybackEvent e) {
					M3TPlayer.this.playerInteraction.changeTitle(M3TPlayer.this.currentMusic);
				}

			});
			this.thread = new PlayerThread();
			this.thread.start();
		} catch (FileNotFoundException | JavaLayerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Plays a given music
	 * 
	 * @param music
	 *            the music to play
	 */
	public void play(Music music) {
		this.setCurrentMusic(music);
		this.play();
	}

	/**
	 * Changes the music and set the next one.
	 */
	public void next() {
		this.skip(PlayerAction.NEXT);
	}

	/**
	 * Changes the music and set the previous one.
	 */
	public void previous() {
		this.skip(PlayerAction.PREVIOUS);
	}

	/**
	 * Change the music (previous or next). If the music is the first one and
	 * the action is to move backward, the player will be stopped. Same if the
	 * music is the last one and the action is to move forward.
	 * 
	 * @param action
	 *            the action <tt>NEXT</tt> or <tt>PREVIOUS</tt>
	 */
	public void skip(PlayerAction action) {
		int musicIndex = this.library.getMusicIndex(this.currentMusic);
		switch (action) {
			case NEXT:
				musicIndex++;
				break;
			case PREVIOUS:
				musicIndex--;
				break;
			default:
				return;
		}
		if (musicIndex < 0 || musicIndex >= this.library.getLibrarySize()) {
			this.stop(true);
			return;
		}
		this.setCurrentMusic(this.library.getMusic(musicIndex));
		this.play();
	}

	/**
	 * Stops the player.
	 * 
	 * @param setFirstMusic
	 *            if setFirstMusic is set to <tt>true</tt>, it will set the
	 *            current music to the default one, i.e the first of the
	 *            library.
	 */
	public void stop(boolean setFirstMusic) {
		if (!this.isPlaying)
			return;
		if (setFirstMusic)
			this.setCurrentMusic(this.library.getMusic(0));
		this.player.close();
		this.thread.close();
		this.isPlaying = false;
		this.playerInteraction.changeTitle(null);
	}

	public boolean isPlaying() {
		return this.isPlaying;
	}

	/**
	 * @return the library
	 */
	public Library getLibrary() {
		return this.library;
	}

	/**
	 * @return the currentMusic
	 */
	public Music getCurrentMusic() {
		return this.currentMusic;
	}

	/**
	 * Sets the current music.
	 * 
	 * @param currentMusic
	 *            the currentMusic to set
	 */
	public void setCurrentMusic(Music currentMusic) {
		this.currentMusic = currentMusic;
	}

	/**
	 * The player thread class
	 */
	private final class PlayerThread extends Thread {

		private boolean close = false;

		public PlayerThread() {
			super("PlayerThread");
		}

		public void close() {
			this.close = true;
		}

		@Override
		public void run() {
			while (!this.close) {
				try {
					M3TPlayer.this.player.play();
				} catch (JavaLayerException e) {
					this.close();
				}
			}
		}
	}
}
