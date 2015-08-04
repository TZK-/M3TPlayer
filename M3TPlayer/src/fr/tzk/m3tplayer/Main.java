package fr.tzk.m3tplayer;

import javax.swing.SwingUtilities;

import fr.tzk.m3tplayer.view.MainFrame;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new MainFrame());
	}

}
