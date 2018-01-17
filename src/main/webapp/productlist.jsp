<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product list - cart example</title>
</head>
<body>
    <div class="wrapper">
        <header>
            Shoping cart example
            <a href="cart" class="link_button">Shopping cart</a>
        </header>
        <main>
            <c:choose>
                <%--@elvariable id="products" type="java.util.List"--%>
                <c:when test="${empty products}">
                    <p>There are no any products in shop</p>>
                    <a href="fill" class="link_button">Fill with test products</a>
                </c:when>
                <c:otherwise>
                    <table border="1" cellpadding="5">
                        <caption><h2>List of products</h2></caption>
                        <tr>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th></th>
                        </tr>
                            <%--@elvariable id="products" type="java.util.List"--%>
                        <c:forEach items="${products}" var="product">
                            <tr>
                                <td><c:out value="${product.title}"/></td>
                                <td><c:out value="${product.description}"/></td>
                                <td><c:out value="${product.price}"/></td>
                                <td>
                                    <a href="/add?id=<c:out value='${product.id}' />" class="link_button">add to cart</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
        </main>
        <footer>
        </footer>
    </div>
</body>
</html>
