<?php

$filename = $_GET['fname'];
$username = $_GET['userid'];
$fnames = $username."_".$filename;
$fileData=file_get_contents('php://input');
$fhandle=fopen($fnames, 'w')or die("Error opening file");
fwrite($fhandle, $fileData) or die("Error writing to file");
fclose($fhandle)or die("Error closing file");
echo "Successful Upload";


$fp      = fopen($fnames, 'r');
$content = fread($fp, filesize($fnames));
$content = addslashes($content);
fclose($fp);

$con = mysql_connect("mysql","kanikabhatia","rammaya2001"); 
if (!$con) 
{ 
die('Could not connect: ' . mysql_error()); 
} 

$filetype = split( ".", $filename);
echo "file type ".$filetype[1].$filetype[0];

mysql_select_db("Team_File _Sharing", $con); 

$sql="INSERT fileupload VALUES ('".$userid."','".$filename."','$content')"; 

if (!mysql_query($sql,$con)) 
{ 
die('Error: ' . mysql_error()); 
} 



?>