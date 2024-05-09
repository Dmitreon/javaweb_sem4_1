<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm Account Deletion</title>
</head>
<body>
<h1>Confirm Account Deletion</h1>
<p>Are you sure you want to delete your account? Please enter your username to confirm:</p>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="DELETE_OWN_ACCOUNT"/>
    <input type="text" name="username" required placeholder="Enter your username"/>
    <input type="submit" value="Confirm"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=VIEW_PROFILE">Cancel</a>
</body>
</html>
