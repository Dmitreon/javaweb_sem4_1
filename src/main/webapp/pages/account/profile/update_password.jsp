<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update Password</title>
    <style>
        .error-msg { color: red; }
    </style>
</head>
<body>
<h2>Update Password</h2>

<c:if test="${not empty errorMessage}">
    <div class="error-msg">
            ${errorMessage}
    </div>
</c:if>

<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="UPDATE_PASSWORD">
    <div>
        <label for="currentPassword">Current Password:</label>
        <input type="password" id="currentPassword" name="currentPassword" required>
    </div>
    <div>
        <label for="newPassword">New Password:</label>
        <input type="password" id="newPassword" name="newPassword" required>
    </div>
    <div>
        <label for="confirmPassword">Confirm New Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
    </div>
    <div>
        <button type="submit">Update</button>
    </div>
</form>

<a href="${pageContext.request.contextPath}/pages/main/main.jsp">Back</a>

</body>
</html>
