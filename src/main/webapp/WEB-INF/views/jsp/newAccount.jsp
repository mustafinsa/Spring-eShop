<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html" charset="utf-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery-1.11.3.min.js"></script>
    <title>Create account</title>

    <script type="text/javascript">
        function onLoad() {
            $("#password").keyup(checkPasswordMatch);
            $("#confirmPassword").keyup(checkPasswordMatch);

            $("#details").submit(canSubmit());
        }

        function canSubmit() {
            var password = $("#password").val();
            var confirmPassword = $("#confirmPassword").val();
            return password == confirmPassword;
        }

        function checkPasswordMatch() {
            var password = $("#password").val();
            var confirmPassword = $("#confirmPassword").val();
            if (password.length > 3 || confirmPassword.length > 3) {
                if (password == confirmPassword) {
                    $("#matchpass").text("<fmt:message key='MatchedPasswords.user.password'/>");
                    $("#matchpass").addClass("valid");
                    $("#matchpass").removeClass("error")
                } else {
                    $("#matchpass").text("<fmt:message key='UnmatchedPasswords.user.password'/>");
                    $("#matchpass").addClass("error");
                    $("#matchpass").removeClass("valid")
                }
            }
        }
        $(document).ready(onLoad)
    </script>

</head>
<body>
<%--<div class="header">
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
</div>--%>
<table class="formTable">
    <sf:form id="details" action="${pageContext.request.contextPath}/createAccount" method="POST" commandName="user">
        <tr>
            <td class="label">Username</td>
            <td>
                <sf:input path="username" class="control" type="text"/><br/>

                <div class="error"><sf:errors path="username"/></div>
            </td>
        </tr>
        <tr>
            <td class="label">Email</td>
            <td>
                <sf:input path="email" class="control" type="text"/><br/>

                <div class="error"><sf:errors path="email"/></div>
            </td>
        </tr>
        <tr>
            <td class="label">Password</td>
            <td>
                <sf:input id="password" path="password" class="control" type="password"/><br/>

                <div class="error"><sf:errors path="password"/></div>
            </td>
        </tr>
        <tr>
            <td class="label">Confirm password</td>
            <td>
                <input id="confirmPassword" name="confirmPassword" class="control" type="password"/>

                <div id="matchpass"></div>
            </td>
        </tr>
        <tr>
            <td class="label"></td>
            <td><input type="submit" class="control" value="Create account"/></td>
        </tr>

    </sf:form>
</table>

</body>
</html>
