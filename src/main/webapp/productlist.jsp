<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product list - cart example</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/main.css">
</head>
<body>
    <div class="wrapper">
        <header>
            <p class="text_header">Shoping cart example</p>
            <a href="${pageContext.request.contextPath}/cart" class="link_button blue">Shopping cart</a>
        </header>
        <main>
            <c:choose>
                <%--@elvariable id="products" type="java.util.List"--%>
                <c:when test="${empty products}">
                    <h2 class="centered">There are no any products in shop</h2>
                    <br>
                    <a href="fill" class="link_button green centerbutton">Fill with test products</a>
                </c:when>
                <c:otherwise>
                    <table border="1" cellpadding="5">
                        <caption><h2>List of products</h2></caption>
                        <tr>
                            <th class="title_column">Title</th>
                            <th>Description</th>
                            <th class="price_column">Price</th>
                            <th class="button_column"></th>
                        </tr>
                            <%--@elvariable id="products" type="java.util.List"--%>
                        <c:forEach items="${products}" var="product">
                            <tr>
                                <td class="centered"><c:out value="${product.title}"/></td>
                                <td><c:out value="${product.description}"/></td>
                                <td class="centered"><c:out value="${product.price}"/></td>
                                <td class="centered">
                                    <a href="/add?id=<c:out value='${product.id}' />" class="link_button red">add to cart</a>
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
