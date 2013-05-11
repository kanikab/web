<?php
$firstname = $_GET['firstname'];
$lastname = $_GET['lastname'];
$emailid = $_GET['emailid'];
$password = $_GET['password'];
$token = generateRandStr(49);

function generateRandStr($length){ 
$randstr = ""; 
for($i=0; $i<$length; $i++)
{ 
$randnum = mt_rand(0,61); 
if($randnum < 10)
{ 
$randstr .= chr($randnum+48); 
}
else if($randnum < 36)
{ 
$randstr .= chr($randnum+55); 
}
else
{ 
$randstr .= chr($randnum+61); 
} 
} 
return $randstr; 
   }  

$con = mysql_connect("mysql","kanikabhatia","rammaya2001"); 
if (!$con) 
{ 
die('Could not connect: ' . mysql_error()); 
} 

mysql_select_db("Team_File _Sharing", $con); 

$sql = "select * from userdetails where emailid = '".$emailid."'";

$result = mysql_query($sql);
$row = mysql_num_rows($result);

if($row == 0)
{

$sql="INSERT usertemp VALUES ('".$firstname."','".$lastname."','".$emailid."','".$password."','".$token."')";
$result = mysql_query($sql);

$userdetails = $firstname."..".$lastname."..".$emailid."..".$password;
$message = "You have successfully registered your account.";
$message .= "Click the given link to activate your account.";
$url = "http://kanikabhatia-photos.com/Team_File_Share/";
$url .= "confirm.php?token=$token";
$message .= $url;
mail($emailid,"Successful Registration",$message);
echo "Success";

$url="http://donniprateek.com/register_sync.php?firstname=".$firstname."&lastname=".$lastname."&password=".$password."&emailid=".$emailid."&token=".$token;

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$response = curl_exec($ch);
curl_close($ch);

 
}
else
{
echo "Error";
}
?>