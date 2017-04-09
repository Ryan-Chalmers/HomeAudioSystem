/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.HomeAudioSystem.model;
import java.sql.Date;
import java.util.*;

// line 9 "../../../../../HomeAudioSystem.ump"
public class Album
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Album Attributes
  private String title;
  private String genre;
  private Date releaseDate;

  //Album Associations
  private List<Song> songs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Album(String aTitle, String aGenre, Date aReleaseDate)
  {
    title = aTitle;
    genre = aGenre;
    releaseDate = aReleaseDate;
    songs = new ArrayList<Song>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setGenre(String aGenre)
  {
    boolean wasSet = false;
    genre = aGenre;
    wasSet = true;
    return wasSet;
  }

  public boolean setReleaseDate(Date aReleaseDate)
  {
    boolean wasSet = false;
    releaseDate = aReleaseDate;
    wasSet = true;
    return wasSet;
  }

  public String getTitle()
  {
    return title;
  }

  public String getGenre()
  {
    return genre;
  }

  public Date getReleaseDate()
  {
    return releaseDate;
  }

  public Song getSong(int index)
  {
    Song aSong = songs.get(index);
    return aSong;
  }

  public List<Song> getSongs()
  {
    List<Song> newSongs = Collections.unmodifiableList(songs);
    return newSongs;
  }

  public int numberOfSongs()
  {
    int number = songs.size();
    return number;
  }

  public boolean hasSongs()
  {
    boolean has = songs.size() > 0;
    return has;
  }

  public int indexOfSong(Song aSong)
  {
    int index = songs.indexOf(aSong);
    return index;
  }

  public static int minimumNumberOfSongs()
  {
    return 0;
  }

  public Song addSong(String aTitle, String aDuration, Artist aArtist)
  {
    return new Song(aTitle, aDuration, this, aArtist);
  }

  public boolean addSong(Song aSong)
  {
    boolean wasAdded = false;
    if (songs.contains(aSong)) { return false; }
    Album existingAlbum = aSong.getAlbum();
    boolean isNewAlbum = existingAlbum != null && !this.equals(existingAlbum);
    if (isNewAlbum)
    {
      aSong.setAlbum(this);
    }
    else
    {
      songs.add(aSong);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSong(Song aSong)
  {
    boolean wasRemoved = false;
    //Unable to remove aSong, as it must always have a album
    if (!this.equals(aSong.getAlbum()))
    {
      songs.remove(aSong);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSongAt(Song aSong, int index)
  {  
    boolean wasAdded = false;
    if(addSong(aSong))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSongs()) { index = numberOfSongs() - 1; }
      songs.remove(aSong);
      songs.add(index, aSong);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSongAt(Song aSong, int index)
  {
    boolean wasAdded = false;
    if(songs.contains(aSong))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSongs()) { index = numberOfSongs() - 1; }
      songs.remove(aSong);
      songs.add(index, aSong);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSongAt(aSong, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=songs.size(); i > 0; i--)
    {
      Song aSong = songs.get(i - 1);
      aSong.delete();
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "genre" + ":" + getGenre()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "releaseDate" + "=" + (getReleaseDate() != null ? !getReleaseDate().equals(this)  ? getReleaseDate().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}