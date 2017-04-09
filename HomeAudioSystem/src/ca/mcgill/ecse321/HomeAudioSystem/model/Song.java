/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.HomeAudioSystem.model;

// line 2 "../../../../../HomeAudioSystem.ump"
public class Song
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Song Attributes
  private String title;
  private String duration;
  private int positionInAlbum;

  //Song Associations
  private Album album;
  private Artist artist;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Song(String aTitle, String aDuration, Album aAlbum, Artist aArtist)
  {
    title = aTitle;
    duration = aDuration;
    boolean didAddAlbum = setAlbum(aAlbum);
    if (!didAddAlbum)
    {
      throw new RuntimeException("Unable to create song due to album");
    }
    boolean didAddArtist = setArtist(aArtist);
    if (!didAddArtist)
    {
      throw new RuntimeException("Unable to create song due to artist");
    }
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

  public boolean setDuration(String aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public boolean setPositionInAlbum(int aPositionInAlbum)
  {
    boolean wasSet = false;
    positionInAlbum = aPositionInAlbum;
    wasSet = true;
    return wasSet;
  }

  public String getTitle()
  {
    return title;
  }

  public String getDuration()
  {
    return duration;
  }

  public int getPositionInAlbum()
  {
    return positionInAlbum;
  }

  public Album getAlbum()
  {
    return album;
  }

  public Artist getArtist()
  {
    return artist;
  }

  public boolean setAlbum(Album aAlbum)
  {
    boolean wasSet = false;
    if (aAlbum == null)
    {
      return wasSet;
    }

    Album existingAlbum = album;
    album = aAlbum;
    if (existingAlbum != null && !existingAlbum.equals(aAlbum))
    {
      existingAlbum.removeSong(this);
    }
    album.addSong(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setArtist(Artist aArtist)
  {
    boolean wasSet = false;
    if (aArtist == null)
    {
      return wasSet;
    }

    Artist existingArtist = artist;
    artist = aArtist;
    if (existingArtist != null && !existingArtist.equals(aArtist))
    {
      existingArtist.removeSong(this);
    }
    artist.addSong(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Album placeholderAlbum = album;
    this.album = null;
    placeholderAlbum.removeSong(this);
    Artist placeholderArtist = artist;
    this.artist = null;
    placeholderArtist.removeSong(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "duration" + ":" + getDuration()+ "," +
            "positionInAlbum" + ":" + getPositionInAlbum()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "album = "+(getAlbum()!=null?Integer.toHexString(System.identityHashCode(getAlbum())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "artist = "+(getArtist()!=null?Integer.toHexString(System.identityHashCode(getArtist())):"null")
     + outputString;
  }
}