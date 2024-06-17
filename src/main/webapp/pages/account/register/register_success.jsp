<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="registration_success_title"/></title>
</head>
<body>
<h2><fmt:message key="registration_success_title"/></h2>
<p><fmt:message key="registration_success_message"/></p>
<a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="return_to_home"/></a>
</body>
</html>
