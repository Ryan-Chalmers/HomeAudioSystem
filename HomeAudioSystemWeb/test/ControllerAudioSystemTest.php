<?php

/*
require_once 'persistence/PersistenceAudioSystem.php';
require_once 'model/HAS.php';
require_once 'model/Album.php';
require_once 'model/Artist.php';
require_once 'model/Playlist.php';
require_once 'model/Location.php';
require_once 'model/Song.php';
require_once 'controller/InputValidator.php';
*/

// /*
require_once 'C:\Users\ArnoldK\Desktop\Group02\HomeAudioSystemWeb\controller\Controller.php';
require_once 'C:\Users\ArnoldK\Desktop\Group02\HomeAudioSystemWeb\persistence\PersistenceAudioSystem.php';
require_once 'C:\Users\ArnoldK\Desktop\Group02\HomeAudioSystemWeb\model\HAS.php';
require_once 'C:\Users\ArnoldK\Desktop\Group02\HomeAudioSystemWeb\model\Album.php';
require_once 'C:\Users\ArnoldK\Desktop\Group02\HomeAudioSystemWeb\model\Artist.php';
require_once 'C:\Users\ArnoldK\Desktop\Group02\HomeAudioSystemWeb\model\Playlist.php';
require_once 'C:\Users\ArnoldK\Desktop\Group02\HomeAudioSystemWeb\model\Location.php';
require_once 'C:\Users\ArnoldK\Desktop\Group02\HomeAudioSystemWeb\model\Song.php';
// */

class PersistenceAudioSystemTest extends PHPUnit_Framework_TestCase
{
	protected $controller;
    protected $persistence;
    protected $has;
	
	protected function setUp()
	{
		$this->controller = new Controller();
		$this->persistence = new PersistenceAudioSystem();
		$this->has = $this->persistence->loadDataFromStore();
		$this->has->delete();
		$this->persistence->writeDataToStore($this->has);
	}
	
	protected function tearDown()
	{	
	}
	
	public function testCreateAlbum() {
		$this->assertEquals(0, count($this->has->getAlbums()));
	
		$name = "If you read this it's too late";
		$album_genre = "Hip hop";
		$album_releasedate = "2015-12-01";
		
		try {
			$this->controller->createAlbum($name, $album_genre, $album_releasedate);
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(1, count($this->has->getAlbums()));
		$this->assertEquals($name, $this->has->getAlbum_index(0)->getTitle());
		$this->assertEquals($album_genre, $this->has->getAlbum_index(0)->getGenre());
		$this->assertEquals($album_releasedate, $this->has->getAlbum_index(0)->getReleaseDate());
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreateAlbumNull() {
		$this->assertEquals(0, count($this->has->getAlbums()));
	
		$name = null;
		$album_genre = null;
		$album_releasedate = null;
	
		$error = "";
		try {
			$this->controller->createAlbum($name, $album_genre, $album_releasedate);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error 
		$this->assertEquals("Album name cannot be empty! Album genre cannot be empty! Album must have a release date!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreateAlbumEmpty() {
		$this->assertEquals(0, count($this->has->getAlbums()));
	
		$name = "";
		$album_genre = "";
		$album_releasedate = "";
	
		$error = "";
		try {
			$this->controller->createAlbum($name, $album_genre, $album_releasedate);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Album name cannot be empty! Album genre cannot be empty! Album must have a release date!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreateAlbumSpaces() {
		$this->assertEquals(0, count($this->has->getAlbums()));
	
		$name = " ";
		$album_genre = " ";
		$album_releasedate = " ";
	
		$error = "";
		try {
			$this->controller->createAlbum($name, $album_genre, $album_releasedate);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Album name cannot be empty! Album genre cannot be empty! Album must have a release date!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	 public function testCreateAlbumWrongDateFormat() {
		$this->assertEquals(0, count($this->has->getAlbums()));
	
		$name = "If you read this it's too late";
		$album_genre = "Hip hop";
		$album_releasedate = "abc123";
	
		$error = "";
		try {
			$this->controller->createAlbum($name, $album_genre, $album_releasedate);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Album must have a release date specified correctly (YYYY-MM-DD)!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	 }
	
	
	public function testCreateArtist() {
		$this->assertEquals(0, count($this->has->getArtists()));
	
		$artist_name = "Drake";
	
		try {
			$this->controller->createArtist($artist_name);
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(1, count($this->has->getArtists()));
		$this->assertEquals($artist_name, $this->has->getArtist_index(0)->getName());
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}

	public function testCreateArtistNull() {
		$this->assertEquals(0, count($this->has->getAlbums()));
	
		$artist_name = null;
	
		$error = "";
		try {
			$this->controller->createArtist($artist_name);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Artist name cannot be empty!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreateArtistEmpty() {
		$this->assertEquals(0, count($this->has->getAlbums()));
	
		$artist_name = "";
	
		$error = "";
		try {
			$this->controller->createArtist($artist_name);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Artist name cannot be empty!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreateArtistSpaces() {
		$this->assertEquals(0, count($this->has->getAlbums()));
	
		$artist_name = " ";
	
		$error = "";
		try {
			$this->controller->createArtist($artist_name);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Artist name cannot be empty!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreateLocation() {
		$this->assertEquals(0, count($this->has->getLocations()));
	
		$location_name = "Kitchen"; 
		$location_volume = "50";
	
		try {
			$this->controller->createLocation($location_name, $location_volume);
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals($location_name, $this->has->getLocation_index(0)->getName());
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals($location_volume, $this->has->getLocation_index(0)->getVolume());
		$this->assertEquals(1, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreateLocationNull() {
		$this->assertEquals(0, count($this->has->getLocations()));
		
		$location_name = null; 
		$location_volume = null;
	
		$error = "";
		try {
			$this->controller->createLocation($location_name, $location_volume);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Location name cannot be empty! Location volume cannot be empty!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreateLocationSpaces() {
		$this->assertEquals(0, count($this->has->getLocations()));
	
		$location_name = " ";
		$location_volume = " ";
	
		$error = "";
		try {
			$this->controller->createLocation($location_name, $location_volume);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Location name cannot be empty! Location volume cannot be empty!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreateLocationEmpty() {
		$this->assertEquals(0, count($this->has->getLocations()));
	
		$location_name = "";
		$location_volume = "";
	
		$error = "";
		try {
			$this->controller->createLocation($location_name, $location_volume);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Location name cannot be empty! Location volume cannot be empty!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreateLocationWrongFormatVolume() {
		$this->assertEquals(0, count($this->has->getLocations()));
	
		$location_name = "Kitchen";
		$location_volume = "abc123";
	
		$error = "";
		try {
			$this->controller->createLocation($location_name, $location_volume);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Location volument must be an Integer!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	/*
	public function testCreateLocationMaxVolume() {
		$this->assertEquals(0, count($this->has->getArtists()));
	
		$location_name = "Kitchen";
		$location_volume = "101";
	
		try {
			$this->controller->createLocation($location_name, $location_volume);
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(1, count($this->has->getArtists()));
		$this->assertEquals($artist_name, $this->has->getArtist_index(0)->getName());
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreateLocationMinVolume() {
		$this->assertEquals(0, count($this->has->getArtists()));
	
		$location_name = "Kitchen";
		$location_volume = "-1";
	
		try {
			$this->controller->createLocation($location_name, $location_volume);
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(1, count($this->has->getArtists()));
		$this->assertEquals($artist_name, $this->has->getArtist_index(0)->getName());
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	*/
	
	public function testCreatePlaylist() {
		$this->assertEquals(0, count($this->has->getPlaylists()));
	
		$playlist_name = "My Fav Playlist";
	
		try {
			$this->controller->createPlaylist($playlist_name);
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(1, count($this->has->getPlaylists()));
		$this->assertEquals($playlist_name, $this->has->getPlaylist_index(0)->getName());
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreatePlaylistNull() {
		$this->assertEquals(0, count($this->has->getLocations()));
	
		$playlist_name = null;
	
		$error = "";
		try {
			$this->controller->createPlaylist($playlist_name);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Playlist name cannot be empty!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreatePlaylistEmpty() {
		$this->assertEquals(0, count($this->has->getLocations()));
	
		$playlist_name = "";
	
		$error = "";
		try {
			$this->controller->createPlaylist($playlist_name);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Playlist name cannot be empty!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreatePlaylistSpaces() {
		$this->assertEquals(0, count($this->has->getLocations()));
	
		$playlist_name = " ";
	
		$error = "";
		try {
			$this->controller->createPlaylist($playlist_name);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Playlist name cannot be empty!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreateSong() {
		$this->assertEquals(0, count($this->has->getSongs()));
	
		$song_title = "Jumpman";
		$song_duration = "03:30";
		
		$artist_name = "Drake";
		
		$album_title = "If you read this it's too late";
		$album_genre = "Hip Hop";
		$album_releasedate = "02/02/2016";
			
		
		try {
			
			$this->controller->createArtist($artist_name);
			$this->controller->createAlbum($album_title, $album_genre, $album_releasedate);
			$this->has = $this->persistence->loadDataFromStore();
			$song_album_index = $this->has->indexOfAlbum($this->has->getAlbum_index(0));
			$song_artist_index = $this->has->indexOfArtist($this->has->getArtist_index(0));
			
			$this->controller->createSong($song_title, $song_duration, $song_album_index, $song_artist_index);
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(1, count($this->has->getArtists()));
		$this->assertEquals(1, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals($song_title, $this->has->getSong_index(0)->getTitle());
		$this->assertEquals($song_duration, $this->has->getSong_index(0)->getDuration());
		$this->assertEquals(1, count($this->has->getSongs()));
	}
	
	public function testCreateSongNull() {
		$this->assertEquals(0, count($this->has->getSongs()));
	
		$song_title = null;
		$song_duration = null;
		$song_album_index = null;
		$song_artist_index = null;
		
	
		$error = "";
		try {
			$this->controller->createSong($song_title, $song_duration, $song_album_index, $song_artist_index);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Song title cannot be empty! Song duration cannot be empty! Song album must be selected! Song artist must be selected!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreateSongEmpty() {
		$this->assertEquals(0, count($this->has->getSongs()));
	
		$song_title = "";
		$song_duration = "";
		$song_album_index = "";
		$song_artist_index = "";
	
	
		$error = "";
		try {
			$this->controller->createSong($song_title, $song_duration, $song_album_index, $song_artist_index);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Song title cannot be empty! Song duration cannot be empty! Song album must be selected! Song artist must be selected!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testCreateSongSpaces() {
		$this->assertEquals(0, count($this->has->getSongs()));
	
		$song_title = " ";
		$song_duration = " ";
		$song_album_index = " ";
		$song_artist_index = " ";
	
	
		$error = "";
		try {
			$this->controller->createSong($song_title, $song_duration, $song_album_index, $song_artist_index);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Song title cannot be empty! Song duration cannot be empty! Song album must be selected! Song artist must be selected!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	
	 	public function testCreateSongWrongFormatDuration() {
		$this->assertEquals(0, count($this->has->getSongs()));
	
		$song_title = "Jumpman";
		$song_duration = "abc123";
		
		$artist_name = "Drake";
		
		$album_title = "If you read this it's too late";
		$album_genre = "Hip Hop";
		$album_releasedate = "02/02/2016";
			
		$error = "";
		try {
			
			$this->controller->createArtist($artist_name);
			$this->controller->createAlbum($album_title, $album_genre, $album_releasedate);
			$this->has = $this->persistence->loadDataFromStore();
			$song_album_index = $this->has->indexOfAlbum($this->has->getAlbum_index(0));
			$song_artist_index = $this->has->indexOfArtist($this->has->getArtist_index(0));
			
			$this->controller->createSong($song_title, $song_duration, $song_album_index, $song_artist_index);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Song duration must follow the format ##:## !", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(1, count($this->has->getAlbums()));
		$this->assertEquals(1, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	 
	
	/*
	public function testMuteLocation() {
		$this->assertEquals(0, count($this->has->getLocations()));
		
		$location_name = "Kitchen";
		$location_volume = "50";
		$newVolume = "75";
	
		try {
			$this->controller->createLocation($location_name, $location_volume);
			$this->has = $this->persistence->loadDataFromStore();
				//utiliser index location au lieu de passer la location direct
			$this->controller->muteLocation($this->has->getLocation_index(0), $newVolume, $location_volume);
		} catch (Exception $e) {
			$this->assertEquals("lol", $e);
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(1, count($this->has->getLocations()));
		$this->assertEquals($location_name, $this->has->getLocation_index(0)->getName());
		$this->assertEquals($newVolume, $this->has->getLocation_index(0)->getVolume());
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	*/
	
	public function testAddSongToPlaylist() {
		$this->assertEquals(0, count($this->has->getPlaylists()));
	
		$song_title = "Jumpman";
		$song_duration = "03:30";
	
		$artist_name = "Drake";
	
		$album_title = "If you read this it's too late";
		$album_genre = "Hip Hop";
		$album_releasedate = "02/02/2016";
			
		$playlist_name = "My Fav Playlist";
	
		try {
				
			$this->controller->createArtist($artist_name);
			$this->controller->createAlbum($album_title, $album_genre, $album_releasedate);
			$this->has = $this->persistence->loadDataFromStore();
			$song_album_index = $this->has->indexOfAlbum($this->has->getAlbum_index(0));
			$song_artist_index = $this->has->indexOfArtist($this->has->getArtist_index(0));
				
			$this->controller->createSong($song_title, $song_duration, $song_album_index, $song_artist_index);
			$this->controller->createPlaylist($playlist_name);
			
			$this->has = $this->persistence->loadDataFromStore();
			$song_index = $this->has->indexOfSong($this->has->getSong_index(0)); 
			$playlist_index = $this->has->indexOfPlaylist($this->has->getPlaylist_index(0));
				
			$this->controller->addSongToPlaylist($song_index, $playlist_index);
				
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(1, count($this->has->getArtists()));
		$this->assertEquals(1, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(1, count($this->has->getPlaylists()));
		$this->assertEquals($song_title, $this->has->getSong_index(0)->getTitle());
		$this->assertEquals($song_duration, $this->has->getSong_index(0)->getDuration());
		$this->assertEquals(1, count($this->has->getSongs()));
	}
	
	/* Might not have to implement
	public function testAddSongWrongToPlaylistWrong() {
		$this->assertEquals(0, count($this->has->getLocations()));
	
		$song_title = null;
		$song_duration = null;
		$song_album_index = null;
		$song_artist_index = null;
	
	
		$error = "";
		try {
			$this->controller->addSongToPlaylist("", "");
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals("Song must be selected! Playlist must be selected!", $error);
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	*/
	
	public function testAssignSongToLocation() {
		$this->assertEquals(0, count($this->has->getSongs()));
		$this->assertEquals(0, count($this->has->getLocations()));
		
	
		$song_title = "Jumpman";
		$song_duration = "03:30";
	
		$artist_name = "Drake";
	
		$album_title = "If you read this it's too late";
		$album_genre = "Hip Hop";
		$album_releasedate = "02/02/2016";
	
		$location_name = "Kitchen";
		$location_volume = "50";
	
		try {
	
			$this->controller->createArtist($artist_name);
			$this->controller->createAlbum($album_title, $album_genre, $album_releasedate);
			$this->controller->createLocation($location_name, $location_volume);
			$this->has = $this->persistence->loadDataFromStore();
			$song_album_index = $this->has->indexOfAlbum($this->has->getAlbum_index(0));
			$song_artist_index = $this->has->indexOfArtist($this->has->getArtist_index(0));
	
			$this->controller->createSong($song_title, $song_duration, $song_album_index, $song_artist_index);
	
			$this->has = $this->persistence->loadDataFromStore();
			$song_index = $this->has->indexOfSong($this->has->getSong_index(0));
			$location_index = $this->has->indexOfLocation($this->has->getLocation_index(0));
	
			$this->controller->assignSongToLocation($song_index, $location_index);
	
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(1, count($this->has->getArtists()));
		$this->assertEquals(1, count($this->has->getAlbums()));
		$this->assertEquals(1, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals($song_title, $this->has->getLocation_index(0)->getSong()->getTitle());
		$this->assertEquals($song_duration, $this->has->getLocation_index(0)->getSong()->getDuration());
		$this->assertEquals(1, count($this->has->getSongs()));
	}
	
	public function testAssignPlaylistToLocation() {
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
			
		$playlist_name = "My Fav Playlist";
		
		$location_name = "Kitchen";
		$location_volume = "50";
		
		try {
			$this->controller->createLocation($location_name, $location_volume);
			$this->controller->createPlaylist($playlist_name);
			
			$this->has = $this->persistence->loadDataFromStore();
			$playlist_index = $this->has->indexOfPlaylist($this->has->getPlaylist_index(0));
			$location_index = $this->has->indexOfLocation($this->has->getLocation_index(0));
				
			$this->controller->assignPlaylistToLocation($playlist_index, $location_index);
				
		} catch (Exception $e) {
			$this->assertEquals("lol", $e);
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getAlbums()));
		$this->assertEquals(1, count($this->has->getLocations()));
		$this->assertEquals(1, count($this->has->getPlaylists()));
		$this->assertEquals($playlist_name, $this->has->getLocation_index(0)->getPlaylist()->getName());
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	public function testAssignAlbumToLocation() {
		$this->assertEquals(0, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getAlbums()));
	
		$album_title = "If you read this it's too late";
		$album_genre = "Hip Hop";
		$album_releasedate = "02/02/2016";
	
		$location_name = "Kitchen";
		$location_volume = "50";
	
		try {
	
			$this->controller->createAlbum($album_title, $album_genre, $album_releasedate);
			$this->controller->createLocation($location_name, $location_volume);
				
			$this->has = $this->persistence->loadDataFromStore();
			$album_index = $this->has->indexOfAlbum($this->has->getAlbum_index(0));
			$location_index = $this->has->indexOfLocation($this->has->getLocation_index(0));
	
			$this->controller->assignAlbumToLocation($album_index, $location_index);
	
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(1, count($this->has->getAlbums()));
		$this->assertEquals($album_title, $this->has->getLocation_index(0)->getAlbum()->getTitle());
		$this->assertEquals(1, count($this->has->getLocations()));
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getSongs()));
	}
	
	
}