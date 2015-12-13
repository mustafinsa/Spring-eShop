<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>eShop catalog</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
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

<div class="content">
    <table class="products">
        <tr>
            <th class="label">Name</th>
            <th class="label">Items in stock</th>
            <th class="label">Price</th>
            <th class="label"></th>
        </tr>
        <tr>
            <td class="label"><c:out value="${requestScope.product.name}"/></td>
            <td class="label"><c:out value="${requestScope.product.quantity}"/></td>
            <td class="label"><c:out value="${requestScope.product.price}"/></td>
            <td class="label">
                <form name="Cart" action="itemcart" method="post">
                    <input type="hidden" name="act" value="add"/>
                    <input type="hidden" name="itemId" value="${product.id}"/>
                    <input type="submit" value="Add to cart"/>
                </form>
            </td>
        </tr>
    </table>
</div>

<hr>
<footer>
    <p>© website.com 2015</p>
</footer>
</body>
</html>
