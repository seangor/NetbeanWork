<%-- 
    Document   : User
    Created on : 17 Mar 2024, 2:50:13 am
    Author     : sean3
--%>

<%@page import="ict.bean.EquipmentBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <a href="/com.ws8_220348826/HandleEquipment?action=list">View Equipment</a>
        <a href="FUNCTION/addRecord.jsp">Borrow Equipment</a>
        <a href="FUNCTION/SearchEquipment.jsp">Search Equipment</a>
        <a href="/com.ws8_220348826/HandleBorrowRecord?action=list">View Record</a>
        <a href="FUNCTION/ReturnEquipment.jsp">Return Equipment</a>
                <a href="/com.ws8_220348826/HandleWishlist?action=notice">Notification</a>


<br />



    </body>
</html>
