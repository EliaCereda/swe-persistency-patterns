<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--@elvariable id="user" type="model.User"--%>
<c:set var="isUpdate" value="${(user != null) ? true : false}" />
<c:set var="title" value="${isUpdate ? 'Update user account' : 'Create a new account'}" />
<c:set var="submitText" value="${isUpdate ? 'Update' : 'Create'}" />

<html>
<head>
    <title><c:out value="${title}" /></title>
</head>
<body>
    <h1><c:out value="${title}" /></h1>

    <form method="post">
        <p>
            <label for="username">Username: </label>
            <input type="text" name="username" id="username" value="${user.username}" ${isUpdate ? "readonly" : "required"}><br>

            <label for="password">Password: </label>
            <input type="password" name="password" id="password" value="${user.password}" required><br>
        </p>

        <p>
            <label for="name">Name: </label>
            <input type="text" name="name" id="name" value="${user.name}" required><br>
        </p>

        <button type="submit"><c:out value="${submitText}" /></button>
    </form>

    <p>
        Go <a href="${pageContext.request.contextPath}/">back</a>.
    </p>
</body>
</html>
