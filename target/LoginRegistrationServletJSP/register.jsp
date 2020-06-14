<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="US-ASCII">
    <title>Registration Page</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

<div class="form-body form-body-registerform">
    <h1>Registeration Form</h1>
    <%
        String msg=(String)request.getAttribute("message");
        if(msg==null)
        {
            msg="";
        }
    %>
    <%=msg%>
    <form action="UserRegisterServlet" method="post">
        <div class="form-input">
            <input type="text" name="username" placeholder="enter the username" class="form-input-text">
        </div>
        <br>

        <div class="form-input">
            <input type="text" name="email" placeholder="enter the email id" class="form-input-text">
        </div>
        <br>

        <div class="form-input">
            <input type="password" name="password" placeholder="enter the password" class="form-input-text">
        </div>
        <br>

        <div class="form-input">
            <input type="password" name="passwordRepeat" placeholder="re-enter password" class="form-input-text">
        </div>
        <br>
        <br>

        <button type="submit" value="Register" class="form-primary">Register</button>
    </form>
    <h3>Already Register please login!!</h3>
    <a href="login.jsp"><button>Login</button></a>
</div>
</body>
</html>