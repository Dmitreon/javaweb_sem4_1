<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>

<h1>Welcome!</h1>

<a href="${pageContext.request.contextPath}/controller?command=VIEW_PROFILE">View Profile</a><br/>

<a href="${pageContext.request.contextPath}/controller?command=ADD_USER">Add New User</a><br/>

<a href="${pageContext.request.contextPath}/controller?command=VIEW_USERS">View List of Users</a><br/>

<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="LOGOUT"/>
    <input type="submit" value="Log Out"/>
</form>

</body>
</html>
