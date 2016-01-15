<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>
</head>
<body>
<div class="header">
    <a class="title" href="${pageContext.request.contextPath}/">eShop catalog</a>

    <sec:authorize access="!isAuthenticated()">
        <a class="login" href="login">Log in</a>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <form class="login" action="logout" method="post">
            <input type="submit" value="Logout"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </sec:authorize>
</div>
<p align="center">Your logged out!</p>
</body>
</html>