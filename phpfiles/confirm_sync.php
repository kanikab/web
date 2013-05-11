<?php
$token=$_GET['token'];
$con = mysql_connect("mysql","kanikabhatia","rammaya2001");
if (!$con)
{
	die('Could not connect: ' . mysql_error());
}
mysql_select_db("Team_File _Sharing", $con);
$sql= "SELECT max(userid) FROM userdetails";
$result = mysql_query($sql);
$id = mysql_fetch_row($result);
$id = $id[0] + 1;
$sql = "select * from usertemp";
$result = mysql_query($sql);
while($row = mysql_fetch_array($result))
{
	$token1 = $row['token'];
	$token1 = trim($token1);
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
	}
}
mysql_close($con);
?>





