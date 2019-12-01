<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="message" type="String"--%>
<%--@elvariable id="username" type="String"--%>

<html>
<head>
    <title>Persistency Patterns &mdash; Login</title>
</head>
<body>
    <h1>Login</h1>

    <form method="post">
        <p>
            <c:out value="${message}" />
        </p>
        <p>
            <label for="username">Username</label>
            <input type="text" name="username" id="username" value="${username}">
            <br>

            <label for="password">Password</label>
            <input type="password" name="password" id="password">
            <br>
        </p>

        <button type="submit">Login</button>
    </form>

    <p>
        Go <a href="<c:url value="/"/>">back</a>.
    </p>
</body>
</html>
