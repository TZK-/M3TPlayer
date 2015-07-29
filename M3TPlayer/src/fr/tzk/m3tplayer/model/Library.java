package fr.tzk.m3tplayer.model;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import fr.tzk.m3tplayer.utils.SerializationHelper;

public class Library implements Serializable {

	/**
	 * The library file path on the hard drive. By default, it is set to the
	 * root folder of the application
	 */
	private final static File LIB_FILE = new File("library.m3t");

	/*
	 * List of imported musics
	 */
	private List<Music> listMusic;

	/**
	 * Creates or loads the library.
	 */
	public Library() {
		this.listMusic = new ArrayList<>();


		if (LIB_FILE.exists()) {
			Library lib = (Library) SerializationHelper.deserialize(LIB_FILE);
			this.listMusic = lib.listMusic;
		}
	}

	/**
	 * Saves the library into the library file
	 */
	public void save() {
		SerializationHelper.serialize(this, LIB_FILE);
	}

	/**
	 * Returns the Music object (stored into the ArrayList) by given its path
	 * 
	 * @param path
	 *            the path
	 * @return the Music object, or <tt>null</tt> if the path does not match
	 *         with a Music
	 */
	public Music getMusicFromPath(String path) {
		File file = new File(path);
		URL url = null;
		try {
			url = new URL(file.toURI().toURL(), path);
			for (Music music : this.listMusic) {
				if (music.getFile().toURI().toURL().equals(url))
					return music;
			}
		} catch (MalformedURLException e) {
			return null;
		}

		return null;
	}

	/**
	 * Imports a given music into the library
	 *
	 * @param music
	 *            the music to import
	 */
	public void importMusic(Music music) {
		this.listMusic.add(music);
	}

	/**
	 * Removes a given music from the library
	 *
	 * @param music
	 */
	public void removeMusic(Music music) {
		this.listMusic.remove(music);
	}

	/**
	 * @return the listMusic
	 */
	public List<Music> getListMusic() {
		return this.listMusic;
	}

}