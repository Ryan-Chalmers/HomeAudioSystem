<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-3f7df50 modeling language!*/

class Location
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Location Attributes
  private $name;
  private $volume;
  private $beforeMuted;
  private $isPlaying;

  //Location Associations
  private $song;
  private $album;
  private $playlist;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aSong, $aAlbum, $aPlaylist)
  {
    $this->name = $aName;
    $this->isPlaying = false;
    if (!$this->setSong($aSong))
    {
      throw new Exception("Unable to create Location due to aSong");
    }
    if (!$this->setAlbum($aAlbum))
    {
      throw new Exception("Unable to create Location due to aAlbum");
    }
    if (!$this->setPlaylist($aPlaylist))
    {
      throw new Exception("Unable to create Location due to aPlaylist");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function setVolume($aVolume)
  {
    $wasSet = false;
    $this->volume = $aVolume;
    $wasSet = true;
    return $wasSet;
  }

  public function setBeforeMuted($aBeforeMuted)
  {
    $wasSet = false;
    $this->beforeMuted = $aBeforeMuted;
    $wasSet = true;
    return $wasSet;
  }

  public function setIsPlaying($aIsPlaying)
  {
    $wasSet = false;
    $this->isPlaying = $aIsPlaying;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getVolume()
  {
    return $this->volume;
  }

  public function getBeforeMuted()
  {
    return $this->beforeMuted;
  }

  public function getIsPlaying()
  {
    return $this->isPlaying;
  }

  public function getSong()
  {
    return $this->song;
  }

  public function getAlbum()
  {
    return $this->album;
  }

  public function getPlaylist()
  {
    return $this->playlist;
  }

  public function setSong($aNewSong)
  {
    $wasSet = false;
    //if ($aNewSong != null)
    {
      $this->song = $aNewSong;
      $wasSet = true;
   	}
    return $wasSet;
  }

  public function setAlbum($aNewAlbum)
  {
    $wasSet = false;
    //if ($aNewAlbum != null)
    {
      $this->album = $aNewAlbum;
      $wasSet = true;
    }
    return $wasSet;
  }

  public function setPlaylist($aNewPlaylist)
  {
    $wasSet = false;
    //if ($aNewPlaylist != null)
    {
      $this->playlist = $aNewPlaylist;
      $wasSet = true;
    }
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->song = null;
    $this->album = null;
    $this->playlist = null;
  }

}
?>