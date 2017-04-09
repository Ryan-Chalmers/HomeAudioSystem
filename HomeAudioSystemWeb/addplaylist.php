<?php
require_once 'controller/Controller.php';


session_start();

$c = new Controller();

try {
	$c->createPlaylist($_POST['playlist_name']);
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