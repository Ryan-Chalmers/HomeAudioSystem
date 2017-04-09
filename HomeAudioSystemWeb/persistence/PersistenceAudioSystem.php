<?php

class PersistenceAudioSystem {
	private $filename;
	 
	function __construct($filename = 'data.txt') {
		$this->filename = $filename;
	}

	function loadDataFromStore() {
		if (file_exists($this->filename)) {
			$str = file_get_contents($this->filename);
			$has = unserialize($str);
		} else {
			$has = HAS::getInstance();
		}
		 
		return $has;
	}
	 
	function writeDataToStore($has) {
		$str = serialize($has);
		file_put_contents($this->filename, $str);
	}
}
?>	