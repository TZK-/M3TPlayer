package fr.tzk.m3tplayer.model;

import java.io.File;
import java.io.Serializable;
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
	 * Imports a given music into the library only if the file exists.
	 *
	 * @param music
	 *            the music to import
	 */
	public void importMusic(Music music) {
		if (music != null && music.getFile().exists())
			this.listMusic.add(music);
	}

	/**
	 * Removes a given music from the library
	 *
	 * @param music
	 *            the music to remove
	 */
	public void removeMusic(Music music) {
		this.listMusic.remove(music);
	}

	/**
	 * Returns the music which matches with a given index
	 * 
	 * @param index
	 *            the index
	 * @return the music or <tt>null</tt> if there has been an error
	 */
	public Music getMusic(int index) {
		try {
			return this.listMusic.get(index);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Returns the music's index stored in the list
	 * 
	 * @param music
	 *            the music
	 * @return the music's index
	 */
	public int getMusicIndex(Music music) {
		return this.listMusic.indexOf(music);
	}

	/**
	 * @return the library size (i.e the number of imported musics)
	 */
	public int getLibrarySize() {
		return this.listMusic.size();
	}

	/**
	 * @return the listMusic
	 */
	public List<Music> getListMusic() {
		return this.listMusic;
	}

}