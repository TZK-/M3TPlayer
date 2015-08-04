package fr.tzk.m3tplayer.interfaces;

import fr.tzk.m3tplayer.model.Music;

/**
 * Interface which abstract the interactions with the player.
 */
public interface PlayerInteraction {

	/**
	 * Sets the music title
	 * 
	 * @param music
	 *            the music
	 */
	public void changeTitle(Music music);

}
