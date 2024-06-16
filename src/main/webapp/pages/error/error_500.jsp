<%--
  Created by IntelliJ IDEA.
  User: 37063
  Date: 20.03.2024
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>500 - Internal Server Error</title>
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
        .error-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
            text-align: center;
        }
        .error-details {
            text-align: left;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h2>500 - Internal Server Error</h2>
    <p>Request from: ${pageContext.errorData.requestURI} is failed</p>
    <p>Servlet name: ${pageContext.errorData.servletName}</p>
    <p>Status code: ${pageContext.errorData.statusCode}</p>
    <p>Exception: ${pageContext.exception}</p>
    <div class="error-details">
        <p>Message from exception: ${error_msg}</p>
    </div>
    <button onclick="history.back()">Go Back</button>
</div>
</body>
</html>
