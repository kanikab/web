/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function validateFirstName()
{
    var x = document.forms["register"]["firstname"].value;
    if (x === null || x === "")
    {
        alert("First name cannot be blank");
   //     return false;
    }
}
function validateLastName()
{
    var x = document.forms["register"]["lastname"].value;
    if (x === null || x === "")
    {
        alert("Last name cannot be blank");
     //   return false;
    }
}
function validateEmail()
{
    var x = document.forms["register"]["email"].value;
    if (x === null || x === "")
    {
        alert("E-Mail cannot be blank");
       // return false;
    }
}

function validatePassword()
{
    var x = document.forms["register"]["password"].value;
    if (x === null || x === "")
    {
        alert("Password cannot be blank");
       // return false;
    }
}
function validateConfirmPassword()
{
    var x = document.forms["register"]["cpassword"].value;
    if (x === null || x === "")
    {
        alert("Password cannot be blank");
        //return false;
    }
}
function emailValidation()
{
    var x = document.forms["register"]["email"].value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length)
    {
        alert("Enter a valid e-mail address");
        //eturn false;
    }
}

function passwordValidation()
{
    var pwd = document.forms["register"]["password"].value;
    var pwd1 = document.forms["register"]["cpassword"].value;
    if (pwd === pwd1)
    {
        var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])\w{8,15}$/;
        if(!re.test(pwd))
            {
                alert("Password should be atleast 8 character long and should have lower and upper case alphabets and digits and special characters [!@#$&*]");
            }

    }
    else
    {
        alert("Password donot match");
    }

}