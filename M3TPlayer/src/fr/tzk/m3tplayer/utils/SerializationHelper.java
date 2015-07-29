package fr.tzk.m3tplayer.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Helper used to ease the Serialization process.
 * 
 * @author TZK
 *
 */
public final class SerializationHelper {

	/**
	 * Serialise a given object into a given file
	 * 
	 * @param object
	 *            the object to serialize
	 * @param file
	 *            the file to serialize the object
	 */
	public static void serialize(Object object, File file) {
		try {

			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			try {
				oos.writeObject(object);
				oos.flush();
			} finally {
				try {
					oos.close();
				} finally {
					fos.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deserialize an object from a given file
	 * 
	 * @param file
	 *            the file to deserialize
	 * @return the deserialized object
	 */
	public static Object deserialize(File file) {
		Object result = null;
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			result = in.readObject();
			in.close();
			fileIn.close();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

}
