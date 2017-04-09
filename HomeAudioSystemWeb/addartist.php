
<?php
require_once 'controller/Controller.php';


session_start();

$_SESSION["error"] = "";

$c = new Controller();

try {
	$c->createArtist($_POST['artist_name']);
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