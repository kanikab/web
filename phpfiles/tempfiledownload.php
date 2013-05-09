<?php
$filename = $_GET['filename'];
$username = $_GET['userid'];
$fname = $username."_".$filename;

$con = mysql_connect("mysql","kanikabhatia","rammaya2001");
if (!$con)
{
die('Could not connect: ' . mysql_error());
}

mysql_select_db("Team_File _Sharing", $con);

$sql = "select * from fileupload where fileName = 'test.html' ";
$result = mysql_query($sql);
while($row = mysql_fetch_array($result))
{
echo $row[file];
}


?>