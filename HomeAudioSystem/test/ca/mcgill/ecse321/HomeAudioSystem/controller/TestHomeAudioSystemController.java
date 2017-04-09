package ca.mcgill.ecse321.HomeAudioSystem.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.HomeAudioSystem.persistence.PersistenceXStream;
import ca.mcgill.ecse321.HomeAudioSystem.model.Album;
import ca.mcgill.ecse321.HomeAudioSystem.model.Artist;
import ca.mcgill.ecse321.HomeAudioSystem.model.HAS;
import ca.mcgill.ecse321.HomeAudioSystem.model.Location;
import ca.mcgill.ecse321.HomeAudioSystem.model.Playlist;
import ca.mcgill.ecse321.HomeAudioSystem.model.Song;

/*
 * JUnits test composed of component testing as well as partition testing 
 * and validation testing
 * 
 */
public class TestHomeAudioSystemController {

	Album album;
	Artist artist;
	Song song;
	Location location;
	Playlist playlist;
	int maxVolume = 30;
	int minVolume = 0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.setFilename("test"+File.separator+"ca"+File.separator+"mcgill"+File.separator+"ecse321"+File.separator+"HomeAudioSystem"+
				File.separator+"controller"+File.separator+"data.xml");
		PersistenceXStream.setAlias("location", Location.class);
		PersistenceXStream.setAlias("album", Album.class);
		PersistenceXStream.setAlias("song", Song.class);
		PersistenceXStream.setAlias("artist", Artist.class);
		PersistenceXStream.setAlias("playlist", Playlist.class);
	}

	@After
	public void tearDown() throws Exception {
		HAS has = HAS.getInstance();
		has.delete();
	}

	@Test
	public void testAddArtists() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getArtists().size());

		String name = "J.S.Bach";

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addArtist(name);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}

		checkResultArtist(name, has);

		HAS has2 = (HAS) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		checkResultArtist(name, has2);
	}

	@Test
	public void testAddArtistNull() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getArtists().size());

		String name = null;

		String error = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addArtist(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Artist name cannot be empty!", error);

		// check no change in memory
		assertEquals(0, has.getArtists().size());
	}

	@Test
	public void testAddArtistEmpty() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getArtists().size());

		String name = "";

		String error = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addArtist(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Artist name cannot be empty!", error);

		//check no change in memory
		assertEquals(0, has.getArtists().size());
	}

	@Test
	public void testAddArtistSpaces() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getArtists().size());

		String name = " ";

		String error = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addArtist(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Artist name cannot be empty!", error);

		//check no change in memory
		assertEquals(0, has.getArtists().size());
	}

	@Test
	public void testAddAlbum() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getAlbums().size());

		String title = "Goldberg Variations BWV 988";
		String genre = "Classic";
		Calendar c = Calendar.getInstance();
		c.set(2015, Calendar.FEBRUARY,17);
		Date date = new Date(c.getTimeInMillis());

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addAlbum(title, genre, date);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}

		// check model in memory
		checkResultAlbum(title, genre, date, has);

		HAS has2 = (HAS) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		checkResultAlbum(title, genre, date, has2);
	}

	@Test
	public void testAddAlbumNull() {
		HAS rm = HAS.getInstance();
		assertEquals(0, rm.getAlbums().size());

		String title = null;
		String genre = null;
		Date date = null;

		String error = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addAlbum(title, genre, date);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Album title cannot be empty! Album genre cannot be empty! Album release date cannot be empty!", error);

		// check model in memory
		assertEquals(0, rm.getAlbums().size());
	}

	@Test
	public void testAddAlbumEmpty() {
		HAS rm = HAS.getInstance();
		assertEquals(0, rm.getAlbums().size());

		String title = "";
		String genre = "";
		Calendar c = Calendar.getInstance();
		c.set(2015, Calendar.FEBRUARY,17);
		Date date = new Date(c.getTimeInMillis());

		String error = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addAlbum(title, genre, date);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Album title cannot be empty! Album genre cannot be empty!", error);

		// check model in memory
		assertEquals(0, rm.getAlbums().size());

	}

	@Test
	public void testAddAlbumSpaces() {
		HAS rm = HAS.getInstance();
		assertEquals(0, rm.getAlbums().size());

		String title = " ";
		String genre = " ";
		Calendar c = Calendar.getInstance();
		c.set(2015, Calendar.FEBRUARY,17);
		Date date = new Date(c.getTimeInMillis());

		String error = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addAlbum(title, genre, date);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Album title cannot be empty! Album genre cannot be empty!", error);

		// check model in memory
		assertEquals(0, rm.getAlbums().size());
	}

	@Test
	public void testAddPlaylist(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getPlaylists().size());

		String name = "If you read this it's too late";

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addPlaylist(name);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}

		checkResultPlaylist(name, has);

		HAS has2 = (HAS) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		checkResultPlaylist(name, has2);
	}

	@Test
	public void testAddPlaylistNull() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getPlaylists().size());

		String name = null;

		String error = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addPlaylist(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Playlist name cannot be empty!", error);

		// check no change in memory
		assertEquals(0, has.getPlaylists().size());
	}

	@Test
	public void testAddPlaylistEmpty() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getPlaylists().size());

		String name = "";

		String error = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addPlaylist(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Playlist name cannot be empty!", error);

		//check no change in memory
		assertEquals(0, has.getArtists().size());
	}

	@Test
	public void testAddPlaylistSpaces() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getArtists().size());

		String name = " ";

		String error = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addPlaylist(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Playlist name cannot be empty!", error);

		//check no change in memory
		assertEquals(0, has.getArtists().size());
	}

	@Test
	public void testAddLocation() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());

		String name = "Kitchen";
		int volume = 20;

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addLocation(name, volume);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}

		checkResultLocation(name, volume, has);

		HAS has2 = (HAS) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		checkResultLocation(name, volume, has2);
	}

	@Test
	public void testAddLocationNull() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());

		String name = null;
		int volume = 20;

		String error = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addLocation(name, volume);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Location name cannot be empty!", error);

		//check no change in memory
		assertEquals(0, has.getLocations().size());
	}

	@Test
	public void testAddLocationEmpty() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());

		String name = "";
		int volume = 20;

		String error = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addLocation(name, volume);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Location name cannot be empty!", error);

		//check no change in memory
		assertEquals(0, has.getLocations().size());
	}

	@Test
	public void testAddLocationSpaces() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());

		String name = " ";
		int volume = 20;

		String error = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addLocation(name, volume);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Location name cannot be empty!", error);

		//check no change in memory
		assertEquals(0, has.getLocations().size());
	}

	@Test
	//Partition testing
	public void testAddLocationMinVolume() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());

		String name = "Kitchen";
		int volume = -1;

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addLocation(name, volume);
		} catch (InvalidInputException e) {
			fail();
		}

		// check volume was adjusted
		assertEquals(minVolume, has.getLocation(0).getVolume());
	}

	@Test
	//Partition testing
	public void testAddLocationMaxVolume() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());

		String name = "Kitchen";
		int volume = 31;

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addLocation(name, volume);
		} catch (InvalidInputException e) {
			fail();
		}

		// check volume was adjusted
		assertEquals(maxVolume, has.getLocation(0).getVolume());

	}

	@Before
	public void setUp() {
		Calendar c = Calendar.getInstance();
		c.set(2015, Calendar.FEBRUARY,17);
		Date releaseDate = new Date(c.getTimeInMillis());

		album = new Album("Get Rich or Die tryin", "Hip Hop", releaseDate);
		artist = new Artist("50 Cent");
		song = new Song("21 Questions", "03:34", album, artist);
		playlist = new Playlist("My Fav Playlist");
		location = new Location("Kitchen");

	}

	@Test
	public void testAddSong() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getSongs().size());

		String songTitle= "Jumpman";
		String duration = "02:30";

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addSong(songTitle, duration, album, artist);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}

		// check model in memory
		checkResultSong(songTitle, duration, album, artist, has);

		HAS has2 = (HAS) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		checkResultSong(songTitle, duration, album, artist, has2);
	}

	@Test
	public void testAddSongNull() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getSongs().size());

		String songTitle= null;
		String duration = null;

		String error = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addSong(songTitle, duration, album, artist);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Song title cannot be empty! Song duration cannot be empty!", error);

		//check no change in memory
		assertEquals(0, has.getSongs().size());
	}

	@Test
	public void testAddSongEmpty() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getSongs().size());

		String songTitle= "";
		String duration = "";

		String error = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addSong(songTitle, duration, album, artist);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Song title cannot be empty! Song duration cannot be empty!", error);

		//check no change in memory
		assertEquals(0, has.getSongs().size());
	}

	@Test
	public void testAddSongSpaces() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getSongs().size());

		String songTitle= " ";
		String duration = " ";

		String error = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addSong(songTitle, duration, album, artist);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Song title cannot be empty! Song duration cannot be empty!", error);

		//check no change in memory
		assertEquals(0, has.getSongs().size());
	}

	@Test
	public void testAddSongWrongFormatDuration() {
		HAS has = HAS.getInstance();
		assertEquals(0, has.getSongs().size());

		String songTitle= "Jumpman";
		String duration = "1";

		String error = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addSong(songTitle, duration, album, artist);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Duration format is not correct!", error);

		//check no change in memory
		assertEquals(0, has.getSongs().size());
	}

	@Test
	public void testFindDefaultAlbumIndex(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getAlbums().size());

		String title = "Goldberg Variations BWV 988";
		String genre = "Classic";
		Calendar c = Calendar.getInstance();
		c.set(2015, Calendar.FEBRUARY,17);
		Date date = new Date(c.getTimeInMillis());

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		int index = -1;
		try {
			hasc.addAlbum(title, genre, date);
			index = hasc.findDefaultAlbumIndex();
		} catch (Exception e) {
			fail();
		}

		assertNotEquals(-1, index);
		assertEquals(1, index);
	}

	@Test
	public void testFindDefaultArtistIndex(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getSongs().size());

		String name = "J.S.Bach";

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		int index = -1;
		try {
			hasc.addArtist(name);
			index = hasc.findDefaultArtistIndex();
		} catch (Exception e) {
			fail();
		}

		assertNotEquals(-1, index);
		assertEquals(1, index);
	}

	@Test
	public void testChangeVolumeLocation(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());

		String name = "Kitchen";
		int volume = 20;

		int newVolume = 25;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addLocation(name, volume);
			hasc.changeVolumeLocation(has.getLocation(0), newVolume, volume);;
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}

		checkResultLocation(name, newVolume, has);

		HAS has2 = (HAS) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		checkResultLocation(name, newVolume, has2);
	}

	@Test
	public void testChangeVolumeLocationNull(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());

		String error = null;
		int volume = 20;

		int newVolume = 25;
		Location location = null;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.changeVolumeLocation(location, newVolume, volume);;
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Location needs to be selected to mute Location", error);
	}

	@Test
	public void testChangeVolumeLocationNonExistingInMemory(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());

		String name = "Kitchen";
		int volume = 20;

		String error = null;
		int newVolume = 25;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addLocation(name, volume);
			hasc.changeVolumeLocation(location, newVolume, volume);;
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Location does not exist!", error);
	}

	@Test
	public void testChangeVolumeLocationMin(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());

		String name = "Kitchen";
		int volume = 20;

		int newVolume = -1;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addLocation(name, volume);
			hasc.changeVolumeLocation(has.getLocation(0), newVolume, volume);;
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}

		//check volume was adjusted
		checkResultLocation(name, minVolume, has);

		HAS has2 = (HAS) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		checkResultLocation(name, minVolume, has2);
	}

	@Test
	public void testChangeVolumeLocationMax(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());

		String name = "Kitchen";
		int volume = 20;

		int newVolume = 31;
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addLocation(name, volume);
			hasc.changeVolumeLocation(has.getLocation(0), newVolume, volume);;
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}

		//check volume was adjusted
		checkResultLocation(name, maxVolume, has);

		HAS has2 = (HAS) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		checkResultLocation(name, maxVolume, has2);
	}

	@Test
	public void testAddSongToPlaylist(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getPlaylists().size());
		assertEquals(0, has.getSongs().size());

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			
			has.addPlaylist(playlist);
			has.addSong(song);

			hasc.addSongToPlaylist(song, playlist);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}

		// check model in memory
		checkResultAddSongToPlaylist(song, playlist, has);

		HAS has2 = (HAS) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		checkResultAddSongToPlaylist(song, playlist, has2);	
	}

	@Test
	public void testAddSongNullToPlaylistNull(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getPlaylists().size());
		assertEquals(0, has.getSongs().size());

		song = null;
		playlist = null;

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		String error = null;
		try {
			hasc.addSongToPlaylist(song, playlist);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Song needs to be selected for adding Song to Playlist! Playlist needs to be selected for adding Song to Playlist!", error);	
	}

	@Test
	public void testAddWrongSongToWrongPlaylist(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getPlaylists().size());
		assertEquals(0, has.getSongs().size());

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		String error = null;
		try {
			hasc.addSongToPlaylist(song, playlist);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Song does not exist! Playlist does not exist!", error);	
	}

	@Test
	public void testAssignSongToLocation(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());
		assertEquals(0, has.getSongs().size());

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			has.addLocation(location);
			has.addSong(song);

			hasc.assignSongToLocation(song, location);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}

		// check model in memory
		checkResultAssignSongToLocation(song, location, has);

		HAS has2 = (HAS) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		checkResultAssignSongToLocation(song, location, has2);		
	}

	@Test
	public void testAssignSongNullToLocationNull(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());
		assertEquals(0, has.getSongs().size());

		song = null;
		location = null;

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		String error = null;
		try {
			hasc.assignSongToLocation(song, location);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Song needs to be selected for assigning Song to Location! Location needs to be selected for assigning Song to Location!", error);	
	}

	@Test
	public void testAssignWrongSongToWrongLocation(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());
		assertEquals(0, has.getSongs().size());

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		String error = null;
		try {
			hasc.assignSongToLocation(song, location);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Song does not exist! Location does not exist!", error);	
	}

	@Test
	public void testAssignAlbumToLocation(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());
		assertEquals(0, has.getAlbums().size());

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			has.addLocation(location);
			has.addAlbum(album);
			album.addSong(song);

			hasc.assignAlbumToLocation(album, location);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}

		// check model in memory
		checkResultAssignAlbumToLocation(album, location, has);

		HAS has2 = (HAS) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		checkResultAssignAlbumToLocation(album, location, has2);		
	}

	@Test
	public void testAssignAlbumEmptyToLocation(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());
		assertEquals(0, has.getAlbums().size());

		String title = "Goldberg Variations BWV 988";
		String genre = "Classic";
		Calendar c = Calendar.getInstance();
		c.set(2015, Calendar.FEBRUARY,17);
		Date date = new Date(c.getTimeInMillis());

		String name = "Kitchen";
		int volume = 25;

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		String error = null;
		try {
			has.addLocation(location);
			
			hasc.addAlbum(title, genre, date);
			album = has.getAlbum(0);

			hasc.assignAlbumToLocation(album, location);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Album is empty!", error);	
	}

	@Test
	public void testAssignAlbumNullToLocationNull(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());
		assertEquals(0, has.getAlbums().size());

		album = null;
		location = null;

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		String error = null;
		try {
			hasc.assignAlbumToLocation(album, location);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Album needs to be selected for assigning album to Location! Location needs to be selected for assigning album to Location!", error);	
	}

	@Test
	public void testAssignWrongAlbumToWrongLocation(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());
		assertEquals(0, has.getAlbums().size());

		album.addSong(song);

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		String error = null;
		try {
			hasc.assignAlbumToLocation(album, location);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Album does not exist! Location does not exist!", error);	
	}

	@Test
	public void testAssignPlaylistToLocation(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());
		assertEquals(0, has.getSongs().size());

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			has.addPlaylist(playlist);
			has.addLocation(location);
			playlist.addSong(song);

			hasc.assignPlaylistToLocation(playlist, location);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}

		// check model in memory
		checkResultAssignPlaylistToLocation(playlist, location, has);

		HAS has2 = (HAS) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		checkResultAssignPlaylistToLocation(playlist, location, has2);		
	}

	@Test
	public void testAssignPlaylistEmptyToLocation(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());
		assertEquals(0, has.getAlbums().size());
		
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		String error = null;
		try {
			has.addLocation(location);
			has.addPlaylist(playlist);

			hasc.assignPlaylistToLocation(playlist, location);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Playlist is Empty!", error);	
	}

	@Test
	public void testAssignPlaylistNullToLocationNull(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());
		assertEquals(0, has.getPlaylists().size());

		playlist = null;
		location = null;

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		String error = null;
		try {
			hasc.assignPlaylistToLocation(playlist, location);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Playlist needs to be selected for assigning playlist to Location! Location needs to be selected for assigning playlist to Location!", error);	
	}

	@Test
	public void testAssignWrongPlaylistToWrongLocation(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getPlaylists().size());
		assertEquals(0, has.getLocations().size());

		playlist.addSong(song);

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		String error = null;
		try {
			hasc.assignPlaylistToLocation(playlist, location);
		} catch (InvalidInputException e) {
			error = e.getMessage();	
		}

		// check error
		assertEquals("Playlist does not exist! Location does not exist!", error);	
	}

	@Test 
	public void testClearLocation(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			has.addLocation(location);
			has.addSong(song);
			has.addAlbum(album);
			has.addPlaylist(playlist);
			
			hasc.addSongToPlaylist(song, playlist);
			
			hasc.assignSongToLocation(song, location);
			hasc.assignAlbumToLocation(album, location);
			hasc.assignPlaylistToLocation(playlist, location);
			hasc.clearLocation(location);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}

		// check model in memory
		assertEquals(null, has.getLocation(0).getSong());
		assertEquals(null, has.getLocation(0).getAlbum());
		assertEquals(null, has.getLocation(0).getPlaylist());
		
		HAS has2 = (HAS) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		assertEquals(null, has2.getLocation(0).getSong());
		assertEquals(null, has2.getLocation(0).getAlbum());
		assertEquals(null, has2.getLocation(0).getPlaylist());
	}

	@Test 
	public void testClearAllLocations(){
		HAS has = HAS.getInstance();
		assertEquals(0, has.getLocations().size());

		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			has.addLocation(location);
			has.addSong(song);
			has.addAlbum(album);
			has.addPlaylist(playlist);
			
			hasc.addSongToPlaylist(song, playlist);
			
			hasc.assignSongToLocation(song, location);
			hasc.assignAlbumToLocation(album, location);
			hasc.assignPlaylistToLocation(playlist, location);
			hasc.clearAllLocations();
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}

		// check model in memory
		assertEquals(null, has.getLocation(0).getSong());
		assertEquals(null, has.getLocation(0).getAlbum());
		assertEquals(null, has.getLocation(0).getPlaylist());
		
		HAS has2 = (HAS) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		assertEquals(null, has2.getLocation(0).getSong());
		assertEquals(null, has2.getLocation(0).getAlbum());
		assertEquals(null, has2.getLocation(0).getPlaylist());
	}


	private void checkResultArtist(String name, HAS has2) {
		assertEquals(1, has2.getArtists().size());
		assertEquals(name, has2.getArtist(0).getName());
		assertEquals(0, has2.getAlbums().size());
		assertEquals(0, has2.getPlaylists().size());
		assertEquals(0, has2.getSongs().size());
		assertEquals(0, has2.getLocations().size());
	}

	private void checkResultAlbum(String title, String genre, Date date, HAS has2) {
		assertEquals(1, has2.getAlbums().size());
		assertEquals(title, has2.getAlbum(0).getTitle());
		assertEquals(date.toString(), has2.getAlbum(0).getReleaseDate().toString());
		assertEquals(0, has2.getSongs().size());
		assertEquals(0, has2.getArtists().size());
		assertEquals(0, has2.getPlaylists().size());
		assertEquals(0, has2.getLocations().size());
	}

	private void checkResultPlaylist(String name, HAS has2) {
		assertEquals(1, has2.getPlaylists().size());
		assertEquals(name, has2.getPlaylist(0).getName());
		assertEquals(0, has2.getSongs().size());
		assertEquals(0, has2.getArtists().size());
		assertEquals(0, has2.getAlbums().size());
		assertEquals(0, has2.getLocations().size());
	}

	private void checkResultLocation(String name, int volume, HAS has2) {
		assertEquals(1, has2.getLocations().size());
		assertEquals(name, has2.getLocation(0).getName());
		assertEquals(volume, has2.getLocation(0).getVolume());
		assertEquals(0, has2.getSongs().size());
		assertEquals(0, has2.getArtists().size());
		assertEquals(0, has2.getPlaylists().size());
		assertEquals(0, has2.getAlbums().size());
	}

	private void checkResultSong(String title, String duration, Album album, Artist artist, HAS has2) {
		assertEquals(1, has2.getSongs().size());
		assertEquals(title, has2.getSong(0).getTitle());
		assertEquals(duration, has2.getSong(0).getDuration());
		assertEquals(album.getTitle(), has2.getSong(0).getAlbum().getTitle());
		assertEquals(artist.getName(), has2.getSong(0).getArtist().getName());	
		assertEquals(2, has2.getSong(0).getPositionInAlbum());
		assertEquals(0, has2.getLocations().size());
		assertEquals(0, has2.getArtists().size());
		assertEquals(0, has2.getPlaylists().size());
		assertEquals(0, has2.getAlbums().size());
	}

	private void checkResultAddSongToPlaylist(Song song, Playlist playlist, HAS has2){
		assertEquals(1, has2.getPlaylists().size());
		assertEquals(1, has2.getPlaylist(0).getSongs().size());
		assertEquals(song.getTitle(), has2.getSong(0).getTitle());	
		assertEquals(song.getTitle(), playlist.getSong(0).getTitle());
	}

	private void checkResultAssignSongToLocation(Song song, Location location, HAS has2) {
		assertEquals(1, has2.getLocations().size());
		assertEquals(1, has2.getSongs().size());
		assertEquals(song.getTitle(), has2.getLocation(0).getSong().getTitle());	
		assertEquals(song.getTitle(), location.getSong().getTitle());
	}

	private void checkResultAssignAlbumToLocation(Album album, Location location, HAS has2) {
		assertEquals(1, has2.getLocations().size());
		assertEquals(1, has2.getAlbums().size());
		assertEquals(album.getTitle(), has2.getLocation(0).getAlbum().getTitle());	
		assertEquals(album.getTitle(), location.getAlbum().getTitle());
	}

	private void checkResultAssignPlaylistToLocation(Playlist playlist, Location location, HAS has2) {
		assertEquals(1, has2.getLocations().size());
		assertEquals(1, has2.getPlaylists().size());
		assertEquals(playlist.getName(), has2.getLocation(0).getPlaylist().getName());	
		assertEquals(playlist.getName(), location.getPlaylist().getName());
	}
}
