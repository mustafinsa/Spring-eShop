<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>eShop catalog</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
    <sec:csrfMetaTags/>
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
            <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </sec:authorize>
</div>

<div class="content">
    <table class="products">
        <tr>
            <th class="label">№</th>
            <th class="label">Product</th>
            <th class="label">Price</th>
            <th class="label"></th>
        </tr>

        <c:forEach var="product" items="${requestScope.productList}" varStatus="index">
            <tr>
                <td class="label"><c:out value="${index.count}"/></td>
                <td class="label"><a href="product?id=${product.id}"><c:out value="${product.name}"/></a></td>
                <td class="label"><c:out value="${product.price}"/></td>
                <td class="label">
                    <form id="productAdded" method="post">
                        <input type="hidden" name="itemId" value="${product.id}"/>
                        <input id="submit" type="submit" value="Add to cart" content="${product.id}"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<hr>
<div class="footer">
    <p>© website.com 2015</p>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
    $(
        $(".products").on("click", "#submit", function(e) {
            e.preventDefault();
            var productId = $(this).attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            var token = $("meta[name='_csrf']").attr("content");
            $.ajax({
                "type": "POST",
                "url": "<c:url value="/postCart"/>",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                },
                "data": JSON.stringify({"target": productId}),
                "success": function() {
                    alert("success " + productId);
                },
                "error": function() {
                    alert("error")
                },
                "contentType": "application/json",
                "dataType": "json"
            });
        })
    )
</script>
</body>
</html>
