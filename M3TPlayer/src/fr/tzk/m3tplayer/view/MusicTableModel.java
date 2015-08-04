package fr.tzk.m3tplayer.view;

import javax.swing.table.AbstractTableModel;

import fr.tzk.m3tplayer.model.Library;
import fr.tzk.m3tplayer.model.Music;
import fr.tzk.m3tplayer.utils.TimeHelper;

/**
 * The Model used by the MusicTable. It permits to manage and format music
 * informations into the table.
 */
public class MusicTableModel extends AbstractTableModel {

	/**
	 * The table headers
	 */
	private final static String[] HEADERS = { "Title", "Artist", "Album", "Length", "Rating" };

	/**
	 * The music list
	 */
	private final Library library;

	/**
	 * Creates a new MusicTableModel
	 * 
	 * @param musics
	 *            the music list
	 */
	public MusicTableModel(Library library) {
		this.library = library;
	}

	@Override
	public int getRowCount() {
		return this.library.getListMusic().size();
	}

	@Override
	public int getColumnCount() {
		return HEADERS.length;
	}

	@Override
	public String getColumnName(int column) {
		return HEADERS[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Music music = this.library.getMusic(rowIndex);
		switch (columnIndex) {
			case 0:
				return music.getTitle();
			case 1:
				return music.getArtist();
			case 2:
				return music.getAlbum();
			case 3:
				return TimeHelper.convertSecondToHHMMString(music.getLenght());
			case 4:
				return music.getRating();
			default:
				return null;
		}
	}

	/**
	 * Returns the music at the given row
	 * 
	 * @param rowIndex
	 *            the row
	 * @return the music at the given row
	 */
	public Music getMusicAt(int rowIndex) {
		return this.library.getMusic(rowIndex);
	}

	/**
	 * Adds the given music into the table
	 * 
	 * @param music
	 *            the music to add
	 */
	public void addMusic(Music music) {
		this.library.importMusic(music);
		int lastInsertedMusicIndex = this.library.getListMusic().size() - 1;
		this.fireTableRowsInserted(lastInsertedMusicIndex, lastInsertedMusicIndex);
	}

	/**
	 * Removes the given music from the table
	 * 
	 * @param rowIndex
	 *            the row to remove
	 */
	public void removeMusic(int rowIndex) {
		this.library.removeMusic(this.getMusicAt(rowIndex));
		this.fireTableRowsDeleted(rowIndex, rowIndex);
	}

}
