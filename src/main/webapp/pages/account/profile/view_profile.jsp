<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
        }
        .profile-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            position: relative;
            max-width: 400px;
            width: 100%;
        }
        .profile-picture {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            object-fit: cover;
            margin-bottom: 20px;
        }
        h2 {
            margin-top: 0;
        }
        .profile-details p {
            margin: 10px 0;
        }
        .profile-details a {
            display: block;
            margin: 5px 0;
            color: #007BFF;
            text-decoration: none;
        }
        .profile-details a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="profile-container">
    <img src="${profileImagePath}" alt="Profile Picture" class="profile-picture">
    <h2>User Profile</h2>
    <div class="profile-details">
        <p><b>Username:</b> ${user.username}</p>
        <p><b>Email:</b> ${user.email}</p>
        <a href="${pageContext.request.contextPath}/controller?command=UPDATE_PASSWORD">Edit Password</a>
        <a href="${pageContext.request.contextPath}/controller?command=DELETE_OWN_ACCOUNT">Delete My Account</a>
        <a href="${pageContext.request.contextPath}/controller?command=VIEW_IMAGES">View Available Images</a>
        <a href="${pageContext.request.contextPath}/pages/main/main.jsp">Back</a>
    </div>
</div>
</body>
</html>
