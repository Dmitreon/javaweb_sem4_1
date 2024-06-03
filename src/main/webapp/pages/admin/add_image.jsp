<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Image</title>
</head>
<body>

<h1>Add New Image</h1>

<form action="${pageContext.request.contextPath}/controller" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="ADD_PREDEFINED_IMAGE"/>
    <input type="file" name="image" accept="image/*" required/>
    <input type="submit" value="Upload"/>
</form>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

</body>
</html>
