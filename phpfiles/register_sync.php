<?php

$firstname=$_GET['firstname'];
$lastname=$_GET['lastname'];
$emailid=$_GET['emailid'];
$password=$_GET['password'];
$token=$_GET['token'];

echo $sql;

$con = mysql_connect("mysql","kanikabhatia","rammaya2001");
if (!$con)
{
die('Could not connect: ' . mysql_error());
}

mysql_select_db("Team_File _Sharing", $con);

$sql = "INSERT INTO usertemp VALUES ('".$firstname."','".$lastname."','".$emailid."','".$password."','".$token."')";

if (!mysql_query($sql,$con))
{
die('Error: ' . mysql_error());
}

mysql_close($con);
?>


