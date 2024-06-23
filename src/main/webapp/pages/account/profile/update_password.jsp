<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="update_password_title"/></title>
    <style>
        .error-msg { color: red; }
    </style>
</head>
<body>
<h2><fmt:message key="update_password_title"/></h2>
<c:if test="${not empty errorMessage}">
    <div class="error-msg">
            ${errorMessage}
    </div>
</c:if>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="UPDATE_PASSWORD">
    <div>
        <label for="currentPassword"><fmt:message key="current_password"/>:</label>
        <input type="password" id="currentPassword" name="currentPassword" required>
    </div>
    <div>
        <label for="newPassword"><fmt:message key="new_password"/>:</label>
        <input type="password" id="newPassword" name="newPassword" required>
    </div>
    <div>
        <label for="confirmPassword"><fmt:message key="confirm_new_password"/>:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
    </div>
    <div>
        <button type="submit"><fmt:message key="update"/></button>
    </div>
</form>
<button onclick="history.back()"><fmt:message key="back"/></button>
</body>
</html>
