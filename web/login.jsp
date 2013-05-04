<%-- 
    Document   : login
    Created on : May 3, 2013, 1:00:09 AM
    Author     : Kanika
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
        <link href="style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id ="menu">
            <a href="home.jsp">Home</a>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;
            <a href="register.jsp">Register</a>
        </div>
        <div id="container">
            <p><p>
                <a href="http://www.sjsu.edu"><img src="sjsu.gif" width="70" height="57" alt="San Jose State University"  />
                    <b>
                        E-Mail<input type="text" name="email" value="" size="25" maxlength="55" />
                        Password <input type="password" name="password" value="" size="25" maxlength="15" />
                        <br>
                        <input type="submit" value="Log In" name="login" right =" 400" />
        </div>
    </body>
</html>
