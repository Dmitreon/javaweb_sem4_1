<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New User</title>
</head>
<body>

<h2>Add New User</h2>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="add_user"/>
    Username: <input type="text" name="username" required/><br/>
    Password: <input type="password" name="password" required/><br/>
    Email: <input type="email" name="email"/><br/>
    <input type="submit" value="Add User"/>
</form>

<a href="${pageContext.request.contextPath}/pages/main/main.jsp">Back</a>

</body>
</html>



