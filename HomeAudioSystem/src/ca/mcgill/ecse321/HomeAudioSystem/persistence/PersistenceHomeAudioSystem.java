package ca.mcgill.ecse321.HomeAudioSystem.persistence;

import java.util.Iterator;

import ca.mcgill.ecse321.HomeAudioSystem.model.Album;
import ca.mcgill.ecse321.HomeAudioSystem.model.Artist;
import ca.mcgill.ecse321.HomeAudioSystem.model.HAS;
import ca.mcgill.ecse321.HomeAudioSystem.model.Location;
import ca.mcgill.ecse321.HomeAudioSystem.model.Playlist;
import ca.mcgill.ecse321.HomeAudioSystem.model.Song;

public class PersistenceHomeAudioSystem {

	private static String filename = "homeaudiosystem.xml";

	private static void initializeXStream() {
		PersistenceXStream.setFilename(filename);
		PersistenceXStream.setAlias("album", Album.class);
		PersistenceXStream.setAlias("artist", Artist.class);
		PersistenceXStream.setAlias("song", Song.class);
		PersistenceXStream.setAlias("playlist", Playlist.class);
		PersistenceXStream.setAlias("location", Location.class);
		PersistenceXStream.setAlias("audiosystem", HAS.class);
	}
	
	public static void loadHomeAudioSystemModel() {
		HAS has = HAS.getInstance();
		PersistenceHomeAudioSystem.initializeXStream();
		HAS has2 = (HAS) PersistenceXStream.loadFromXMLwithXStream();
		if (has2 != null) {
			Iterator<Album> abIt = has2.getAlbums().iterator();
			while (abIt.hasNext())
				has.addAlbum(abIt.next());
			Iterator<Artist> aIt = has2.getArtists().iterator();
			while (aIt.hasNext())
				has.addArtist(aIt.next());
			Iterator<Song> sIt = has2.getSongs().iterator();
			while (sIt.hasNext())
				has.addSong(sIt.next());
			Iterator<Playlist> plIt = has2.getPlaylists().iterator();
			while (plIt.hasNext())
				has.addPlaylist(plIt.next());
			Iterator<Location> lIt = has2.getLocations().iterator();
			while (lIt.hasNext())
				has.addLocation(lIt.next());
			
		}
	}

	public static void setFileName (String name){
		filename = name;
	}
}
