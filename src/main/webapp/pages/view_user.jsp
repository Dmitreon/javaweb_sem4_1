<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>User List</title>
</head>
<body>

<h2>List of Users</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Password</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>********</td>
            <td>${user.email}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/pages/main.jsp">Back</a>

</body>
</html>
