<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="user" type="model.User"--%>
<%--@elvariable id="address" type="model.Address"--%>
<%--@elvariable id="bestFriends" type="java.util.List<model.User>"--%>

<c:set var="isUpdate" value="${(user != null) ? true : false}" />
<c:set var="title" value="${isUpdate ? 'Update user account' : 'Create a new account'}" />
<c:set var="submitText" value="${isUpdate ? 'Update' : 'Create'}" />

<html>
<head>
    <title><c:out value="${title}" /></title>

    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
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

        <p>
            <fieldset>
                <legend>Address</legend>
                <label for="street_address">Street Address: </label>
                <input type="text" name="street_address" id="street_address" value="${address.streetAddress}" required><br>
            </fieldset>
        </p>

        <p>
            <label for="best_friend">Best Friend: </label>
            <select name="best_friend" id="best_friend">
                <option value="" ${user.bestFriend == null ? 'selected' : ''}>(none)</option>

                <c:forEach items="${bestFriends}" var="bestFriend">
                    <option value="${bestFriend.username}" ${user.bestFriend == bestFriend.username ? 'selected' : ''}>
                        <c:out value="${bestFriend.name}" />
                    </option>
                </c:forEach>
            </select>
        </p>

        <button type="submit"><c:out value="${submitText}" /></button>
    </form>

    <p>
        Go <a href="<c:url value="/"/>">back</a>.
    </p>
</body>
</html>
