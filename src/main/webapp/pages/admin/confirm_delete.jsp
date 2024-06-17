<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="confirm_delete_title"/></title>
</head>
<body>
<h1><fmt:message key="confirm_delete_title"/></h1>
<p><fmt:message key="confirm_delete_message"/></p>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="DELETE_USER"/>
    <input type="hidden" name="userId" value="${param.userId}"/>
    <input type="text" name="username" required placeholder="<fmt:message key="username"/>"/>
    <input type="submit" value="<fmt:message key="confirm"/>"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=VIEW_USERS"><fmt:message key="cancel"/></a>
</body>
</html>
