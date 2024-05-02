<%--
  Created by IntelliJ IDEA.
  User: 37063
  Date: 24.04.2024
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<h2>Register New Account</h2>

<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="register_user"/>
    Username: <input type="text" name="username" required /><br/>
    Password: <input type="password" name="password" required /><br/>
    Email: <input type="email" name="email" required /><br/>
    <input type="submit" value="Register"/>
</form>

<a href="${pageContext.request.contextPath}/index.jsp">Return to Home Page</a>

</body>
</html>



