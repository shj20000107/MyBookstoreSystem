<%-- 
    Document   : Error
    Created on : 2021-6-12, 18:26:36
    Author     : 沈海健
--%>

<%@page contentType="text/html" pageEncoding="GB2312"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>报错</title>
    </head>
    <body>
        <%
            String errMsg = (String)session.getAttribute("errMsg");
            if(errMsg.equals("NullKey")){
        %>
                <h1>您没有输入关键字!</h1>
        <%
            }
        %>
        <%
            if(errMsg.equals("EmptySearchBookList")){
        %>
                <h1>没有任何有效查询!</h1>
        <%
            }
        %>
        <%
            if(errMsg.equals("EmptyCartList")){
        %>
                <h1>您购物车没有图书!</h1>
        <%
            }
        %>
        <%
            if(errMsg.equals("FaultFormat")){
        %>
                <h1>请至少购买一本书!</h1>
        <%
            }
        %>
        <%
            if(errMsg.equals("EmptyAddress")){
        %>
                <h1>地址栏必须要填写!</h1>
        <%
            }
        %>
        <form method="post" action="http://localhost:8080/MyBookstoreSystem-war/CtrlServlet">
            <input type="submit" name="submit" value="返回">
            <input type="hidden" name="page" value="Error" >
        </form>
    </body>
</html>
