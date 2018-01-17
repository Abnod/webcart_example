<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Shopping cart</title>
    </head>
<body>
    <div class="wrapper">
        <main>
            <jsp:useBean id="cart" scope="session" class="abnod.webcart.model.Cart"/>
            <a href="/" class="link_button">back to product list</a>
            <c:choose>
                <c:when test="${cart.hasProducts}">
                    <p>Product cart is empty</p>
                </c:when>
                <c:otherwise>
                    <table border="1" cellpadding="5">
                        <caption><p class="text_header">Products in cart</p></caption>
                        <tr>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Sum</th>
                            <th></th>
                        </tr>
                            <%--@elvariable id="products" type="java.util.List"--%>
                        <c:forEach items="${cart.cartItems}" var="entry">
                            <tr>
                                <td><c:out value="${entry.key.title}"/></td>
                                <td><c:out value="${entry.key.description}"/></td>
                                <td><c:out value="${entry.key.price}"/></td>
                                <td><c:out value="${entry.value}"/></td>
                                <td><c:out value="${entry.value * entry.key.price}"/></td>
                                <td>
                                    <a href="/remove?id=<c:out value='${entry.key.id}'/>" class="link_button">remove from cart</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <a href="/clear" class="link_button">Remove all</a>
                    <form action="/order" method="post">
                        <p>Name</p>
                        <input type="text" name="name" required>
                        <p>Surname</p>
                        <input type="text" name="surname" required>
                        <p>Phone number</p>
                        <input type="tel" name="phone" required>
                        <input type="submit" value="Checkout" class="order_button"/>
                    </form>
                </c:otherwise>
            </c:choose>
        </main>
    </div>
</body>
</html>
