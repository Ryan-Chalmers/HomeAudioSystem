package ca.mcgill.ecse321.HomeAudioSystem.persistence;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.HomeAudioSystem.persistence.PersistenceXStream;
import ca.mcgill.ecse321.HomeAudioSystem.model.Album;
import ca.mcgill.ecse321.HomeAudioSystem.model.Artist;
import ca.mcgill.ecse321.HomeAudioSystem.model.HAS;
import ca.mcgill.ecse321.HomeAudioSystem.model.Location;
import ca.mcgill.ecse321.HomeAudioSystem.model.Playlist;
import ca.mcgill.ecse321.HomeAudioSystem.model.Song;

public class TestPersistence {

	@Before
	public void setUp() throws Exception {
		HAS has = HAS.getInstance();

		// create location
		Location lt = new Location("Living Room");

		// create Album
		Calendar c = Calendar.getInstance();
		c.set(2015, Calendar.FEBRUARY,17);
		Date releaseDate = new Date(c.getTimeInMillis());
		Album album = new Album("Goldberg Variations BWV 988", "Classic", releaseDate);

		// create Artist
		Artist artist = new Artist("J.S.Bach");

		// create Song

		String duration = "02:33";
		Song song = new Song("Aria", duration, album, artist);

		// create Playlist 
		Playlist pl = new Playlist("playlist");

		// manage HAS
		has.addAlbum(album);
		has.addArtist(artist);
		has.addSong(song);
		has.addPlaylist(pl);
		has.addLocation(lt);


	}

	@After
	public void tearDown() throws Exception {
		// clear all 
		HAS has = HAS.getInstance();
		has.delete();
	}

	@Test
	public void test() {
		// save model
		HAS has = HAS.getInstance();
		PersistenceXStream.setFilename("test"+File.separator+"ca"+File.separator+"mcgill"+File.separator+"ecse321"+File.separator+"HomeAudioSystem"+
				File.separator+"persistence"+File.separator+"data.xml");
		PersistenceXStream.setAlias("location", Location.class);
		PersistenceXStream.setAlias("album", Album.class);
		PersistenceXStream.setAlias("song", Song.class);
		PersistenceXStream.setAlias("artist", Artist.class);
		PersistenceXStream.setAlias("playlist", Playlist.class);
		if (!PersistenceXStream.saveToXMLwithXStream(has))
			fail("Could not save file");

		// clear the model in memory
		has.delete();
		assertEquals(0, has.getAlbums().size());
		assertEquals(0, has.getSongs().size());	
		assertEquals(0, has.getArtists().size());
		assertEquals(0, has.getPlaylists().size());
		assertEquals(0, has.getLocations().size());

		// load model
		has = (HAS) PersistenceXStream.loadFromXMLwithXStream();
		if (has == null)
			fail("Could not load file.");

		//check Location
		assertEquals(1, has.getLocations().size());
		assertEquals("Living Room", has.getLocation(0).getName());
		assertEquals(0, has.getLocation(0).getVolume());

		// check Artist
		assertEquals(1, has.getArtists().size());
		assertEquals("J.S.Bach", has.getArtist(0).getName());

		// check Song
		assertEquals(1, has.getSongs().size());
		assertEquals("Aria", has.getSong(0).getTitle());

		// check Album
		assertEquals(1, has.getAlbums().size());
		assertEquals("Goldberg Variations BWV 988", has.getAlbum(0).getTitle());
		assertEquals("Classic", has.getAlbum(0).getGenre());
		Calendar c = Calendar.getInstance();
		c.set(2015, Calendar.FEBRUARY,17);
		Date releaseDate = new Date(c.getTimeInMillis());
		assertEquals(releaseDate.toString(), has.getAlbum(0).getReleaseDate().toString());


		// check HAS
		assertEquals(1, has.getPlaylists().size());
		//				assertEquals(has.getAlbum(0), has.getPlaylist(0).getAlbum());


	}

}
