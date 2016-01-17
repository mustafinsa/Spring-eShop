<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>
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
        <p>Your logged out! Go to <a href="${pageContext.request.contextPath}/">home page</a></p>
    </div>

    <div class="footer">
        <hr>
        <p>© website.com 2015</p>
    </div>
</div>
</body>
</html>