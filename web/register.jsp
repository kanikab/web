<%-- 
    Document   : index
    Created on : May 3, 2013, 12:32:00 AM
    Author     : Kanika
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <link href="style.css" rel="stylesheet" type="text/css">
        <script src="validation.js"></script>
    </head>
    <body>
        
        <div id ="menu">
            <a href="login.jsp">Home</a>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;
            <a href="register.jsp">Register</a>
    </div>
        
        <div id ="container">
            <p><p><a href="http://www.sjsu.edu"><img src="sjsu.gif" width="70" height="57" alt="images"  />
         </a>   
        <h3>Registration</h3>
        <form action="home.jsp" method="POST" name ="register" id="register">
            <br>First Name<br>
            <input type="text" name="firstname" value="" size="35" maxlength="55" /></b><br>
            <br>Last Name
            <br><input type="text" name="lastname" value="" size="35" maxlength="55"/></b><br>
            <br>E-Mail
            <br><input type="text" name="email" value="" size="35" maxlength="55"/></b><br>
            <br>Password
            <br><input type="password" name="password" value="" size="35" maxlength="15"/></b><br>
            <br>Confirm Password
            <br><input type="password" name="cpassword" value="" size="35" maxlength="15"/></b><br>
            <br><br><br><br>
            <input type="submit" value="Register" name="register" onclick="readData();"/>
            <input type="reset" value="Reset" name="reset" />
        </form>
        </div>
    </body>
    
    <script lang ="javascript">
        function readData()
        {
           
                var data = document.forms["register"]["firstname"].value;
                data = data + ",,"+document.forms["register"]["lastname"].value;
                data = data + ",,"+document.forms["register"]["email"].value;
                data = data + ",,"+document.forms["register"]["password"].value;
                alert(data);
                //alert(reg.test1(data));
                
        }
    </script>
</html>
