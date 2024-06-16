<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Success</title>
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
    <h2>Registration Successful</h2>
    <p>User has been successfully registered.</p>
    <a href="${pageContext.request.contextPath}/pages/main/main.jsp">Return to main Page</a>
</div>
</body>
</html>
