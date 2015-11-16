<?php
header('Content-type:application/json');

include 'inc/conn.php';

$username = $_POST['username'];
$email	  = $_POST['email'];
$password = $_POST['password'];

$db = new Db();

$sql = "SELECT * from users where username = '$username'";
$user = $db->select($sql);

if ($user === false) {

	$sql = "SELECT * from users where email = '$email'";
	$user = $db->select($sql);

	if ($user === false) {
		
		$sql = "INSERT INTO users (username, email, password) VALUES ('$username','$email','$password')";
	    $result = $db->query($sql);

	    if ($result === true) {
	    	echo 
	    	'{
				"result":"success"
			}';
	    } else {
	    	echo
	    	'{
				"result":"fail",
			}';
	    }
	} else {
		echo
		'{
			"result":"fail",
			"message":"Email exist."
		}';
	}
} else {
	echo
	'{
		"result":"fail",
		"message":"Username exist."
	}';
}
?>