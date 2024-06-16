<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Image Added</title>
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
        .success-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="success-container">
    <h2>Image added successfully!</h2>
    <a href="${pageContext.request.contextPath}/pages/admin/add_image.jsp">Add another image</a><br/>
    <a href="${pageContext.request.contextPath}/pages/main/main.jsp">Back</a>
</div>
</body>
</html>
