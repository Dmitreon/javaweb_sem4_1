<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="user_list_title"/></title>
</head>
<body>
<h2><fmt:message key="user_list_title"/></h2>
<table border="1">
    <thead>
    <tr>
        <th><fmt:message key="user_list_id"/></th>
        <th><fmt:message key="user_list_username"/></th>
        <th><fmt:message key="user_list_password"/></th>
        <th><fmt:message key="user_list_email"/></th>
        <th><fmt:message key="user_list_actions"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>********</td>
            <td>${user.email}</td>
            <td>
                <c:if test="${sessionScope.currentUser.role == 'admin'}">
                    <c:if test="${user.id != sessionScope.currentUser.id}">
                        <a href="${pageContext.request.contextPath}/pages/admin/confirm_delete.jsp?userId=${user.id}"><fmt:message key="user_list_delete"/></a>
                    </c:if>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/pages/main/main.jsp"><fmt:message key="back"/></a>
</body>
</html>
