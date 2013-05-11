<?php
$filename = $_GET['filename'];
$username = $_GET['userid'];

$con = mysql_connect("mysql","kanikabhatia","rammaya2001");
if (!$con)
{
die('Could not connect: ' . mysql_error());
}

mysql_select_db("Team_File _Sharing", $con);

$query = "SELECT userid , emailid FROM userdetails";
$result = mysql_query($query);


//echo $username;
while($row = mysql_fetch_array($result))
{
   $email = $row['emailid'];
   $email1 = trim($email);
   $id=$row['userid'];
   if($userid == $email1)
     break;
}
$fnames = $id."_".$filename;
$fp      = fopen($fnames, 'r');
$content = fread($fp, filesize($fnames));
$content = addslashes($content);
fclose($fp);
echo $content;


?>