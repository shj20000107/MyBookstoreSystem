<%-- 
    Document   : Order
    Created on : 2021-6-12, 21:47:57
    Author     : �򺣽�
--%>

<%@page import="statefulsession.Cart"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="GB2312"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ͼ�鶩��</title>
    </head>
    <body>
        <h1>������������ͼ��</h1>
        <br><table border=2>
            <tr>
                <td>����</td>
                <td>����</td>
            </tr>
            <% 
                List<Cart> finalList = (List<Cart>)request.getAttribute("FinalList");
                int size = finalList.size();
                for(int i = 0; i < size; i++){//�Ȱ���ѡ�е�ͼ�������ﳵ�����
            %>    
            <tr>
                <td><%=finalList.get(i).getTitle() %></td>
                <td><%=finalList.get(i).getNumber() %></td>
            </tr>
            <%
                }
            %>
        </table></br>
        <h1>ͼ����48Сʱ�͵����µ�ַ</h1>
    <h><%=request.getAttribute("address") %></h><br>
    <h1>�븶�� <%=request.getAttribute("total") %> Ԫ</h1>
    </body>
</html>
