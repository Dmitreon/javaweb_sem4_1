<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="register_title"/></title>
</head>
<body>
<h2><fmt:message key="register_title"/></h2>
<c:if test="${not empty sessionScope.error}">
    <p style="color: red;">${sessionScope.error}</p>
    <c:remove var="error" scope="session"/>
</c:if>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="register_user"/>
    <label for="username"><fmt:message key="username"/>:</label>
    <input type="text" id="username" name="username" required /><br/>
    <label for="password"><fmt:message key="password"/>:</label>
    <input type="password" id="password" name="password" required /><br/>
    <label for="email"><fmt:message key="email"/>:</label>
    <input type="email" id="email" name="email" required /><br/>
    <input type="submit" value="<fmt:message key="register"/>"/>
</form>
<a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="return_to_home"/></a>
</body>
</html>
