<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>

<h1>Welcome, ${user_name}!</h1>

<a href="${pageContext.request.contextPath}/controller?command=view_users">View List of Users</a><br/>

<a href="${pageContext.request.contextPath}/pages/add_user.jsp">Add New User</a><br/>

<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="Log Out"/>
</form>

</body>
</html>