<%-- 
    Document   : Cart
    Created on : 2021-6-12, 18:33:09
    Author     : �򺣽�
--%>

<%@page import="java.util.List"%>
<%@page import="statefulsession.Cart"%>
<%@page contentType="text/html" pageEncoding="GB2312"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>���ﳵ</title>
    </head>
    <body>
        <h1>��ָ������ͼ��������������ַ</h1>
        <form method="post" action="http://localhost:8080/MyBookstoreSystem-war/CtrlServlet">
            <br><table border=2>
            <tr>
                <td>����</td>
                <td>�۸�</td>
                <td>����</td>
            </tr>
            <% 
                List<Cart> cartList = (List<Cart>)session.getAttribute("CartList");
                int size = cartList.size();
                for(int i = 0; i < size; i++){//�Ȱ���ѡ�е�ͼ�������ﳵ�����
            %>    
            <tr>
                <td><%=cartList.get(i).getTitle() %></td>
                <td><%=cartList.get(i).getPrice() %></td>
                <td><input type="text" name="<%=cartList.get(i).getIsbn() %>" value="<%=cartList.get(i).getNumber() %>" ></td>
            </tr>
            <%
                }
            %>
            </table></br>
            <h>��д���ַ:</h><br>
            <input type="text" name="address"><p>
            <input type="submit" name="submit" value="Order">
            <input type="submit" name="submit" value="Return">
            <input type="submit" name="submit" value="Search">
            <input type="hidden" name="page" value="Cart" >
        </form>
    </body>
</html>
