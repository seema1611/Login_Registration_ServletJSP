<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="US-ASCII">
    <title>Login Form</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

<div class="form-body form-body-loginform">
    <h1>Login Form</h1>
    <%
        String msg=(String)request.getAttribute("message");
        if(msg==null)
        {
            msg="";
        }
    %>
    <%=msg%>
    <form action="UserLoginServlet" method="post">

        <div class="form-input">
            <input type="text" name="email" placeholder="enter the email id" class="form-input-text">
        </div>
        <br>

        <div class="form-input">
            <input type="password" name="password" placeholder="enter the password" class="form-input-text">
        </div>
        <br>

        <br>
        <button type="submit" value="Login" class="form-primary">Login</button>
    </form>
    <h3>Yor are not Already user please Register?</h3>
    <a href="register.jsp"><button>Register</button></a>
</div>
</body>
</html>