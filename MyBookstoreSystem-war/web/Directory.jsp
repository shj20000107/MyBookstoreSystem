<%-- 
    Document   : Directory
    Created on : 2021-6-12, 17:49:05
    Author     : �򺣽�
--%>

<%@page import="entity.Bookinfo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="GB2312"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ͼ���ѯ���</title>
    </head>
    <body>
        <h1>���ϲ�ѯ������ͼ�����£�</h1>
        <form method="post" action="http://localhost:8080/MyBookstoreSystem-war/CtrlServlet">
            <br><table border=2>
            <tr>
                <td> </td>
                <td>����</td>
                <td>����</td>
                <td>������</td>
                <td>�۸�</td>
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
