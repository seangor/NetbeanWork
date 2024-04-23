<%-- 
    Document   : ViewOrder
    Created on : 14 Apr 2024, 3:11:11 am
    Author     : sean3
--%>

<%@page import="ict.bean.OrderBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@ taglib uri="/WEB-INF/tlds/showStatus" prefix="ict" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
                          <jsp:include page="/WEB-INF/header.jsp"  />

        <%
            ArrayList<OrderBean> eqs = (ArrayList<OrderBean>) request.getAttribute("OrderList");

        %> 
        <h1>View Available Order</h1>
        <table border='1' >
            <tr>
                <th>ID</th><th>Delivey Date</th><th>Delivery Time</th><th>Created Date</th><th>Status</th><th>View Detail</th>
            </tr>
            <%                         for (int i = 0; i < eqs.size(); i++) {
                    OrderBean c = eqs.get(i);
            %> 
            <tr>
                <td><%=c.getOrderId()%></td>
                <td><%=c.getDeliverdate()%></td>
                <td><%=c.getDelivertime()%></td>
                <td><%=c.getCreatedTime()%></td>
                <td><ict:showStatus item="order" status="<%=c.getStatus()%>" /></td>
                <td><form action="<%=request.getContextPath()%>/HandleStatus">
                        <input type="hidden" name="action" value="UpDeliver" >
                        <input type="hidden" name="orderid" value="<%=c.getOrderId()%>" >
                        <input type="submit" value="接受" style="background:none!important; border:none; padding:0!important; color:blue; text-decoration:underline; cursor:pointer;"></form></td>
            </tr>
            <% }%>
        </table>
                <jsp:include page="/WEB-INF/footer.jsp"  />

    </body>
</html>
