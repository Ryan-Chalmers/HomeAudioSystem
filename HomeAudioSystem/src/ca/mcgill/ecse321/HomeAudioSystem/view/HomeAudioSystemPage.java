package ca.mcgill.ecse321.HomeAudioSystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse321.HomeAudioSystem.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.HomeAudioSystem.controller.InvalidInputException;
import ca.mcgill.ecse321.HomeAudioSystem.model.Genre;
import ca.mcgill.ecse321.HomeAudioSystem.model.Album;
import ca.mcgill.ecse321.HomeAudioSystem.model.Artist;
import ca.mcgill.ecse321.HomeAudioSystem.model.HAS;
import ca.mcgill.ecse321.HomeAudioSystem.model.Location;
import ca.mcgill.ecse321.HomeAudioSystem.model.Playlist;
import ca.mcgill.ecse321.HomeAudioSystem.model.Song;

public final class HomeAudioSystemPage extends JFrame {
	private static final long serialVersionUID = -8062635784771606869L;

	// UI elements
	private JLabel errorMessage;
	private JComboBox<String> albumList;
	private JLabel albumLabel;

	private JComboBox<Genre> albumGenreTextField;
	private JLabel albumGenreLabel;

	private JTextField albumTitleTextField;
	private JLabel albumTitleLabel;
	private JDatePickerImpl albumDatePicker;
	private JLabel albumDateLabel;
	private JButton addAlbumButton;

	private JComboBox<String> artistList;
	private JLabel artistLabel;
	private JTextField artistNameTextField;
	private JLabel artistNameLabel;
	private JButton addArtistButton;

	private JComboBox<String> songList;
	private JLabel songLabel;
	private JTextField songTitleTextField;
	private JLabel songTitleLabel;
	private JTextField songDurationTextField;
	private JLabel songDurationLabel;
	private JButton addSongButton;

	private JComboBox<String> playlistList;
	private JLabel playlistLabel;
	private JTextField playlistNameTextField;
	private JLabel playlistNameLabel;
	private JButton addPlaylistButton;
	private JButton addSongToPlaylistButton;

	private JComboBox<String> locationList;
	private JLabel locationLabel;
	private JTextField locationNameTextField;
	private JLabel locationNameLabel;
	private JLabel locationVolumeLabel;
	private JSlider locationVolumeSlider;
	private JButton muteButton;
	private JButton unMuteButton;
	private JButton changeVolumeButton;
	private JButton addLocationButton;
	static final int VOL_MIN = 0;
	static final int VOL_MAX = 30;
	static final int VOL_INIT = 15;
	private JPanel mutePanel;

	private JButton clearLocationButton;
	private JButton clearAllLocationButton;
	private JButton assignSongButton;
	private JButton assignAlbumButton;
	private JButton assignPlaylistButton;

	private JPanel playPauseAllPanel;
	private JButton playAllButton;
	private JButton pauseAllButton;
	private JButton pauseButton;

	private JLabel statusLabel;
	private JPanel statusPanel;
	private JScrollPane scroll;
	private JTextArea display;

	private JPanel gapPanel;
	private JTextArea gapArea;

	// data elements
	private String error = null;
	private Integer selectedAlbum = -1;
	private HashMap<Integer, Album> albums;
	private Integer selectedArtist = -1;
	private HashMap<Integer, Artist> artists;
	private Integer selectedSong = -1;
	private HashMap<Integer, Song> songs;
	private Integer selectedPlaylist = -1;
	private HashMap<Integer, Playlist> playlists;
	private Integer selectedLocation = -1;
	private HashMap<Integer, Location> locations;

	/** Creates new form HomeAudioSystemPage */
	public HomeAudioSystemPage() {
		initComponents();
		refreshData();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);

		// elements for creating album
		albumList = new JComboBox<String>(new String[0]);
		albumList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedAlbum = cb.getSelectedIndex();
			}
		});
		albumLabel = new JLabel();

		// elements for creating artist
		artistList = new JComboBox<String>(new String[0]);
		artistList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedArtist = cb.getSelectedIndex();
			}
		});
		artistLabel = new JLabel();

		// elements for creating song
		songList = new JComboBox<String>(new String[0]);
		songList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSong = cb.getSelectedIndex();
			}
		});
		songLabel = new JLabel();

		// elements for creating playlist
		playlistList = new JComboBox<String>(new String[0]);
		playlistList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedPlaylist = cb.getSelectedIndex();
			}
		});
		playlistLabel = new JLabel();

		// elements for creating location

		locationList = new JComboBox<String>(new String[0]);
		locationList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedLocation = cb.getSelectedIndex();
			}
		});
		locationLabel = new JLabel();
		locationVolumeLabel = new JLabel();
		statusLabel = new JLabel();

		// elements for album
		albumTitleTextField = new JTextField();
		albumTitleLabel = new JLabel();

		albumGenreTextField = new JComboBox(Genre.values());
		albumGenreLabel = new JLabel();

		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		albumDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		addAlbumButton = new JButton();
		albumDateLabel = new JLabel();

		// elements for artist
		artistNameTextField = new JTextField();
		artistNameLabel = new JLabel();
		addArtistButton = new JButton();

		// elements for song
		songTitleTextField = new JTextField();
		songTitleLabel = new JLabel();
		songDurationTextField = new JTextField();
		songDurationLabel = new JLabel();
		addSongButton = new JButton();

		// elements for playlist
		playlistNameTextField = new JTextField();
		playlistNameLabel = new JLabel();
		addPlaylistButton = new JButton();
		addSongToPlaylistButton = new JButton();

		// elements for location
		locationNameTextField = new JTextField();
		locationNameLabel = new JLabel();
		locationVolumeSlider = new JSlider(JSlider.HORIZONTAL, VOL_MIN, VOL_MAX, VOL_INIT);
		locationVolumeSlider.setMajorTickSpacing(10);
		locationVolumeSlider.setMinorTickSpacing(1);
		locationVolumeSlider.setPaintTicks(true);
		locationVolumeSlider.setPaintLabels(true);
		muteButton = new JButton();
		unMuteButton = new JButton();
		changeVolumeButton = new JButton();
		mutePanel = new JPanel();
		mutePanel.setLayout(new BorderLayout());
		mutePanel.add(muteButton, BorderLayout.CENTER);
		mutePanel.add(unMuteButton, BorderLayout.EAST);
		assignSongButton = new JButton();
		assignAlbumButton = new JButton();
		assignPlaylistButton = new JButton();
		addLocationButton = new JButton();
		clearLocationButton = new JButton();
		clearAllLocationButton = new JButton();


		// element for play
		playAllButton = new JButton();
		pauseAllButton = new JButton();
		playPauseAllPanel = new JPanel();
		playPauseAllPanel.setLayout(new BorderLayout());
		playPauseAllPanel.add(playAllButton, BorderLayout.WEST);
		playPauseAllPanel.add(pauseAllButton, BorderLayout.EAST);
		pauseButton = new JButton();

		gapPanel = new JPanel();
		gapPanel.setBackground(Color.LIGHT_GRAY);
		gapArea = new JTextArea(2,0);
		gapArea.setEditable(false);
		gapPanel.add(gapArea);

		// element for status
		statusPanel = new JPanel();
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
		statusPanel.setBorder(new TitledBorder(new EtchedBorder(), "HAS Status"));
		statusPanel.add(statusLabel, BorderLayout.CENTER);

		display = new JTextArea(8, 32);
		display.setEditable(false); // set textArea non-editable
		scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// Add Text area in to panel
		statusPanel.add(scroll);

		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("HAS - Home Audio System");

		albumLabel.setText("Select Album:");
		albumTitleLabel.setText("Title:");
		albumGenreLabel.setText("Genre:");
		albumDateLabel.setText("Release Date:");
		addAlbumButton.setText("Add Album");
		addAlbumButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addAlbumButtonActionPerformed(evt);
			}
		});

		artistLabel.setText("Select Artist:");
		artistNameLabel.setText("Name:");
		addArtistButton.setText("Add Artist");
		addArtistButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addArtistButtonActionPerformed(evt);
			}
		});

		songLabel.setText("Select Song:");
		songTitleLabel.setText("Title:");
		songDurationLabel.setText("Duration:");
		addSongButton.setText("Add Song");
		addSongButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addSongButtonActionPerformed(evt);
			}
		});

		playlistLabel.setText("Select Playlist:");
		playlistNameLabel.setText("Name:");
		addPlaylistButton.setText("Add Playlist");
		addPlaylistButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addPlaylistButtonActionPerformed(evt);
			}
		});
		addSongToPlaylistButton.setText("Add Song to Playlist");
		addSongToPlaylistButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addSongToPlaylistButtonActionPerformed(evt);
			}
		});

		locationLabel.setText("Select Location:");
		locationNameLabel.setText("Name:");
		Font font = new Font("Volume", Font.ITALIC, 10);
		locationVolumeSlider.setFont(font);
		locationVolumeLabel.setText("Volume: ");
		muteButton.setText("    Mute    ");
		muteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				muteButtonActionPerformed(evt);
			}
		});
		unMuteButton.setText("   Unmute   ");
		unMuteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				unMuteButtonActionPerformed(evt);
			}
		});
		changeVolumeButton.setText("Change Volume");
		changeVolumeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				changeVolumeActionPerformed(evt);
			}
		});
		addLocationButton.setText("Add Location");
		addLocationButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addLocationButtonActionPerformed(evt);
			}
		});

		assignSongButton.setText("Assign a Song to Location");
		assignSongButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				assignSongButtonActionPerformed(evt);
			}
		});
		assignAlbumButton.setText("Assign an Album to Location");
		assignAlbumButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				assignAlbumButtonActionPerformed(evt);
			}
		});
		assignPlaylistButton.setText("Assign a Playlist to Location");
		assignPlaylistButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				assignPlaylistButtonActionPerformed(evt);
			}
		});
		playAllButton.setText("    Play All    ");
		playAllButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playAllButtonActionPerformed(evt);
			}
		});
		pauseAllButton.setText("   Pause All  ");
		pauseAllButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				pauseAllButtonActionPerformed(evt);
			}
		});

		pauseButton.setText("Play / Pause");
		pauseButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playPauseButtonActionPerformed(evt);
			}
		});
		statusLabel.setText("");
		statusLabel.setForeground(Color.BLUE);

		clearLocationButton.setText("Clear Location");
		clearLocationButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				clearLocationButtonActionPerformed(evt);
			}
		});
		clearAllLocationButton.setText("Clear All Locations");
		clearAllLocationButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				clearAllLocationButtonActionPerformed(evt);
			}
		});


		//		// display information about the selected item
		//		songList.addActionListener(new java.awt.event.ActionListener () {
		//			public void actionPerformed(java.awt.event.ActionEvent evt) {
		//				displayInfo(evt);
		//			}
		//		});


		// layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup().addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(albumLabel)
								.addComponent(albumTitleLabel)
								.addComponent(albumGenreLabel)
								.addComponent(albumDateLabel)
								.addComponent(playlistLabel)
								.addComponent(playlistNameLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(albumList)
								.addComponent(albumTitleTextField, 200, 200, 400)
								.addComponent(albumGenreTextField, 200, 200, 400)
								.addComponent(albumDatePicker)
								.addComponent(addAlbumButton)
								.addComponent(gapPanel)
								.addComponent(playlistList)
								.addComponent(playlistNameTextField, 200, 200, 400)
								.addComponent(addPlaylistButton)
								.addComponent(addSongToPlaylistButton)
								.addComponent(playPauseAllPanel)
								.addComponent(pauseButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(artistLabel)
								.addComponent(artistNameLabel)
								.addComponent(locationLabel)
								.addComponent(locationNameLabel)
								.addComponent(locationVolumeLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(artistList)
								.addComponent(artistNameTextField, 200, 200, 400)
								.addComponent(addArtistButton)
								.addComponent(locationList)
								.addComponent(locationNameTextField, 200, 200, 400)
								.addComponent(locationVolumeSlider)
								.addComponent(mutePanel, 200, 200, 400)
								.addComponent(changeVolumeButton)
								.addComponent(addLocationButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(songLabel)
								.addComponent(songTitleLabel)
								.addComponent(songDurationLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(songList)
								.addComponent(songTitleTextField, 200, 200, 400)
								.addComponent(songDurationTextField, 200, 200, 400)
								.addComponent(addSongButton)
								.addComponent(clearLocationButton)
								.addComponent(clearAllLocationButton)
								.addComponent(assignSongButton)
								.addComponent(assignAlbumButton)
								.addComponent(assignPlaylistButton)))
				.addComponent(statusPanel));

		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { albumLabel, artistLabel, songLabel });
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { albumTitleTextField, albumGenreTextField, addAlbumButton });
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { artistNameTextField, addArtistButton });
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { songTitleTextField, songDurationTextField, addSongButton, clearLocationButton, clearAllLocationButton, assignSongButton, assignAlbumButton, assignPlaylistButton });
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { playlistLabel, locationLabel, locationVolumeLabel });
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { playlistNameTextField, addPlaylistButton, addSongToPlaylistButton, playPauseAllPanel, pauseButton });
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { locationNameTextField,locationVolumeSlider, mutePanel, changeVolumeButton, addLocationButton });

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(albumLabel)
						.addComponent(albumList)
						.addComponent(artistLabel)
						.addComponent(artistList)
						.addComponent(songLabel)
						.addComponent(songList))
				.addGroup(layout.createParallelGroup()
						.addComponent(albumTitleLabel)
						.addComponent(albumTitleTextField)
						.addComponent(artistNameLabel)
						.addComponent(artistNameTextField)
						.addComponent(songTitleLabel)
						.addComponent(songTitleTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(songDurationLabel)
						.addComponent(songDurationTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(albumGenreLabel)
						.addComponent(albumGenreTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(albumDateLabel)
						.addComponent(albumDatePicker))
				.addGroup(layout.createParallelGroup()
						.addComponent(addAlbumButton)
						.addComponent(addArtistButton)
						.addComponent(addSongButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(gapPanel))
				.addGroup(layout.createParallelGroup()
						.addComponent(playlistLabel)
						.addComponent(playlistList)
						.addComponent(locationLabel)
						.addComponent(locationList)
						.addComponent(clearLocationButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(playlistNameLabel)
						.addComponent(playlistNameTextField)
						.addComponent(locationNameLabel)
						.addComponent(locationNameTextField)
						.addComponent(clearAllLocationButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(addPlaylistButton)
						.addComponent(locationVolumeLabel)
						.addComponent(locationVolumeSlider))
				.addGroup(layout.createParallelGroup()
						.addComponent(addSongToPlaylistButton)
						.addComponent(mutePanel)
						.addComponent(assignSongButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(playPauseAllPanel)
						.addComponent(changeVolumeButton)
						.addComponent(assignAlbumButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(pauseButton)
						.addComponent(addLocationButton)
						.addComponent(assignPlaylistButton))
				.addComponent(statusPanel));
		pack();
	}

	private void updateStatus() {
		HAS has = HAS.getInstance();
		HomeAudioSystemController hasc = new HomeAudioSystemController();

		String msg = "";
		String stt = "\nNo music is assigned to the Location: ";
		for (int i = 0; i < has.getLocations().size(); i++) {
			if (has.getLocation(i).getIsPlaying()) {
				if (has.getLocation(i).getSong() != null) {
					msg = msg + "\nPlaying song: " + "\"" + has.getLocation(i).getSong().getTitle() + "\" at the "
							+ has.getLocation(i).getName() + " | Volume: " + has.getLocation(i).getVolume()
							+ " | Total Duration: " + hasc.convertSecondsToTime(has.getLocation(i).getTime());
				}
				if (has.getLocation(i).getAlbum() != null) {
					msg = msg + "\nPlaying album: " + "\"" + has.getLocation(i).getAlbum().getTitle() + "\" at the "
							+ has.getLocation(i).getName() + " | Volume: " + has.getLocation(i).getVolume()
							+ " | Total Duration: " + hasc.convertSecondsToTime(has.getLocation(i).getTime());
				}
				if (has.getLocation(i).getPlaylist() != null) {
					msg = msg + "\nPlaying playlist: " + "\"" + has.getLocation(i).getPlaylist().getName() + "\" at the "
							+ has.getLocation(i).getName() + " | Volume: " + has.getLocation(i).getVolume()
							+ " | Total Duration: " + hasc.convertSecondsToTime(has.getLocation(i).getTime());
				}
				if (has.getLocation(i).getSong() == null && has.getLocation(i).getAlbum() == null
						&& has.getLocation(i).getPlaylist() == null) {
					stt = stt + has.getLocation(i).getName() +", ";
				}
				message(msg);
				messageStatus(stt.substring(0, stt.length() - 2));
			}

			else if (!has.getLocation(i).getIsPlaying()) {
				if (has.getLocation(i).getSong() != null) {
					msg = msg + "\nPaused song: " + "\"" + has.getLocation(i).getSong().getTitle() + "\" at the "
							+ has.getLocation(i).getName() + " | Volume: " + has.getLocation(i).getVolume()
							+ " | Total Duration: " + hasc.convertSecondsToTime(has.getLocation(i).getTime());
				}
				if (has.getLocation(i).getAlbum() != null) {
					msg = msg + "\nPaused album: " + "\"" + has.getLocation(i).getAlbum().getTitle() + "\" at the "
							+ has.getLocation(i).getName() + " | Volume: " + has.getLocation(i).getVolume()
							+ " | Total Duration: " + hasc.convertSecondsToTime(has.getLocation(i).getTime());
				}
				if (has.getLocation(i).getPlaylist() != null) {
					msg = msg + "\nPaused playlist: " + "\"" + has.getLocation(i).getPlaylist().getName() + "\" at the "
							+ has.getLocation(i).getName() + " | Volume: " + has.getLocation(i).getVolume()
							+ " | Total Duration: " + hasc.convertSecondsToTime(has.getLocation(i).getTime());
				}
				if (has.getLocation(i).getSong() == null && has.getLocation(i).getAlbum() == null
						&& has.getLocation(i).getPlaylist() == null) {
					stt = stt + has.getLocation(i).getName() +", ";
				}
				message(msg);
				messageStatus(stt.substring(0, stt.length() - 2));
			}
		}
	}

	private void refreshData() {
		HAS has = HAS.getInstance();

		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			// Album list
			albums = new HashMap<Integer, Album>();
			albumList.removeAllItems();
			Iterator<Album> abIt = has.getAlbums().iterator();
			Integer index = 0;
			while (abIt.hasNext()) {
				Album ab = abIt.next();
				albums.put(index, ab);
				if (ab.getSongs().isEmpty())
					albumList.addItem(ab.getTitle() + " (Empty!)");
				else
					albumList.addItem(ab.getTitle());
				index++;
			}
			selectedAlbum = -1;
			albumList.setSelectedIndex(selectedAlbum);

			// artist list
			artists = new HashMap<Integer, Artist>();
			artistList.removeAllItems();
			Iterator<Artist> aIt = has.getArtists().iterator();
			index = 0;
			while (aIt.hasNext()) {
				Artist a = aIt.next();
				artists.put(index, a);
				if (a.getSongs().isEmpty())
					artistList.addItem(a.getName() + " (Empty!)");
				else
					artistList.addItem(a.getName());
				index++;
			}
			selectedArtist = -1;
			artistList.setSelectedIndex(selectedArtist);

			// song list
			songs = new HashMap<Integer, Song>();
			songList.removeAllItems();
			Iterator<Song> sIt = has.getSongs().iterator();
			index = 0;
			while (sIt.hasNext()) {
				Song s = sIt.next();
				songs.put(index, s);
				songList.addItem(s.getTitle());
				index++;
			}
			selectedSong = -1;
			songList.setSelectedIndex(selectedSong);

			// playlist list
			playlists = new HashMap<Integer, Playlist>();
			playlistList.removeAllItems();
			Iterator<Playlist> plIt = has.getPlaylists().iterator();
			index = 0;
			while (plIt.hasNext()) {
				Playlist pl = plIt.next();
				playlists.put(index, pl);
				if (pl.getSongs().isEmpty())
					playlistList.addItem(pl.getName() + " (Empty!)");
				else
					playlistList.addItem(pl.getName());
				index++;
			}
			selectedPlaylist = -1;
			playlistList.setSelectedIndex(selectedPlaylist);

			// location list
			locations = new HashMap<Integer, Location>();
			locationList.removeAllItems();
			Iterator<Location> lIt = has.getLocations().iterator();
			index = 0;
			while (lIt.hasNext()) {
				Location l = lIt.next();
				locations.put(index, l);
				if (l.getSong() == null && l.getAlbum() == null && l.getPlaylist() == null)
					locationList.addItem(l.getName() + " (Empty!)");
				else if (l.getSong() != null)
					locationList.addItem(l.getName() + " (song assigned!)");
				else if (l.getAlbum() != null)
					locationList.addItem(l.getName() + " (album assigned!)");
				else if (l.getPlaylist() != null)
					locationList.addItem(l.getName() + " (playlist assigned!)");
				else
					locationList.addItem(l.getName());
				index++;
			}
			selectedLocation = -1;
			locationList.setSelectedIndex(selectedLocation);

			updateStatus();

			// album
			albumTitleTextField.setText("");
			albumDatePicker.getModel().setValue(null);
			// artist
			artistNameTextField.setText("");
			// song
			songTitleTextField.setText("");
			songDurationTextField.setText("");
			songDurationTextField.setToolTipText("mm:ss");
			// playlist
			playlistNameTextField.setText("");
			// location
			locationNameTextField.setText("");

		}

		// this is needed because the size of the window change depending on
		// whether an error message is shown or not
		pack();
	}

	private void addAlbumButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// call the controller
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		error = null;
		try {
			hasc.addAlbum(albumTitleTextField.getText(), String.valueOf(albumGenreTextField.getSelectedItem()),
					(java.sql.Date) albumDatePicker.getModel().getValue());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// update visuals
		refreshData();
	}

	private void addArtistButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// call the controller
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		error = null;
		try {
			hasc.addArtist(artistNameTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// update visuals
		refreshData();
	}

	private void addSongButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// call the controller
		HAS has = HAS.getInstance();
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		error = "";
		
		int albumIntex = albumList.getSelectedIndex();
		int artistIndex = artistList.getSelectedIndex();

//		if (albumIntex < 0)
//			error = "Song album cannot be empty! ";
//		if (artistIndex < 0)
//			error = error + "Song artist cannot be empty! ";
			
		if (error.length() == 0) {
			try {
				if(albumIntex<0){
					if (artistIndex<0){
						hasc.addSong(songTitleTextField.getText(), songDurationTextField.getText(),
										null, null);
					}
					else{ 
						hasc.addSong(songTitleTextField.getText(), songDurationTextField.getText(),
										null, has.getArtist(artistIndex));
					}
				}
				else if (artistIndex<0){
					hasc.addSong(songTitleTextField.getText(), songDurationTextField.getText(),
										has.getAlbum(albumIntex), null);
				}
				else{
					hasc.addSong(songTitleTextField.getText(), songDurationTextField.getText(),
							has.getAlbum(albumIntex), has.getArtist(artistIndex));
				}				
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		// update visuals
		refreshData();
	}

	private void addPlaylistButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// call the controller
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		error = "";
		try {
			hasc.addPlaylist(playlistNameTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// update visuals
		refreshData();
	}

	private void muteButtonActionPerformed(java.awt.event.ActionEvent evt) {

		HAS has = HAS.getInstance();
		HomeAudioSystemController hasc = new HomeAudioSystemController();

		error = "";
		if (selectedLocation < 0)
			error = "Location cannot be empty! ";
		if (error.length() == 0) {
			int volume = has.getLocation(locationList.getSelectedIndex()).getVolume();
			try {
				hasc.changeVolumeLocation(has.getLocation(locationList.getSelectedIndex()), 0, volume);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		refreshData();
	}

	private void unMuteButtonActionPerformed(java.awt.event.ActionEvent evt) {

		HAS has = HAS.getInstance();
		HomeAudioSystemController hasc = new HomeAudioSystemController();

		error = "";
		if (selectedLocation < 0)
			error = "Location cannot be empty! ";
		if (error.length() == 0) {
			int volume = has.getLocation(locationList.getSelectedIndex()).getBeforeMuted();
			try {
				hasc.changeVolumeLocation(has.getLocation(locationList.getSelectedIndex()), volume, 0);
			} catch (InvalidInputException e) {
				if (locationList.getSelectedIndex() == -1) {
					error = "Location cannot be empty! ";
				}
			}
		}
		refreshData();
	}

	private void changeVolumeActionPerformed(java.awt.event.ActionEvent evt) {

		HAS has = HAS.getInstance();
		HomeAudioSystemController hasc = new HomeAudioSystemController();

		error = "";
		if (selectedLocation < 0)
			error = "location cannot be empty! ";
		if (error.length() == 0) {
			try {
				hasc.changeVolumeLocation(has.getLocation(locationList.getSelectedIndex()),
						locationVolumeSlider.getValue(), 0);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		refreshData();
	}

	private void addLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// call the controller

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		error = null;

		try {
			hasc.addLocation(locationNameTextField.getText(), locationVolumeSlider.getValue());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// update visuals
		refreshData();
	}

	private void addSongToPlaylistButtonActionPerformed(java.awt.event.ActionEvent evt) {
		HAS has = HAS.getInstance();
		HomeAudioSystemController hasc = new HomeAudioSystemController();

		error = "";
		if (selectedSong < 0)
			error = error + "Song cannot be empty! ";
		if (selectedPlaylist < 0)
			error = error + "Playlist cannot be empty! ";
		error = error.trim();
		if (error.length() == 0) {
			// call the controller
			try {
				hasc.addSongToPlaylist(has.getSong(songList.getSelectedIndex()),
						has.getPlaylist(playlistList.getSelectedIndex()));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		refreshData();
	}

	private void assignSongButtonActionPerformed(java.awt.event.ActionEvent evt) {
		HAS has = HAS.getInstance();
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		error = "";
		if (selectedSong < 0)
			error = error + "Song cannot be empty! ";
		if (selectedLocation < 0)
			error = error + "Location cannot be empty! ";
		error = error.trim();
		if (error.length() == 0) {
			try {
				hasc.assignSongToLocation(has.getSong(songList.getSelectedIndex()),
						has.getLocation(locationList.getSelectedIndex()));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		refreshData();
	}

	private void assignAlbumButtonActionPerformed(java.awt.event.ActionEvent evt) {
		HAS has = HAS.getInstance();
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		error = "";
		if (selectedAlbum < 0)
			error = error + "Album cannot be empty! ";
		if (selectedLocation < 0)
			error = error + "Location cannot be empty! ";
		error = error.trim();
		if (error.length() == 0) {
			try {
				hasc.assignAlbumToLocation(has.getAlbum(albumList.getSelectedIndex()),
						has.getLocation(locationList.getSelectedIndex()));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		refreshData();
	}

	private void assignPlaylistButtonActionPerformed(java.awt.event.ActionEvent evt) {
		HAS has = HAS.getInstance();
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		error = "";
		if (selectedPlaylist < 0)
			error = error + "Playlist cannot be empty! ";
		if (selectedLocation < 0)
			error = error + "Location cannot be empty! ";
		error = error.trim();
		if (error.length() == 0) {
			try {
				hasc.assignPlaylistToLocation(has.getPlaylist(playlistList.getSelectedIndex()),
						has.getLocation(locationList.getSelectedIndex()));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		refreshData();
	}

	private void playAllButtonActionPerformed(java.awt.event.ActionEvent evt) {
		HAS has = HAS.getInstance();
		HomeAudioSystemController hasc = new HomeAudioSystemController();

		error = "";
		if (has.getLocations().isEmpty())
			error = error + "Location is not created in HAS! ";
		error = error.trim();

		if (error.length() == 0) {
			if (playAllButton.getText().contains("Play All")) {
				hasc.playPauseAll(true);
			}
		}
		refreshData();
	}

	private void pauseAllButtonActionPerformed(java.awt.event.ActionEvent evt) {
		HAS has = HAS.getInstance();
		HomeAudioSystemController hasc = new HomeAudioSystemController();

		error = "";
		if (has.getLocations().isEmpty())
			error = error + "Location is not created in HAS! ";
		error = error.trim();

		if (error.length() == 0) {
			if (pauseAllButton.getText().contains("Pause All")){
				hasc.playPauseAll(false);

			}
		}
		refreshData();
	}

	void message(String msg) {
		display.setText(msg);
	}

	void messageStatus(String msg) {
		statusLabel.setText(msg);
	}

	private void playPauseButtonActionPerformed(java.awt.event.ActionEvent evt) {
		HAS has = HAS.getInstance();
		HomeAudioSystemController hasc = new HomeAudioSystemController();

		error = "";
		if (selectedLocation < 0)
			error = error + "Location cannot be empty! ";
		else if (has.getLocation(locationList.getSelectedIndex()).getSong()==null && has.getLocation(locationList.getSelectedIndex()).getAlbum()==null && has.getLocation(locationList.getSelectedIndex()).getPlaylist()==null)
			error = error + "Selected location doesn't have any music assigned!";
		error = error.trim();

		if (error.length() == 0) {
			if (has.getLocation(locationList.getSelectedIndex()).getIsPlaying()) {
				try {
					hasc.playPause(has.getLocation(locationList.getSelectedIndex()), false);
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}
			} else if (!(has.getLocation(locationList.getSelectedIndex()).getIsPlaying())) {
				try {
					hasc.playPause(has.getLocation(locationList.getSelectedIndex()), true);
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}
			}
		}
		refreshData();
	}

	private void clearLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {
		HAS has = HAS.getInstance();
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		error = "";
		if (selectedLocation < 0)
			error = error + "Location cannot be empty! ";
		error = error.trim();
		if (error.length() == 0) {
			try {
				hasc.clearLocation(has.getLocation(locationList.getSelectedIndex()));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		refreshData();
	}

	private void clearAllLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {
		HAS has = HAS.getInstance();
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		error = "";
		if (has.getLocations().isEmpty())
			error = error + "No Location is in HAS! ";
		error = error.trim();

		if (error.length() == 0) {
			hasc.clearAllLocations();
		}
		refreshData();
	}

	//	private void displayInfo(java.awt.event.ActionEvent evt) {
	//		HAS has = HAS.getInstance();
	//
	//		if (selectedSong > -1) {
	//			songTitleTextField.setText(has.getSong(songList.getSelectedIndex()).getTitle());
	//			songDurationTextField.setText(has.getSong(songList.getSelectedIndex()).getDuration());;
	//		}
	//	}

}