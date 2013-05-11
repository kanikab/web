<?php

$filename = $_GET['fname'];
$userid = $_GET['userid'];


$con = mysql_connect("mysql","kanikabhatia","rammaya2001");
if (!$con)
{
die('Could not connect: ' . mysql_error());
}

mysql_select_db("Team_File _Sharing", $con);
/*
$query = "select fileName from fileupload" ;
$result = mysql_query($query);*/

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
  echo $id;
//$id = $id[0] + 1;
//$id = $id[userid];

//echo $id;


$fnames = $id."_".$filename;

echo $fnames;

$fileData=file_get_contents('php://input');
$fhandle=fopen($fnames, 'w')or die("Error opening file");
fwrite($fhandle, $fileData) or die("Error writing to file");
fclose($fhandle)or die("Error closing file");

echo "Successful Upload";


$fp      = fopen($fnames, 'r');
$content = fread($fp, filesize($fnames));
$content = addslashes($content);
fclose($fp);

//$content="";

$sql="INSERT into fileupload VALUES ('".$userid."','".$fnames."','$content')";

if (!mysql_query($sql,$con))
{
die('Error: ' . mysql_error());
}

//$fnames="2_k.k";

$url="http://donniprateek.com/curlfilenonssl.php?fname=http://kanikabhatia-photos.com/Team_File_Share/uploads/".$fnames."&uploadname=".$fnames."&emailid=".$userid ;
echo $url;

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$response = curl_exec($ch);
curl_close($ch);


?>