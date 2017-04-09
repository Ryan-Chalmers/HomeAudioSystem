<?php

/*
require_once 'C:\xampp\htdocs\HomeAudioSystemWeb\persistence\PersistenceAudioSystem.php';
require_once 'C:\xampp\htdocs\HomeAudioSystemWeb\model\HAS.php';
require_once 'C:\xampp\htdocs\HomeAudioSystemWeb\model\Album.php';
*/

// /*
require_once 'C:\Users\ArnoldK\Desktop\Group02\HomeAudioSystemWeb\persistence\PersistenceAudioSystem.php';
require_once 'C:\Users\ArnoldK\Desktop\Group02\HomeAudioSystemWeb\model\HAS.php';
require_once 'C:\Users\ArnoldK\Desktop\Group02\HomeAudioSystemWeb\model\Album.php';
// */

class PersistenceAudioSystemTest extends PHPUnit_Framework_TestCase
{
	protected $pm;
	
	protected function setUp()
	{
		$this->pm = new PersistenceAudioSystem();
	}
	
	protected function tearDown()
	{	
	}
	
	public function testPersistence()
	{
		$has = HAS::getInstance();
		$album = new Album("Title", "Genre", "ReleaseDate");
		$has->addAlbum($album);
		
		$this->pm->writeDataToStore($has);
		
		$has->delete();
		
		$this->assertEquals(0, count($has->getAlbums()));
		
		$has = $this->pm->loadDataFromStore();
		
		$this->assertEquals(1, count($has->getAlbums()));
		$myAlbum = $has->getAlbum_index(0);
		$this->assertEquals("Title", $myAlbum->getTitle());
	}
}