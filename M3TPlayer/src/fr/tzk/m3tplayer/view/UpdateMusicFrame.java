package fr.tzk.m3tplayer.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.tzk.m3tplayer.controller.M3TPlayer;
import fr.tzk.m3tplayer.model.Music;

/**
 * Frame which allows to update informations of a given music.
 */
public class UpdateMusicFrame extends JFrame {

	/**
	 * Initializes the frame and displays the form to update a music.
	 * 
	 * @param music
	 *            the music to update
	 */
	public UpdateMusicFrame(Music music) {
		this.setTitle(M3TPlayer.APP_NAME + " | Updating music");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JLabel labelTitle = new JLabel("Title: ");
		JLabel labelArtist = new JLabel("Artist: ");
		JLabel labelAlbum = new JLabel("Album: ");
		JLabel labelRating = new JLabel("Rating: ");

		JTextField textTitle = new JTextField(music.getTitle(), 20);
		JTextField textArtist = new JTextField(music.getArtist(), 20);
		JTextField textAlbum = new JTextField(music.getAlbum(), 20);
		JTextField textRating = new JTextField(Integer.toString(music.getRating()), 20);

		JButton buttonUpdate = new JButton("Update");
		buttonUpdate.addActionListener(e -> {
			music.setTitle(textTitle.getText());
			music.setArtist(textArtist.getText());
			music.setAlbum(textAlbum.getText());
			music.setRating(Integer.parseInt(textRating.getText()));
			this.dispose();
		});

		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(e -> {
			this.dispose();
		});

		this.setLayout(new BorderLayout());

		JPanel pane = new JPanel();

		pane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		pane.add(labelTitle, gbc);

		gbc.gridx = 1;
		pane.add(textTitle, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		pane.add(labelArtist, gbc);

		gbc.gridx = 1;
		pane.add(textArtist, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		pane.add(labelAlbum, gbc);

		gbc.gridx = 1;
		pane.add(textAlbum, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		pane.add(labelRating, gbc);

		gbc.gridx = 1;
		pane.add(textRating, gbc);

		this.getContentPane().add(pane, BorderLayout.NORTH);
		this.getContentPane().add(buttonUpdate, BorderLayout.CENTER);
		this.getContentPane().add(buttonCancel, BorderLayout.SOUTH);

		this.pack();
		this.setVisible(true);
	}
}
