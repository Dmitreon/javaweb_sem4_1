<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add New User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            background-color: #f4f4f4;
        }
        .form-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }
        .error-message {
            color: red;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Add New User</h2>
    <c:if test="${not empty sessionScope.error}">
        <div class="error-message">${sessionScope.error}</div>
        <c:remove var="error" scope="session"/>
    </c:if>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="ADD_USER"/>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required/><br/>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required/><br/>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required/><br/>
        <input type="submit" value="Add User"/>
    </form>
    <a href="${pageContext.request.contextPath}/pages/main/main.jsp">Back</a>
</div>
</body>
</html>
