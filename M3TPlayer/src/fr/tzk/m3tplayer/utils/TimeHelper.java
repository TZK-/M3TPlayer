package fr.tzk.m3tplayer.utils;

import java.time.LocalTime;

/**
 * This helper is used to manage, convert and format time values
 */
public class TimeHelper {

	/**
	 * Convert a second time into a Hours:Minutes:Seconds formated string.
	 * 
	 * @param secondtTime
	 *            the second time
	 * @return the formated string
	 */
	public static String convertSecondToHHMMString(int secondtTime) {
		return LocalTime.MIN.plusSeconds(secondtTime).toString();
	}

}
