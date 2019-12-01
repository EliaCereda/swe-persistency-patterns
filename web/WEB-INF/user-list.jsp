<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="users" type="java.util.List<model.User>"--%>

<html>
<head>
    <title>Registered Users</title>

    <style>
        table {
            border-collapse: collapse;
        }
        td, th {
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
                    <th>Username</th>
                    <th>Password</th>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td><c:out value="${user.username}"/></td>
                        <td><c:out value="${user.password}"/></td>
                        <td><c:out value="${user.name}"/></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/users/${user.username}">Update</a>
                            <a href="${pageContext.request.contextPath}/users/${user.username}">Delete</a>
                        </td>
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
