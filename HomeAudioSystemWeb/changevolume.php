<?php
require_once 'controller/Controller.php';


session_start();

$c = new Controller();

try {
	$c->changeVolume($_POST['location_index'], $_POST['volume']);
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