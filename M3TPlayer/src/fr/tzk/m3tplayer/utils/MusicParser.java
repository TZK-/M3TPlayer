package fr.tzk.m3tplayer.utils;



import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import fr.tzk.m3tplayer.model.Music;

/**
 * This class is used to parse an audio file and get its informations Tag
 * 
 * @see "https://en.wikipedia.org/wiki/ID3"
 * @author TZK
 *
 */
public final class MusicParser {

	/**
	 * Returns a new Music object ans sets its informations, such as the title,
	 * the artist, album etc.
	 * 
	 * @param path
	 *            the music path
	 * @return a new Music object or <tt>null</tt> if there was a problem to
	 *         parse the file.
	 */
	public static Music getFormattedMusicFromPath(String path) {

		File musicFile = new File(path);
		AudioFile audio = null;

		try {
			audio = AudioFileIO.read(musicFile);
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			e.printStackTrace();
		}

		Tag tag = audio.getTag();

		String title = tag.getFirst(FieldKey.TITLE);
		String artist = tag.getFirst(FieldKey.ARTIST);
		String album = tag.getFirst(FieldKey.ALBUM);
		int lenght = audio.getAudioHeader().getTrackLength();

		if (title.isEmpty())
			title = musicFile.getName().split("\\.")[0];

		if (artist.isEmpty())
			artist = tag.getFirst(FieldKey.ALBUM_ARTIST);

		return new Music(path, title, artist, album, lenght);
	}

}