<%-- 
    Document   : Courier
    Created on : 17 Mar 2024, 2:52:20 am
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
        <a href="<%=request.getContextPath() %>/HandleBorrowRecord?action=cList">View Pending</a>
                <a href="<%=request.getContextPath() %>/HandleBorrowRecord?action=OrderList">View Order</a>

    </body>
</html>
