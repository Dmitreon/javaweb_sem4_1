<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm Delete</title>
</head>
<body>
<h1>Confirm Delete</h1>
<p>Are you sure you want to delete this user? Please enter the username to confirm.</p>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="DELETE_USER"/>
    <input type="hidden" name="userId" value="${param.userId}"/>
    <input type="text" name="username" required placeholder="Enter username"/>
    <input type="submit" value="Confirm"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=VIEW_USERS">Cancel</a>
</body>
</html>
