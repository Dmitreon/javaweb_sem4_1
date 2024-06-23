<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="view_images_title"/></title>
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
<h1><fmt:message key="view_images_title"/></h1>
<c:if test="${not empty images}">
    <div class="image-gallery">
        <c:forEach var="image" items="${images}">
            <div class="image-container">
                <img src="data:image/jpeg;base64,${image.base64Data}" alt="Image ${image.id}"/>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="SET_USER_IMAGE"/>
                    <input type="hidden" name="imageId" value="${image.id}"/>
                    <input type="submit" value="<fmt:message key="set_as_profile_picture"/>"/>
                </form>
            </div>
        </c:forEach>
    </div>
</c:if>
<c:if test="${empty images}">
    <p><fmt:message key="no_images_available"/></p>
</c:if>
<button onclick="history.back()"><fmt:message key="back"/></button>
</body>
</html>
