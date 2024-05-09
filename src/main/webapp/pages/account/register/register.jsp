<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<h2>Register New Account</h2>

<c:if test="${not empty sessionScope.error}">
    <p style="color: red;">${sessionScope.error}</p>
    <c:remove var="error" scope="session"/>
</c:if>

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
