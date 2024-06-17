<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            background-color: #f4f4f4;
            height: 100vh;
        }
        .main-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        a, input[type="submit"] {
            margin: 10px 0;
            display: block;
        }
    </style>
</head>
<body>
<div class="main-container">
    <h2>Welcome!</h2>
    <a href="${pageContext.request.contextPath}/controller?command=VIEW_PROFILE">View Profile</a>
    <a href="${pageContext.request.contextPath}/controller?command=VIEW_USERS">View List of Users</a>

    <c:choose>
        <c:when test="${not empty sessionScope.currentUser and sessionScope.currentUser.role == 'admin'}">
            <a href="${pageContext.request.contextPath}/controller?command=ADD_USER">Add New User</a>
            <a href="${pageContext.request.contextPath}/pages/admin/add_image.jsp">Add New Image</a>
        </c:when>
    </c:choose>

    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="LOGOUT"/>
        <input type="submit" value="Log Out"/>
    </form>
</div>
</body>
</html>
