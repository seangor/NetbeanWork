<%-- 
    Document   : ViewDelivery
    Created on : 15 Apr 2024, 1:17:03 am
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
        <h1>Your Delivery</h1>
        <table border='1' >
            <tr>
                <th>ID</th><th>Delivery Date</th><th>Delviery Time</th><th>Order Created Date</th><th>Status</th><th>Contact No</th><th>View Detail</th>
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
                <td><%=c.getTel()%></td>
                <td><% if (c.getStatus().equalsIgnoreCase("2")) {%>
                    <form action="<%=request.getContextPath()%>/HandleStatus">
                        <input type="hidden" name="action" value="UpFinish" >
                        <input type="hidden" name="orderid" value="<%=c.getOrderId()%>" >
                        <input type="submit" value="送達" style="background:none!important; border:none; padding:0!important; color:blue; text-decoration:underline; cursor:pointer;"></form></td>
            </tr>
            <%} else {%>
                        <input type="submit" value="已送達" style="background:none!important; border:none; padding:0!important; color:blue; text-decoration:underline; cursor:pointer;" disabled></form></td>
<%}
}%>
        </table>   
    
            <jsp:include page="/WEB-INF/footer.jsp"  />
</body>
</html>
