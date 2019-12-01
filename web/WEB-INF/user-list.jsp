<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="users" type="java.util.List<model.User>"--%>
<%--@elvariable id="addresses" type="java.util.Map<String, model.Address>"--%>

<%--@elvariable id="field" type="String"--%>
<%--@elvariable id="query" type="String"--%>

<c:set var="isSearchResults" value="${(field != null && query != null) ? true : false}" />
<c:set var="noResultsText" value="${(isSearchResults) ? 'The search produced no results.' : 'No users registered.'}" />

<html>
<head>
    <title>Registered Users</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">

    <script>
        function deleteUser(username) {
            return confirm("Are you sure you want to delete the user '" + username + "'?");
        }

        function resetSearch() {
            window.location = "${pageContext.request.contextPath}/users";
            return false;
        }
    </script>
</head>
<body>
    <h1>Registered Users</h1>

    <form onreset="return resetSearch()">
        <fieldset>
            <legend>Search</legend>
            <label>
                <input type="radio" name="field" value="name" ${field == 'name' ? 'checked' : ''}>
                by name
            </label>
            <label>
                <input type="radio" name="field" value="address" ${field == 'address' ? 'checked' : ''}>
                by address
            </label>
            <label>
                <input type="radio" name="field" value="best_friend" ${field == 'best_friend' ? 'checked' : ''}>
                by best friend
            </label>

            <span style="display: inline-block; width: 100px"></span>

            <input type="text" name="query" placeholder="Insert query…" value="${query}">

            <button type="submit">Search</button>
            <button type="reset">Clear</button>
        </fieldset>
    </form>

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
                        <td class="actions">
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
            <c:out value="${noResultsText}" />
        </c:otherwise>
    </c:choose>

    <p>Go <a href="${pageContext.request.contextPath}/">back</a>.</p>
</body>
</html>
