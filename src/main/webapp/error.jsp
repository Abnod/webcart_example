<%@ page isErrorPage="true" import="java.io.*" contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Error page</title>
    <link rel="stylesheet" href="<c:url value="${pageContext.request.contextPath}/main.css"/>">
</head>
<body>
<div class="wrapper">
    <main>
        <h1 class="text_header">Error</h1>
        <h2><%=exception.getMessage() %><br/></h2>
    </main>
</div>
</body>
</html>
