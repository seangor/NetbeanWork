<%-- 
    Document   : Staff
    Created on : 17 Mar 2024, 2:50:44 am
    Author     : sean3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="<%= request.getContextPath()%>/index.jsp">返回Index</a>
        <a href="<%= request.getContextPath()%>/HandleEquipment?action=list">View Equipment</a>
        <a href="<%= request.getContextPath()%>/HandleOrder?action=list">View Order</a>
        <a href="<%= request.getContextPath()%>/HandleBorrowRecord?action=Recordlist">View Record</a>
        <a href="FUNCTION/View_information.jsp">View personal Info & update password/Info</a>
        <a href="<%= request.getContextPath()%>/HandleWishlist?action=notice">Notification</a>
    </body>
</html>
