<%-- 
    Document   : Search
    Created on : 2021-6-12, 17:43:01
    Author     : 沈海健
--%>

<%@page contentType="text/html" pageEncoding="GB2312"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>网上书店</title>
    </head>
    <body>
        <h1>图书查询：请指定书名、作者或出版社</h1>
        <form method="post" action="http://localhost:8080/MyBookstoreSystem-war/CtrlServlet">
            <input type="text" name="key"><p>
            <input type="submit" name="submit" value="模糊查询">
            <input type="hidden" name="page" value="Search" >
        </form>
    </body>
</html>
