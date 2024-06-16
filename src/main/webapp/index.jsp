<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
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
        .form-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        .error-message {
            color: red;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Hello User!</h2>
    <form action="controller">
        <input type="hidden" name="command" value="login"/>
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" value=""/><br/>
        <label for="pass">Password:</label>
        <input type="password" id="pass" name="pass" value=""/><br/>
        <input type="submit" name="sub" value="Push"/><br/>
        <c:if test="${not empty login_msg}">
            <div class="error-message">${login_msg}</div>
        </c:if>
    </form>
    <p>Not registered yet? <a href="${pageContext.request.contextPath}/pages/account/register/register.jsp">Sign up here!</a></p>
</div>
</body>
</html>
