<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--@elvariable id="auth" type="controller.auth.AuthManager"--%>

<html>
<head>
    <title>Persistency Patterns &mdash; Home</title>

    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>
<body>
    <c:choose>
        <c:when test="${auth.isAuthenticated}">
            <h1>Welcome, <c:out value="${auth.currentUser.username}" />!</h1>

            <div class="actions">
                <form action="<c:url value="/logout"/>" method="post">
                    <button type="submit">Logout</button>
                </form>
            </div>

            <form action="<c:url value="/users"/>">
                <button type="submit">Show User List</button>
            </form>
        </c:when>
        <c:otherwise>
            <h1>Welcome, guest!</h1>

            <div class="actions">
                <form action="<c:url value="/login"/>">
                    <button type="submit">Login</button>
                </form>
                <form action="<c:url value="/signup"/>">
                    <button type="submit">Sign Up</button>
                </form>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>
