<?php

$token =$_GET['token'];

$con = mysql_connect("mysql","kanikabhatia","rammaya2001");
if (!$con)
{
die('Could not connect: ' . mysql_error());
}

mysql_select_db("Team_File _Sharing", $con);

$query = "SELECT max(userid) FROM userdetails";
$result = mysql_query($query);

$id = mysql_fetch_row($result);
$id = $id[0] + 1;


$sql = "select * from usertemp";


$result = mysql_query($sql);
while($row = mysql_fetch_array($result))
{
 $token1 = $row['token'];
 $token1 = trim($token1);
//  echo $token1;

 if($token1 == $token)
 {
   $sql="INSERT userdetails VALUES (".$id.",'".$row['firstname']."','".$row['lastname']."','".$row['emailid']."','".$row['password']."','".$row['token']."')";
   echo $sql;
   if (!mysql_query($sql,$con))
   {
     die('Error: ' . mysql_error());
    }
   echo "Account Activated";
$sql = "delete from usertemp where token = '".$token1."'";
$result = mysql_query($sql);

$url="http://donniprateek.com/activate_sync.php?token=".$token1;

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$response = curl_exec($ch);
curl_close($ch);

 }


}
echo "hello";




?>