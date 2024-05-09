<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
<h2>User Profile:</h2>
<p><b>Username:</b> ${user.username}</p>
<p><b>Email:</b> ${user.email}</p>

<a href="${pageContext.request.contextPath}/controller?command=UPDATE_PASSWORD">Edit Password</a>

<a href="${pageContext.request.contextPath}/controller?command=DELETE_OWN_ACCOUNT">Delete My Account</a>

<a href="${pageContext.request.contextPath}/pages/main/main.jsp">Back</a>
</body>
</html>
