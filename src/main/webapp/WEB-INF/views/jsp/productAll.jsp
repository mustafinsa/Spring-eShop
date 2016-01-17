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
                <th>№</th>
                <th>Product</th>
                <th>Price</th>
                <th></th>
            </tr>

            <c:forEach var="product" items="${requestScope.productList}" varStatus="index">
                <tr>
                    <td><c:out value="${index.count}"/></td>
                    <td><a href="product?id=${product.id}"><c:out value="${product.name}"/></a></td>
                    <td><c:out value="${product.price}"/></td>
                    <td class="addToCart">
                        <form id="productAdded" method="post">
                            <input type="hidden" name="itemId" value="${product.id}"/>
                            <input id="submit" type="submit" value="Add to cart" content="${product.id}"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="footer">
        <hr>
        <p>© website.com 2015</p>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
    $(
            $("table").on("click", "#submit", function (e) {
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
                    "success": function () {
                        alert("success " + productId);
                    },
                    "error": function () {
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
