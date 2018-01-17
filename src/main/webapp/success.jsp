<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order success!</title>
</head>
<body>
    <div class="wrapper">
        <main>
            <div>
                <p class="text_header">Your order accepted</p>
                <p>Order number: <c:out value="${orderid}"/></p>
                <a href="/" class="link_button">Back to the shop</a>
            </div>
        </main>
    </div>
</body>
</html>
