<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Product list - cart example</title>
    </head>
    <body>
        <header>
            Shoping cart example
        </header>
        <main>
            <%--<% if (((List) request.getAttribute("productList")).isEmpty()) {%>--%>
            <%--<p>There are no any products in shop</p>--%>
            <%--<a href="fill">Fill with test products</a>--%>
            <%--<%} else { %>--%>
            <table border="1" cellpadding="5">
                <caption><h2>List of Books</h2></caption>
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
                <c:forEach items="${productList}" var="product">
                    <tr>
                        <%--<td><c:out value="${product.getId}"/></td>--%>
                        <td><c:out value="${product.id}"/></td>
                        <td><c:out value="${product.description}"/></td>
                        <td><c:out value="${product.price}"/></td>
                        <td>
                            <a href="/edit?id=<c:out value='${product.id}' />">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/delete?id=<c:out value='${product.id}' />">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <%--<% } %>--%>
        </main>
        <footer>
            Shoping cart example
        </footer>
    </body>
</html>
