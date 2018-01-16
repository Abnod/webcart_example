<%@ page isErrorPage="true" import="java.io.*" contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Error page</title>
    </head>
    <body>
        <h1>Error</h1>
        <h2><%=exception.getMessage() %><br/></h2>
    </body>
</html>
