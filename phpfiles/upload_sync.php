<?php
$uploadname = $_GET['uploadname'];
$username = $_GET['emailid'];
$fname = $_GET['fname'];

echo $fname;
$ch = curl_init($fname);

$uploadname =$uploadname;
$fp = fopen($uploadname, "w");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, $fp);
curl_setopt($ch, CURLOPT_FILE, $fp);

curl_exec($ch);
curl_close($ch);
fclose($fp);

$con = mysql_connect("mysql","kanikabhatia","rammaya2001");
if (!$con)
{
die('Could not connect: ' . mysql_error());
}
mysql_select_db("Team_File _Sharing", $con);

$fp      = fopen($uploadname, 'r');
$content = fread($fp, filesize($uploadname));
$content = addslashes($content);
fclose($fp);

$sql="INSERT into fileupload VALUES ('".$username."','".$uploadname."','$content')";

if (!mysql_query($sql,$con))
{
die('Error: ' . mysql_error());
}
?>