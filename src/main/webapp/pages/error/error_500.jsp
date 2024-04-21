<%--
  Created by IntelliJ IDEA.
  User: 37063
  Date: 20.03.2024
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500</title>
</head>
<body>
Request from : ${pageContext.errorData.requestURI} is failed <br/>
Servlet name: ${pageContext.errorData.servletName} <br/>
Status code: ${pageContext.errorData.statusCode} <br/>
Exception: ${pageContext.exception} <br/>
<br/><br/><br/>
Message from exception: ${error_msg}
</body>
</html>
