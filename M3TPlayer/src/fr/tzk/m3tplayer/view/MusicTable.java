package fr.tzk.m3tplayer.view;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import fr.tzk.m3tplayer.controller.M3TPlayer;
import fr.tzk.m3tplayer.model.Music;

/**
 * The music table which displays all the imported musics with their
 * informations and allow the user to interact with.
 */
public class MusicTable extends JTable {

	/**
	 * The table model
	 */
	private final MusicTableModel model;

	/**
	 * Initializes the table and link it with actions.
	 * 
	 * @param player
	 *            a reference to the M3TPlayer
	 */
	public MusicTable(final M3TPlayer player) {
		this.model = new MusicTableModel(player.getLibrary());
		this.setModel(this.model);
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem itemPlay = new JMenuItem("Play");

		itemPlay.addActionListener((event) -> {
			Music music = this.getSelectedMusic();
			if (music != null)
				player.play(music);
		});

		JMenuItem itemPrevious = new JMenuItem("Previous track");
		itemPrevious.addActionListener(e -> {
			player.setCurrentMusic(this.getSelectedMusic());
			player.previous();
		});

		JMenuItem itemNext = new JMenuItem("Next track");
		itemNext.addActionListener(e -> {
			player.setCurrentMusic(this.getSelectedMusic());
			player.next();
		});

		JMenuItem itemUpdate = new JMenuItem("Update informations");
		itemUpdate.addActionListener(e -> {
			new UpdateMusicFrame(this.getSelectedMusic());
		});

		JMenuItem itemDelete = new JMenuItem("Delete music");
		itemDelete.addActionListener(e -> {
			Music selectedMusic = this.getSelectedMusic();
			this.getModel().removeMusic(this.getSelectedRow());
			if (player.getCurrentMusic().equals(selectedMusic)) {
				player.stop(true);
				this.repaint();
			}
		});

		popupMenu.add(itemPlay);
		popupMenu.add(itemPrevious);
		popupMenu.add(itemNext);
		popupMenu.add(itemUpdate);
		popupMenu.add(itemDelete);

		this.setComponentPopupMenu(popupMenu);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mousePressed(e);
				Point point = e.getPoint();
				int currentRow = MusicTable.this.rowAtPoint(point);

				if (e.getClickCount() == 2) {
					MusicTable musicTable = (MusicTable) e.getSource();
					Music musicToPlay = musicTable.getModel().getMusicAt(currentRow);
					player.play(musicToPlay);
				}

				MusicTable.this.setRowSelectionInterval(currentRow, currentRow);
			}
		});

	}

	/**
	 * @return the selected music or <tt>null</tt> if no row is selected.
	 */
	public Music getSelectedMusic() {
		int row = this.getSelectedRow();
		if (row == -1)
			return null;
		return this.getModel().getMusicAt(row);
	}

	/**
	 * @return the model
	 */
	@Override
	public MusicTableModel getModel() {
		return this.model;
	}
}