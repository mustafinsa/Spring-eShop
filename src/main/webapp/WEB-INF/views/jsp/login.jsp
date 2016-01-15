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
<div align="center">
    <h2>Log in page</h2>

    <c:if test="${param.error != null}">
        <span class="loginError">Login failed. Check that username and password are correct.</span>
    </c:if>
</div>
<div align="center">
    <form class="formTable" name='f' action='login' method='POST'>
        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='username'></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password'/></td>
            </tr>
            <tr>
                <td>Remember me:</td>
                <td><input type='checkbox' name='remember-me' checked="checked"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input name="submit" type="submit" value="Login"/></td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <div class="label" >
        <a href="${pageContext.request.contextPath}/newAccount">Create new account</a>
    </div>
</div>
</body>
</html>
