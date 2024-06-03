<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>View Images</title>
</head>
<body>

<h1>Available Images</h1>

<c:if test="${not empty images}">
    <c:forEach var="image" items="${images}">
        <div>
            <img src="data:image/jpeg;base64,${image.base64Data}" alt="Image ${image.id}" width="100" height="100"/>
        </div>
    </c:forEach>
</c:if>

<c:if test="${empty images}">
    <p>No images available.</p>
</c:if>

<a href="${pageContext.request.contextPath}/pages/main/main.jsp">Back to Main Page</a>

</body>
</html>
