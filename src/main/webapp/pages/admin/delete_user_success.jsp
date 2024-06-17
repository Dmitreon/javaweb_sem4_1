<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="user_delete_success_title"/></title>
</head>
<body>
<h1><fmt:message key="user_delete_success_title"/></h1>
<p><fmt:message key="user_delete_success_message"/></p>
<a href="${pageContext.request.contextPath}/controller?command=VIEW_USERS"><fmt:message key="view_remaining_users"/></a>
</body>
</html>
