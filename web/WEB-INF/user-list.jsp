<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="users" type="java.util.List<model.User>"--%>
<%--@elvariable id="addresses" type="java.util.Map<String, model.Address>"--%>

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
        form {
            display: inline;
        }
    </style>

    <script>
        function deleteUser(username) {
            return confirm("Are you sure you want to delete the user '" + username + "'?");
        }
    </script>
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
                    <th>Address</th>
                    <th>Best Friend</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <c:set var="address" value="${addresses.get(user.username)}" />

                    <tr>
                        <td><c:out value="${user.username}"/></td>
                        <td><c:out value="${user.password}"/></td>
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${address.streetAddress}" default="(none)"/></td>
                        <td><c:out value="${user.bestFriend}" default="(none)" /></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/users/${user.username}">
                                <button type="submit">Update</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/delete-user" method="post"
                                  onsubmit="return deleteUser('${user.username}')">
                                <input type="hidden" name="username" value="${user.username}">
                                <button type="submit">Delete</button>
                            </form>
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
