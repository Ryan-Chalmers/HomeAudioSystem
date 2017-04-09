<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-3f7df50 modeling language!*/

class Song
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Song Attributes
  private $title;
  private $duration;
  private $positionInAlbum;

  //Song Associations
  private $album;
  private $artist;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aTitle, $aDuration, $aAlbum, $aArtist)
  {
    $this->title = $aTitle;
    $this->duration = $aDuration;
    $didAddAlbum = $this->setAlbum($aAlbum);
    if (!$didAddAlbum)
    {
      throw new Exception("Unable to create song due to album");
    }
    $didAddArtist = $this->setArtist($aArtist);
    if (!$didAddArtist)
    {
      throw new Exception("Unable to create song due to artist");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setTitle($aTitle)
  {
    $wasSet = false;
    $this->title = $aTitle;
    $wasSet = true;
    return $wasSet;
  }

  public function setDuration($aDuration)
  {
    $wasSet = false;
    $this->duration = $aDuration;
    $wasSet = true;
    return $wasSet;
  }

  public function setPositionInAlbum($aPositionInAlbum)
  {
    $wasSet = false;
    $this->positionInAlbum = $aPositionInAlbum;
    $wasSet = true;
    return $wasSet;
  }

  public function getTitle()
  {
    return $this->title;
  }

  public function getDuration()
  {
    return $this->duration;
  }

  public function getPositionInAlbum()
  {
    return $this->positionInAlbum;
  }

  public function getAlbum()
  {
    return $this->album;
  }

  public function getArtist()
  {
    return $this->artist;
  }

  public function setAlbum($aAlbum)
  {
    $wasSet = false;
    if ($aAlbum == null)
    {
      return $wasSet;
    }
    
    $existingAlbum = $this->album;
    $this->album = $aAlbum;
    if ($existingAlbum != null && $existingAlbum != $aAlbum)
    {
      $existingAlbum->removeSong($this);
    }
    $this->album->addSong($this);
    $wasSet = true;
    return $wasSet;
  }

  public function setArtist($aArtist)
  {
    $wasSet = false;
    if ($aArtist == null)
    {
      return $wasSet;
    }
    
    $existingArtist = $this->artist;
    $this->artist = $aArtist;
    if ($existingArtist != null && $existingArtist != $aArtist)
    {
      $existingArtist->removeSong($this);
    }
    $this->artist->addSong($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderAlbum = $this->album;
    $this->album = null;
    $placeholderAlbum->removeSong($this);
    $placeholderArtist = $this->artist;
    $this->artist = null;
    $placeholderArtist->removeSong($this);
  }

}
?>