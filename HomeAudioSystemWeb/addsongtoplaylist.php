<?php
require_once 'controller/Controller.php';


session_start();

$c = new Controller();

try {
	$c->addSongToPlaylist($_POST['song_index'], $_POST['playlist_index']);
	$_SESSION["error"] = "";
} catch (Exception $e) {

	$_SESSION["error"] = $e->getMessage();
}
?>

<!DOCTYPE html> 
<html> 
  	<head> 
    	<meta http-equiv="refresh" content="0; url=/HomeAudioSystemWeb/" /> 
  	</head> 
</html> 