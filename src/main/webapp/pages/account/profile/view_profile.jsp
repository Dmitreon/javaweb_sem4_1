<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="user_profile"/></title>
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
    <script>
        function changeLanguage(lang) {
            const url = new URL(window.location.href);
            url.searchParams.set('lang', lang);
            window.location.href = url.href;
        }
    </script>
</head>
<body>
<div class="profile-container">
    <img src="${profileImagePath}" alt="Profile Picture" class="profile-picture">
    <h2><fmt:message key="user_profile"/></h2>
    <div class="profile-details">
        <p><b><fmt:message key="username"/>:</b> ${user.username}</p>
        <p><b><fmt:message key="email"/>:</b> ${user.email}</p>
        <a href="${pageContext.request.contextPath}/controller?command=UPDATE_PASSWORD"><fmt:message key="edit_password"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=DELETE_OWN_ACCOUNT"><fmt:message key="delete_account"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=VIEW_IMAGES"><fmt:message key="view_images"/></a>
        <a href="${pageContext.request.contextPath}/pages/main/main.jsp"><fmt:message key="back"/></a>
    </div>
    <p><fmt:message key="change_language"/>:
        <a href="javascript:void(0)" onclick="changeLanguage('en')">English</a> |
        <a href="javascript:void(0)" onclick="changeLanguage('ru')">Русский</a>
    </p>
</div>
</body>
</html>
