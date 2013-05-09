<?php
$filename=$_GET['fname'];
$handle = fopen($filename, "r") or die("error in reading");
$contents = fread($handle, filesize($filename));
$contents = addslashes($contents);
fclose($handle);

$field = split(",,",$contents);


$con = mysql_connect("mysql","kanikabhatia","rammaya2001");
if (!$con)
{
die('Could not connect: ' . mysql_error());
}

mysql_select_db("Team_File _Sharing", $con);

$sql="INSERT userdetails VALUES ('".$field[0]."','".$field[1]."','".$field[2]."','".$field[3]."','".$field[4]."')";

if (!mysql_query($sql,$con))
{
die('Error: ' . mysql_error());
}
unlink($filename);
echo "Account Activated";
?>