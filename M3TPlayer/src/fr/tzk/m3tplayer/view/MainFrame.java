package fr.tzk.m3tplayer.view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.tzk.m3tplayer.controller.M3TPlayer;
import fr.tzk.m3tplayer.interfaces.PlayerInteraction;
import fr.tzk.m3tplayer.model.Music;

public class MainFrame extends JFrame implements Runnable, PlayerInteraction {

	private static final String APP_NAME = "M3TPlayer";

	private final ControllerPanel controllerPanel;

	private final MusicTable musicTable;

	private final M3TPlayer player;

	public MainFrame() {
		this.player = new M3TPlayer(this);
		this.controllerPanel = new ControllerPanel(this.player, this.musicTable);
		this.musicTable = new MusicTable(this.player);
	}

	private void initFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(APP_NAME);
		this.setSize(800, 600);

		JPanel root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(this.musicTable);

		root.add(this.controllerPanel);
		root.add(scrollPane);

		this.getContentPane().add(root);

		this.pack();
		this.setVisible(true);
	}

	@Override
	public void run() {
		this.initFrame();
	}

	@Override
	public void changeTitle(Music music) {
		if (music == null)
			this.setTitle(M3TPlayer.APP_NAME);
		else
			this.setTitle(M3TPlayer.APP_NAME + " | " + music.getTitle());
	}
}
