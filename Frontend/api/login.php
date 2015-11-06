<?php
header('Content-type:application/json');

include 'inc/conn.php';

$username = $_POST['username'];
$password = $_POST['password'];

$db = new Db();

$sql = "SELECT * from users where username = '$username' and password = '$password'";
$user = $db->select($sql);

if ($user === false) {
	
	$sql = "SELECT * from users where username = '$username'";
	$user = $db->select($sql);

	if ($user === false) {
		echo 
		'{
			"result":"fail",
			"message":"User not found."
		}';
	} else {
		echo
		'{
			"result":"fail",
			"message":"Password is wrong."
		}';
	}
} else {
	echo '
	{
		"result": "success",
		"user": {
			"id": ' . $user["id"] . ',
			"username": "' . $user["username"] . '"
		}
	}
	';
}
?>