<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add New Image</title>
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
    <h2>Add New Image</h2>
    <form action="${pageContext.request.contextPath}/controller" method="post" enctype="multipart/form-data">
        <input type="hidden" name="command" value="ADD_PREDEFINED_IMAGE"/>
        <input type="file" name="image" accept="image/*" required/><br/>
        <input type="submit" value="Upload"/>
    </form>
    <c:if test="${not empty error}">
        <div class="error-message">${error}</div>
    </c:if>
    <a href="${pageContext.request.contextPath}/pages/main/main.jsp">Back</a>
</div>
</body>
</html>
