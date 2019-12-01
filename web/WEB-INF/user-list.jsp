<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="users" type="java.util.List<model.User>"--%>

<html>
<head>
    <title>Registered Users</title>

    <style>
        table{
            border-collapse: collapse;
        }
        table td {
            border: 1px solid #AAA;
            padding: 5px;
        }
    </style>
</head>
<body>
    <h1>Registered Users</h1>

    <c:choose>
        <c:when test="${users != null && users.size() > 0}">
            <table>
                <thead>
                <tr>
                    <td>Username</td>
                    <td>Password</td>
                    <td>Name</td>
                    <td>Actions</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td><c:out value = "${user.username}"/></td>
                        <td><c:out value = "${user.password}"/></td>
                        <td><c:out value = "${user.name}"/></td>
                        <td></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            No users registered.
        </c:otherwise>
    </c:choose>

    <p>Go <a href="${pageContext.request.contextPath}/">back</a>.</p>
</body>
</html>
