<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Shopping cart</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/main.css">
    </head>
<body>
    <div class="wrapper">
        <header>
            <p class="text_header">Shoping cart example</p>
            <a href="/" class="link_button blue">back to the shop</a>
        </header>
        <main>
            <jsp:useBean id="cart" scope="session" class="abnod.webcart.model.Cart"/>
            <c:choose>
                <c:when test="${cart.hasProducts}">
                    <h2 class="centered">Product cart is empty</h2>
                </c:when>
                <c:otherwise>
                    <table border="1" cellpadding="5">
                        <caption><h2>Products in cart</h2></caption>
                        <tr>
                            <th class="title_column">Title</th>
                            <th>Description</th>
                            <th class="price_column">Price</th>
                            <th class="price_column">Quantity</th>
                            <th class="price_column">Sum</th>
                            <th class="button_column"></th>
                        </tr>
                            <%--@elvariable id="products" type="java.util.List"--%>
                        <c:forEach items="${cart.cartItems}" var="entry">
                            <tr>
                                <td class="centered"><c:out value="${entry.key.title}"/></td>
                                <td class="centered"><c:out value="${entry.key.description}"/></td>
                                <td class="centered"><c:out value="${entry.key.price}"/></td>
                                <td class="centered"><c:out value="${entry.value}"/></td>
                                <td class="centered"><c:out value="${entry.value * entry.key.price}"/></td>
                                <td class="centered">
                                    <a href="/remove?id=<c:out value='${entry.key.id}'/>" class="link_button red">remove</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <a href="/clear" class="link_button red remove">Remove all</a>
                    <form action="/order" method="post">
                        <span>Name</span>
                        <input type="text" name="name" required>
                        <span>Surname</span>
                        <input type="text" name="surname" required>
                        <span>Phone number</span>
                        <input type="tel" name="phone" required>
                        <input type="submit" value="Checkout" class="link_button green"/>
                    </form>
                </c:otherwise>
            </c:choose>
        </main>
    </div>
</body>
</html>
