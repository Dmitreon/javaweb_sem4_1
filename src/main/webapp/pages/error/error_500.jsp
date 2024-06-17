<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title><fmt:message key="error_500_title"/></title>
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
    <h2><fmt:message key="error_500_title"/></h2>
    <p><fmt:message key="request_failed"><fmt:param value="${pageContext.errorData.requestURI}"/></fmt:message></p>
    <p><fmt:message key="servlet_name"><fmt:param value="${pageContext.errorData.servletName}"/></fmt:message></p>
    <p><fmt:message key="status_code"><fmt:param value="${pageContext.errorData.statusCode}"/></fmt:message></p>
    <p><fmt:message key="exception"><fmt:param value="${pageContext.exception}"/></fmt:message></p>
    <div class="error-details">
        <p><fmt:message key="exception_message"><fmt:param value="${error_msg}"/></fmt:message></p>
    </div>
    <button onclick="history.back()"><fmt:message key="go_back_button"/></button>
</div>
</body>
</html>
