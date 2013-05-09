<?php
$emailid = $_GET['emailid'];
$password = $_GET['password'];

$con = mysql_connect("mysql","kanikabhatia","rammaya2001");
if (!$con)
{
die('Could not connect: ' . mysql_error());
}

mysql_select_db("Team_File _Sharing", $con);

$sql = "select * from userdetails where emailid = '".$emailid."' and password = '".$password."'";

$result = mysql_query($sql);
$row = mysql_num_rows($result);
echo $row;
?>
