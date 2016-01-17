<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html" charset="utf-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
    <title>Login page</title>
</head>
<body onload='document.f.username.focus();'>
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
        <c:if test="${param.error != null}">
            <span class="loginError">Login failed. Check that username and password are correct.</span>
        </c:if>
        <form class="formTable" name='f' action='login' method='POST'>
            <fieldset>
                <legend>Log in</legend>
                <label for="username">User: </label><input type='text' id='username' name="username" class="login">
                <label for="password">Password: </label><input type='password' id="password" name='password'
                                                               class="login"/>
                <label for="remember-me">Remember me: </label><input type='checkbox' name='remember-me' id="remember-me"
                                                                     checked="checked" class="login"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input name="submit" id="submit" type="submit" value="Login"/>

            </fieldset>
        </form>

        <a href="${pageContext.request.contextPath}/newAccount">Create new account</a>
    </div>
    <div class="footer">
        <hr>
        <p>© website.com 2015</p>
    </div>
</div>
</body>
</html>
