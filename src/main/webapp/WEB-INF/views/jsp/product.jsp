<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>eShop catalog</title>

    <spring:url value="/resources/core/css/hello.css" var="coreCss" />
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${coreCss}" rel="stylesheet" />
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">eShop catalog</a>
        </div>
        <div class="navbar-right">
            <a class ="navbar-brand" href="itemcart">In cart ${sessionScope.itemCart != null ? sessionScope.itemCart.size() : 0 } items</a>
        </div>
        <div class="navbar-right">
            <a class="navbar-brand" href="login">login</a>
        </div>
    </div>
</nav>

<br/><br/><br/><br/><br/><br/>

<div>
<table align="center" border="1px" cellspacing="0px" cellpadding="0px">
    <tr>
        <th width="100px">Name</th>
        <th width="100px">Items in stock</th>
        <th width="50px">Price</th>
        <th width="50px"></th>
    </tr>
    <tr>
        <td><c:out value="${requestScope.product.name}"/></td>
        <td><c:out value="${requestScope.product.quantity}"/></td>
        <td><c:out value="${requestScope.product.price}"/></td>
        <td>
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


<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>
