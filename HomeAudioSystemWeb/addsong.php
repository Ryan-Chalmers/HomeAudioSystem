
<?php
require_once 'controller/Controller.php';


session_start();

$c = new Controller();

try {
	$c->createSong($_POST['song_title'], $_POST['song_duration'], $_POST['song_album'], $_POST['song_artist']);
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