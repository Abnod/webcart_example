<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order success!</title>
    <link rel="stylesheet" href="<c:url value="${pageContext.request.contextPath}/main.css"/>">
</head>
<body>
    <div class="wrapper">
        <header>
            <p class="text_header">Shoping cart example</p>
            <a href="/" class="link_button blue">back to the shop</a>
        </header>
        <main>
            <div class="centered">
                <h2>Your order accepted</h2>
                <p>Order number: <c:out value="${orderid}"/></p>
            </div>
        </main>
    </div>
</body>
</html>
