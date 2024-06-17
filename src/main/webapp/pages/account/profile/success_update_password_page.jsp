<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="password_update_success_title"/></title>
    <meta http-equiv="refresh" content="3;${pageContext.request.contextPath}/controller?command=VIEW_PROFILE">
</head>
<body>
<h1><fmt:message key="password_update_success_message"/></h1>
<p><fmt:message key="redirecting_to_profile"/></p>
</body>
</html>
