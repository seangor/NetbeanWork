<%-- 
    Document   : ViewOrderItem
    Created on : 14 Apr 2024, 5:33:24 am
    Author     : sean3
--%>

<%@page import="ict.bean.OrderitemBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <%
            ArrayList<OrderitemBean> eqs = (ArrayList<OrderitemBean>) request.getAttribute("OrderItemList");

        %> 
        <h1>查看訂單物品</h1>
        <table border='1' >
            <tr>
                <th>ID</th><th>eid</th>
            </tr>
            <%                         for (int i = 0; i < eqs.size(); i++) {
                    OrderitemBean c = eqs.get(i);
            %> 
            <tr>
                <td><%=c.getOrderid()%></td>
                <td><%=c.getEid()%></td>

            </tr>
            <% }%>
        </table> 
        <button>批准送貨</button> 
    </body>
</html>
