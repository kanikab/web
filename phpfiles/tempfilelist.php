<?php
$username = $_GET['userid'];
$con = mysql_connect("mysql","kanikabhatia","rammaya2001");
if (!$con)
{
die('Could not connect: ' . mysql_error());
}

mysql_select_db("Team_File _Sharing", $con);

$sql = "select fileName from fileupload where username = '".$username."'";

$result = mysql_query($sql);
while($row = mysql_fetch_array($result))
{
echo $row[fileName].",";
}
?>