<%-- 
    Document   : login
    Created on : Sep 28, 2018, 3:23:47 PM
    Author     : 766375
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remember Me Login Page</title>
    </head>
    <body>
        <h1>Remember Me Login Page</h1>
        <form action="login" method="POST">
            User Name: <input type="text" name="userName" value="${userName}"><br>
            Password: <input type="password" name="password"><br>
            <input type="checkbox" name="persist" value="true">Remember Me<br>
            <input type="submit" value="Login" name="submit" ${checked}>
        </form>
        <div>
            ${error}
        </div>
    </body>
</html>
