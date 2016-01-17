<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
</head>
<body>
<div class="wrapper">

    <div class="header">
        <a class="title" href="${pageContext.request.contextPath}/">eShop catalog</a>

        <div class="login">
            <sec:authorize access="!isAuthenticated()">
                <a href="login">Log in</a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <form action="logout" method="post">
                    <input type="submit" value="Logout"/>
                    <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </sec:authorize>
        </div>
    </div>

    <div class="content">
        <table>
            <tr>
                <td>Username</td>
                <td>Email</td>
                <td>Role</td>
                <td>Enabled</td>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.authority}</td>
                    <td>${user.enabled}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="footer">
        <hr>
        <p>© website.com 2015</p>
    </div>
</div>
</body>
</html>
