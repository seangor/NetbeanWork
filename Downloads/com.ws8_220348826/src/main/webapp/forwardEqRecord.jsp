<%-- 
    Document   : forwardEqRecord
    Created on : 18 Mar 2024, 2:39:34 am
    Author     : sean3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="ict.bean.SystemBean" %>
<%@ page import="ict.db.BookingSystem" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   <body>
    <h1>Hello World!</h1>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Status</th>
        </tr>
        <% 
        String url = "jdbc:mysql://localhost:3306/vtcit";
        String username = "root";
        String password = "root";
        BookingSystem booking = new BookingSystem(url, username, password);
        ArrayList<SystemBean> cbs = booking.queryBooking();
for (SystemBean cb : cbs) { %>
            <tr>
                <td><%= cb.getEName() %></td>
                <td><%= cb.getEStatus() %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>