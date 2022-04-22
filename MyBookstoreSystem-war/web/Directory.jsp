<%-- 
    Document   : Directory
    Created on : 2021-6-12, 17:49:05
    Author     : 沈海健
--%>

<%@page import="entity.Bookinfo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="GB2312"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>图书查询结果</title>
    </head>
    <body>
        <h1>符合查询条件的图书如下：</h1>
        <form method="post" action="http://localhost:8080/MyBookstoreSystem-war/CtrlServlet">
            <br><table border=2>
            <tr>
                <td> </td>
                <td>书名</td>
                <td>作者</td>
                <td>出版社</td>
                <td>价格</td>
            </tr>
            <% 
                List<Bookinfo> searchBookList = (List<Bookinfo>)session.getAttribute("SearchBookList");
                int size = searchBookList.size();
                for(int i = 0; i < size; i++){
            %>
            <tr>
                <td><input type="checkbox" name="choose" value="<%=searchBookList.get(i).getIsbn()%>" ></td>
                <td><%=searchBookList.get(i).getTitle() %></td>
                <td><%=searchBookList.get(i).getAuthor() %></td>
                <td><%=searchBookList.get(i).getPress() %></td>
                <td><%=searchBookList.get(i).getPrice() %></td>
            </tr>
            <% 
                }
            %>
            </table></br>
            <input type="submit" name="submit" value="Cart">
            <input type="submit" name="submit" value="Search">
            <input type="hidden" name="page" value="Directory" >
        </form>
    </body>
</html>
