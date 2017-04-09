<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-3f7df50 modeling language!*/

class Artist
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Artist Attributes
  private $name;

  //Artist Associations
  private $songs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName)
  {
    $this->name = $aName;
    $this->songs = array();
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

  public function getName()
  {
    return $this->name;
  }

  public function getSong_index($index)
  {
    $aSong = $this->songs[$index];
    return $aSong;
  }

  public function getSongs()
  {
    $newSongs = $this->songs;
    return $newSongs;
  }

  public function numberOfSongs()
  {
    $number = count($this->songs);
    return $number;
  }

  public function hasSongs()
  {
    $has = $this->numberOfSongs() > 0;
    return $has;
  }

  public function indexOfSong($aSong)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->songs as $song)
    {
      if ($song->equals($aSong))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfSongs()
  {
    return 0;
  }

  public function addSongVia($aTitle, $aDuration, $aAlbum)
  {
    return new Song($aTitle, $aDuration, $aAlbum, $this);
  }

  public function addSong($aSong)
  {
    $wasAdded = false;
    if ($this->indexOfSong($aSong) !== -1) { return false; }
    $existingArtist = $aSong->getArtist();
    $isNewArtist = $existingArtist != null && $this !== $existingArtist;
    if ($isNewArtist)
    {
      $aSong->setArtist($this);
    }
    else
    {
      $this->songs[] = $aSong;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSong($aSong)
  {
    $wasRemoved = false;
    //Unable to remove aSong, as it must always have a artist
    if ($this !== $aSong->getArtist())
    {
      unset($this->songs[$this->indexOfSong($aSong)]);
      $this->songs = array_values($this->songs);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addSongAt($aSong, $index)
  {  
    $wasAdded = false;
    if($this->addSong($aSong))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSongs()) { $index = $this->numberOfSongs() - 1; }
      array_splice($this->songs, $this->indexOfSong($aSong), 1);
      array_splice($this->songs, $index, 0, array($aSong));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveSongAt($aSong, $index)
  {
    $wasAdded = false;
    if($this->indexOfSong($aSong) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSongs()) { $index = $this->numberOfSongs() - 1; }
      array_splice($this->songs, $this->indexOfSong($aSong), 1);
      array_splice($this->songs, $index, 0, array($aSong));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addSongAt($aSong, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    foreach ($this->songs as $aSong)
    {
      $aSong->delete();
    }
  }

}
?>