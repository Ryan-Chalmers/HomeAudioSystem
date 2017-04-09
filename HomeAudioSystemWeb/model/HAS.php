<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-3f7df50 modeling language!*/

class HAS
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HAS Associations
  private $songs;
  private $albums;
  private $artists;
  private $playlists;
  private $locations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private function __construct()
  {
    $this->songs = array();
    $this->albums = array();
    $this->artists = array();
    $this->playlists = array();
    $this->locations = array();
  }

  public static function getInstance()
  {
    if(self::$theInstance == null)
    {
      self::$theInstance = new HAS();
    }
    return self::$theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public function getAlbum_index($index)
  {
    $aAlbum = $this->albums[$index];
    return $aAlbum;
  }

  public function getAlbums()
  {
    $newAlbums = $this->albums;
    return $newAlbums;
  }

  public function numberOfAlbums()
  {
    $number = count($this->albums);
    return $number;
  }

  public function hasAlbums()
  {
    $has = $this->numberOfAlbums() > 0;
    return $has;
  }

  public function indexOfAlbum($aAlbum)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->albums as $album)
    {
      if ($album->equals($aAlbum))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getArtist_index($index)
  {
    $aArtist = $this->artists[$index];
    return $aArtist;
  }

  public function getArtists()
  {
    $newArtists = $this->artists;
    return $newArtists;
  }

  public function numberOfArtists()
  {
    $number = count($this->artists);
    return $number;
  }

  public function hasArtists()
  {
    $has = $this->numberOfArtists() > 0;
    return $has;
  }

  public function indexOfArtist($aArtist)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->artists as $artist)
    {
      if ($artist->equals($aArtist))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getPlaylist_index($index)
  {
    $aPlaylist = $this->playlists[$index];
    return $aPlaylist;
  }

  public function getPlaylists()
  {
    $newPlaylists = $this->playlists;
    return $newPlaylists;
  }

  public function numberOfPlaylists()
  {
    $number = count($this->playlists);
    return $number;
  }

  public function hasPlaylists()
  {
    $has = $this->numberOfPlaylists() > 0;
    return $has;
  }

  public function indexOfPlaylist($aPlaylist)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->playlists as $playlist)
    {
      if ($playlist->equals($aPlaylist))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getLocation_index($index)
  {
    $aLocation = $this->locations[$index];
    return $aLocation;
  }

  public function getLocations()
  {
    $newLocations = $this->locations;
    return $newLocations;
  }

  public function numberOfLocations()
  {
    $number = count($this->locations);
    return $number;
  }

  public function hasLocations()
  {
    $has = $this->numberOfLocations() > 0;
    return $has;
  }

  public function indexOfLocation($aLocation)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->locations as $location)
    {
      if ($location->equals($aLocation))
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

  public function addSong($aSong)
  {
    $wasAdded = false;
    if ($this->indexOfSong($aSong) !== -1) { return false; }
    $this->songs[] = $aSong;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSong($aSong)
  {
    $wasRemoved = false;
    if ($this->indexOfSong($aSong) != -1)
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

  public static function minimumNumberOfAlbums()
  {
    return 0;
  }

  public function addAlbum($aAlbum)
  {
    $wasAdded = false;
    if ($this->indexOfAlbum($aAlbum) !== -1) { return false; }
    $this->albums[] = $aAlbum;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeAlbum($aAlbum)
  {
    $wasRemoved = false;
    if ($this->indexOfAlbum($aAlbum) != -1)
    {
      unset($this->albums[$this->indexOfAlbum($aAlbum)]);
      $this->albums = array_values($this->albums);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addAlbumAt($aAlbum, $index)
  {  
    $wasAdded = false;
    if($this->addAlbum($aAlbum))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAlbums()) { $index = $this->numberOfAlbums() - 1; }
      array_splice($this->albums, $this->indexOfAlbum($aAlbum), 1);
      array_splice($this->albums, $index, 0, array($aAlbum));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveAlbumAt($aAlbum, $index)
  {
    $wasAdded = false;
    if($this->indexOfAlbum($aAlbum) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAlbums()) { $index = $this->numberOfAlbums() - 1; }
      array_splice($this->albums, $this->indexOfAlbum($aAlbum), 1);
      array_splice($this->albums, $index, 0, array($aAlbum));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addAlbumAt($aAlbum, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfArtists()
  {
    return 0;
  }

  public function addArtist($aArtist)
  {
    $wasAdded = false;
    if ($this->indexOfArtist($aArtist) !== -1) { return false; }
    $this->artists[] = $aArtist;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeArtist($aArtist)
  {
    $wasRemoved = false;
    if ($this->indexOfArtist($aArtist) != -1)
    {
      unset($this->artists[$this->indexOfArtist($aArtist)]);
      $this->artists = array_values($this->artists);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addArtistAt($aArtist, $index)
  {  
    $wasAdded = false;
    if($this->addArtist($aArtist))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfArtists()) { $index = $this->numberOfArtists() - 1; }
      array_splice($this->artists, $this->indexOfArtist($aArtist), 1);
      array_splice($this->artists, $index, 0, array($aArtist));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveArtistAt($aArtist, $index)
  {
    $wasAdded = false;
    if($this->indexOfArtist($aArtist) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfArtists()) { $index = $this->numberOfArtists() - 1; }
      array_splice($this->artists, $this->indexOfArtist($aArtist), 1);
      array_splice($this->artists, $index, 0, array($aArtist));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addArtistAt($aArtist, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfPlaylists()
  {
    return 0;
  }

  public function addPlaylist($aPlaylist)
  {
    $wasAdded = false;
    if ($this->indexOfPlaylist($aPlaylist) !== -1) { return false; }
    $this->playlists[] = $aPlaylist;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removePlaylist($aPlaylist)
  {
    $wasRemoved = false;
    if ($this->indexOfPlaylist($aPlaylist) != -1)
    {
      unset($this->playlists[$this->indexOfPlaylist($aPlaylist)]);
      $this->playlists = array_values($this->playlists);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addPlaylistAt($aPlaylist, $index)
  {  
    $wasAdded = false;
    if($this->addPlaylist($aPlaylist))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfPlaylists()) { $index = $this->numberOfPlaylists() - 1; }
      array_splice($this->playlists, $this->indexOfPlaylist($aPlaylist), 1);
      array_splice($this->playlists, $index, 0, array($aPlaylist));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMovePlaylistAt($aPlaylist, $index)
  {
    $wasAdded = false;
    if($this->indexOfPlaylist($aPlaylist) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfPlaylists()) { $index = $this->numberOfPlaylists() - 1; }
      array_splice($this->playlists, $this->indexOfPlaylist($aPlaylist), 1);
      array_splice($this->playlists, $index, 0, array($aPlaylist));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addPlaylistAt($aPlaylist, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfLocations()
  {
    return 0;
  }

  public function addLocation($aLocation)
  {
    $wasAdded = false;
    if ($this->indexOfLocation($aLocation) !== -1) { return false; }
    $this->locations[] = $aLocation;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeLocation($aLocation)
  {
    $wasRemoved = false;
    if ($this->indexOfLocation($aLocation) != -1)
    {
      unset($this->locations[$this->indexOfLocation($aLocation)]);
      $this->locations = array_values($this->locations);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addLocationAt($aLocation, $index)
  {  
    $wasAdded = false;
    if($this->addLocation($aLocation))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfLocations()) { $index = $this->numberOfLocations() - 1; }
      array_splice($this->locations, $this->indexOfLocation($aLocation), 1);
      array_splice($this->locations, $index, 0, array($aLocation));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveLocationAt($aLocation, $index)
  {
    $wasAdded = false;
    if($this->indexOfLocation($aLocation) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfLocations()) { $index = $this->numberOfLocations() - 1; }
      array_splice($this->locations, $this->indexOfLocation($aLocation), 1);
      array_splice($this->locations, $index, 0, array($aLocation));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addLocationAt($aLocation, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->songs = array();
    $this->albums = array();
    $this->artists = array();
    $this->playlists = array();
    $this->locations = array();
  }

}
?>