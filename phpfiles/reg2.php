<?php
$firstname = $_GET['firstname'];
$lastname = $_GET['lastname'];
$emailid = $_GET['emailid'];
$password = $_GET['password'];
$token = generateRandStr(10);

$filename = "register.txt";
$fileData = $firstname.",,".$lastname.",,".$emailid.",,".$password.",,".$token;
$fhandle=fopen($filename, 'w')or die("Error opening file");
fwrite($fhandle, $fileData) or die("Error writing to file");
fclose($fhandle)or die("Error closing file");
echo "Successful Upload";


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

$sql = "select * from userdetails where emailid = '".$field[0]."'";

$result = mysql_query($sql);
$row = mysql_fetch_row($result)

if($row == 0)
{
$userdetails = $firstname."..".$lastname."..".$emailid."..".$password;
$message = "You have successfully registered your account.";
$message .= "Click the given link to activate your account.";
$url = "http://kanikabhatia-photos.com/Team_File_Share/";
$url .= "confirmation.php?fname=$filename";
$message .= $url;
mail($emailid,"Successful Registration",$message);
echo "Success";
}
else
{
echo "Error";
}
?>