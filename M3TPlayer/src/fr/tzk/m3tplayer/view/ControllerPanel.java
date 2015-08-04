package fr.tzk.m3tplayer.view;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import fr.tzk.m3tplayer.controller.M3TPlayer;
import fr.tzk.m3tplayer.model.Music;
import fr.tzk.m3tplayer.utils.MusicParser;

/**
 * The controller panel which is used to interact with the player with buttons.
 */
public class ControllerPanel extends JPanel {

	private JButton bPlay;

	private JButton bStop;

	private JButton bPrevious;

	private JButton bNext;

	private JButton bImport;

	/**
	 * Initializes the controller panel and display all the buttons.
	 * 
	 * @param player
	 *            a reference to the M3TPlayer
	 * @param table
	 *            the MusicTable
	 */
	public ControllerPanel(final M3TPlayer player, final MusicTable table) {

		this.bPlay = new JButton("Play");
		this.bPlay.addActionListener((event) -> {
			this.bStop.setEnabled(true);
			player.play();
		});

		this.bStop = new JButton("Stop");
		this.bStop.setEnabled(false);
		this.bStop.addActionListener((event) -> {
			player.stop(true);
			this.bStop.setEnabled(false);
		});

		this.bPrevious = new JButton("Previous");
		this.bPrevious.addActionListener((event) -> player.previous());

		this.bNext = new JButton("Next");
		this.bNext.addActionListener((event) -> player.next());

		this.bImport = new JButton("Import");
		this.bImport.addActionListener(e -> {
			String userHome = System.getProperty("user.home");
			JFileChooser fileChooser = new JFileChooser(userHome + File.separator + "Music");
			int returnVal = fileChooser.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String selectedFile = fileChooser.getSelectedFile().toString();
				Music importedMusic = MusicParser.getFormattedMusicFromPath(selectedFile);
				table.getModel().addMusic(importedMusic);
			}
		});

		this.add(this.bPrevious);
		this.add(this.bPlay);
		this.add(this.bStop);
		this.add(this.bNext);
		this.add(this.bImport);
	}
}
