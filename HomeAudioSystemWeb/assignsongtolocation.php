<?php

require_once 'controller/Controller.php';

session_start();


$c = new Controller();

$song_index=$_POST['song_index'];
$location_index=$_POST['location_index'];

try {
	$c->assignSongToLocation($song_index, $location_index);
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