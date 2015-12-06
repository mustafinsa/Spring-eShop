<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login page</title>

    <spring:url value="/resources/core/css/hello.css" var="coreCss"/>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${coreCss}" rel="stylesheet"/>
</head>
<body onload='document.f.username.focus();'>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">eShop catalog</a>
        </div>
        <div class="navbar-right">
            <a class="navbar-brand" href="itemcart">In
                cart ${sessionScope.itemCart != null ? sessionScope.itemCart.size() : 0 } items</a>
        </div>
        <div class="navbar-right">
            <a class="navbar-brand" href="login">login</a>
        </div>
    </div>
</nav>
<br/><br/><br/><br/><br/><br/>
<div class="container" style="width: 500px;">
    <div class="control-box">
        <c:if test="${param.error != null}">
            <span class="loginError">Login failed. Check that username and password are correct.</span>
        </c:if>

        <form class="formTable" name='f' action='login' method='POST'>
            <table>
                <tr>
                    <td>User:</td>
                    <td><input type='text' name='username'></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type='text' name='password'/></td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan='2'><input name="submit" type="submit" value="Login"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>

<hr>
<footer>
    <p>© website.com 2015</p>
</footer>


<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs"/>

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>
