<%-- 
    Document   : ViewDelivery
    Created on : 15 Apr 2024, 1:17:03 am
    Author     : sean3
--%>

<%@page import="ict.bean.OrderBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
                                             <a href="<%= request.getContextPath()%>/index.jsp">返回Index</a>
    
        <a href="<%=request.getContextPath() %>/HandleCourierOrder?action=CourierOrder">View Order</a>
                <a href="<%=request.getContextPath() %>/HandleCourierOrder?action=Courierdeliver">your Delivery</a>
        <%
            ArrayList<OrderBean> eqs = (ArrayList<OrderBean>) request.getAttribute("OrderList");

        %> 
        <h1>查看訂單</h1>
        <table border='1' >
            <tr>
                <th>ID</th><th>送貨日期</th><th>送貨時間</th><th>訂單創建日期</th><th>狀態</th><th>聯繫電話</th><th>查看細節</th>
            </tr>
            <%                         for (int i = 0; i < eqs.size(); i++) {
                    OrderBean c = eqs.get(i);
            %> 
            <tr>
                <td><%=c.getOrderId()%></td>
                <td><%=c.getDeliverdate()%></td>
                <td><%=c.getDelivertime()%></td>
                <td><%=c.getCreatedTime()%></td>
                <td><%=c.getStatus()%></td>
                <td><%=c.getTel()%></td>
                <td><% if (c.getStatus().equalsIgnoreCase("delivering")) {%>
                    <form action="<%=request.getContextPath()%>/HandleStatus">
                        <input type="hidden" name="action" value="UpFinish" >
                        <input type="hidden" name="orderid" value="<%=c.getOrderId()%>" >
                        <input type="submit" value="送達" style="background:none!important; border:none; padding:0!important; color:blue; text-decoration:underline; cursor:pointer;"></form></td>
            </tr>
            <%} else {%>
                        <input type="submit" value="已送達" style="background:none!important; border:none; padding:0!important; color:blue; text-decoration:underline; cursor:pointer;" disabled></form></td>
<%}
}%>
        </table>    </body>
</html>
