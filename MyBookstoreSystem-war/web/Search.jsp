<%-- 
    Document   : Search
    Created on : 2021-6-12, 17:43:01
    Author     : �򺣽�
--%>

<%@page contentType="text/html" pageEncoding="GB2312"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>�������</title>
    </head>
    <body>
        <h1>ͼ���ѯ����ָ�����������߻������</h1>
        <form method="post" action="http://localhost:8080/MyBookstoreSystem-war/CtrlServlet">
            <input type="text" name="key"><p>
            <input type="submit" name="submit" value="ģ����ѯ">
            <input type="hidden" name="page" value="Search" >
        </form>
    </body>
</html>
