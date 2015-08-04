package fr.tzk.m3tplayer.model;

import java.io.File;
import java.io.Serializable;

/**
 * This class represents a music file. A music is defined by its path on the
 * hard drive, its title, its artist and ablum plus an additionnal rating.
 *
 * @author TZK
 * @version 1.0.2
 *
 */
public class Music implements Serializable {

	/**
	 * The file path
	 */
	private final File file;

	/**
	 * The title
	 */
	private String title;

	/**
	 * The artist
	 */
	private String artist;

	/**
	 * The album name
	 */
	private String album;

	/**
	 * The rating. It is a value between 0 and 10.
	 */
	private int rating;

	/**
	 * The lenght of the music in miliseconds
	 */
	private final int lenght;

	/**
	 * Creates a new Music without giving artist and album informations
	 *
	 * @param path
	 *            the path
	 * @param title
	 *            the title
	 */
	public Music(String path, String title) {
		this(path, title, null, null, 0);
	}

	/**
	 * Creates a new Music and set its rating to 0
	 *
	 * @param path
	 *            the path
	 * @param title
	 *            the title
	 * @param artist
	 *            the artist
	 * @param album
	 *            the album
	 * @param album
	 *            the album
	 */
	public Music(String path, String title, String artist, String album, int lenght) {
		this.file = new File(path);
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.lenght = lenght;
		this.rating = 0;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return this.file;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return this.artist;
	}

	/**
	 * @param artist
	 *            the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @return the album
	 */
	public String getAlbum() {
		return this.album;
	}

	/**
	 * @param album
	 *            the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * @return the lenght
	 */
	public int getLenght() {
		return this.lenght;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return this.rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.album == null) ? 0 : this.album.hashCode());
		result = prime * result + ((this.artist == null) ? 0 : this.artist.hashCode());
		result = prime * result + this.lenght;
		result = prime * result + ((this.file == null) ? 0 : this.file.hashCode());
		result = prime * result + this.rating;
		result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Music other = (Music) obj;
		if (this.album == null) {
			if (other.album != null)
				return false;
		} else if (!this.album.equals(other.album))
			return false;
		if (this.artist == null) {
			if (other.artist != null)
				return false;
		} else if (!this.artist.equals(other.artist))
			return false;
		if (this.lenght != other.lenght)
			return false;
		if (this.file == null) {
			if (other.file != null)
				return false;
		} else if (!this.file.equals(other.file))
			return false;
		if (this.rating != other.rating)
			return false;
		if (this.title == null) {
			if (other.title != null)
				return false;
		} else if (!this.title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Music [path=" + this.file + ", title=" + this.title + ", artist=" + this.artist + ", album="
				+ this.album + ", rating=" + this.rating + ", lenght=" + this.lenght + "]";
	}

}