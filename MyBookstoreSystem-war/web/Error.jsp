<%-- 
    Document   : Error
    Created on : 2021-6-12, 18:26:36
    Author     : �򺣽�
--%>

<%@page contentType="text/html" pageEncoding="GB2312"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>����</title>
    </head>
    <body>
        <%
            String errMsg = (String)session.getAttribute("errMsg");
            if(errMsg.equals("NullKey")){
        %>
                <h1>��û������ؼ���!</h1>
        <%
            }
        %>
        <%
            if(errMsg.equals("EmptySearchBookList")){
        %>
                <h1>û���κ���Ч��ѯ!</h1>
        <%
            }
        %>
        <%
            if(errMsg.equals("EmptyCartList")){
        %>
                <h1>�����ﳵû��ͼ��!</h1>
        <%
            }
        %>
        <%
            if(errMsg.equals("FaultFormat")){
        %>
                <h1>�����ٹ���һ����!</h1>
        <%
            }
        %>
        <%
            if(errMsg.equals("EmptyAddress")){
        %>
                <h1>��ַ������Ҫ��д!</h1>
        <%
            }
        %>
        <form method="post" action="http://localhost:8080/MyBookstoreSystem-war/CtrlServlet">
            <input type="submit" name="submit" value="����">
            <input type="hidden" name="page" value="Error" >
        </form>
    </body>
</html>
