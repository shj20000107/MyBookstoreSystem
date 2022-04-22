<%-- 
    Document   : Order
    Created on : 2021-6-12, 21:47:57
    Author     : 沈海健
--%>

<%@page import="statefulsession.Cart"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="GB2312"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>图书订单</title>
    </head>
    <body>
        <h1>您订购了以下图书</h1>
        <br><table border=2>
            <tr>
                <td>书名</td>
                <td>数量</td>
            </tr>
            <% 
                List<Cart> finalList = (List<Cart>)request.getAttribute("FinalList");
                int size = finalList.size();
                for(int i = 0; i < size; i++){//先把新选中的图书往购物车里添加
            %>    
            <tr>
                <td><%=finalList.get(i).getTitle() %></td>
                <td><%=finalList.get(i).getNumber() %></td>
            </tr>
            <%
                }
            %>
        </table></br>
        <h1>图书在48小时送到以下地址</h1>
    <h><%=request.getAttribute("address") %></h><br>
    <h1>请付款 <%=request.getAttribute("total") %> 元</h1>
    </body>
</html>
