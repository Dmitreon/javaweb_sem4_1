<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>View Images</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }
        .image-gallery {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .image-container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .image-container img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            object-fit: cover;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<h1>Available Images</h1>

<c:if test="${not empty images}">
    <div class="image-gallery">
        <c:forEach var="image" items="${images}">
            <div class="image-container">
                <img src="data:image/jpeg;base64,${image.base64Data}" alt="Image ${image.id}"/>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="SET_USER_IMAGE"/>
                    <input type="hidden" name="imageId" value="${image.id}"/>
                    <input type="submit" value="Set as Profile Picture"/>
                </form>
            </div>
        </c:forEach>
    </div>
</c:if>

<c:if test="${empty images}">
    <p>No images available.</p>
</c:if>

<a href="${pageContext.request.contextPath}/pages/main/main.jsp">Back to Main Page</a>
</body>
</html>
