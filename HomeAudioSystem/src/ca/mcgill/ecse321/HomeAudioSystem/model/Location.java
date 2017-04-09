/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.HomeAudioSystem.model;
import java.sql.Date;

// line 30 "../../../../../HomeAudioSystem.ump"
public class Location
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Location Attributes
	private String name;
	private int volume;
	private int beforeMuted;
	private boolean isPlaying;
	private int time;

	//Location Associations
	private Song song;
	private Album album;
	private Playlist playlist;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Location(String aName)
	{
		name = aName;
	}

	//------------------------
	// INTERFACE
	//------------------------

	public boolean setName(String aName)
	{
		boolean wasSet = false;
		name = aName;
		wasSet = true;
		return wasSet;
	}

	public boolean setVolume(int aVolume)
	{
		if(aVolume<0){
			aVolume = 0;
		}
		if(aVolume>30){
			aVolume = 30;
		}
		boolean wasSet = false;
		volume = aVolume;
		wasSet = true;
		return wasSet;
	}

	public boolean setBeforeMuted(int aBeforeMuted)
	{
		boolean wasSet = false;
		beforeMuted = aBeforeMuted;
		wasSet = true;
		return wasSet;
	}

	public boolean setIsPlaying(boolean aIsPlaying)
	{
		boolean wasSet = false;
		isPlaying = aIsPlaying;
		wasSet = true;
		return wasSet;
	}

	public boolean setTime(int aTime)
	{
		boolean wasSet = false;
		time = aTime;
		wasSet = true;
		return wasSet;
	}

	public String getName()
	{
		return name;
	}

	public int getVolume()
	{
		return volume;
	}

	public int getBeforeMuted()
	{
		return beforeMuted;
	}

	public boolean getIsPlaying()
	{
		return isPlaying;
	}

	public int getTime()
	{
		return time;
	}

	public Song getSong()
	{
		return song;
	}

	public boolean hasSong()
	{
		boolean has = song != null;
		return has;
	}

	public Album getAlbum()
	{
		return album;
	}

	public boolean hasAlbum()
	{
		boolean has = album != null;
		return has;
	}

	public Playlist getPlaylist()
	{
		return playlist;
	}

	public boolean hasPlaylist()
	{
		boolean has = playlist != null;
		return has;
	}

	public boolean setSong(Song aNewSong)
	{
		boolean wasSet = false;
		song = aNewSong;
		wasSet = true;
		return wasSet;
	}

	public boolean setAlbum(Album aNewAlbum)
	{
		boolean wasSet = false;
		album = aNewAlbum;
		wasSet = true;
		return wasSet;
	}

	public boolean setPlaylist(Playlist aNewPlaylist)
	{
		boolean wasSet = false;
		playlist = aNewPlaylist;
		wasSet = true;
		return wasSet;
	}

	public void delete()
	{
		song = null;
		album = null;
		playlist = null;
	}


	public String toString()
	{
		String outputString = "";
		return super.toString() + "["+
		"name" + ":" + getName()+ "," +
		"volume" + ":" + getVolume()+ "," +
		"beforeMuted" + ":" + getBeforeMuted()+ "," +
		"isPlaying" + ":" + getIsPlaying()+ "," +
		"time" + ":" + getTime()+ "]" + System.getProperties().getProperty("line.separator") +
		"  " + "song = "+(getSong()!=null?Integer.toHexString(System.identityHashCode(getSong())):"null") + System.getProperties().getProperty("line.separator") +
		"  " + "album = "+(getAlbum()!=null?Integer.toHexString(System.identityHashCode(getAlbum())):"null") + System.getProperties().getProperty("line.separator") +
		"  " + "playlist = "+(getPlaylist()!=null?Integer.toHexString(System.identityHashCode(getPlaylist())):"null")
		+ outputString;
	}
}