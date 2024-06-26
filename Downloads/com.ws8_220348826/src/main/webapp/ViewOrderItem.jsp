<%-- 
    Document   : ViewOrderItem
    Created on : 14 Apr 2024, 5:33:24 am
    Author     : sean3
--%>

<%@page import="ict.bean.OrderBean"%>
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
                       <jsp:include page="/WEB-INF/header.jsp"  />

        <%
            ArrayList<OrderitemBean> eqs = (ArrayList<OrderitemBean>) request.getAttribute("OrderItemList");
            String action = request.getParameter("action");
            OrderBean Order = (OrderBean) request.getAttribute("Order");
        %> 
        <jsp:useBean class="ict.bean.OrderBean" id="or" scope="request" />
        <jsp:setProperty name="or" property="orderId" value="<%=Order.getOrderId()%>" />

        <h1>查看訂單物品</h1>
        <span>Order ID: <jsp:getProperty name="or" property="orderId" /></span><br>
        <span>Delivery Address: <jsp:getProperty name="or" property="orderId" /></span><br>
        <span>Shipping Address: <jsp:getProperty name="or" property="orderId" /></span><br>

        <span>Campus: LWL</span> <br>
        <span>User: Him</span><br>
        <table border='1' >
            <tr>
                <th>ID</th><th>eid</th>
            </tr>
            <%                         for (int i = 0; i < eqs.size(); i++) {
                    OrderitemBean c = eqs.get(i);
            %> 
            <tr>
                <td style="width: 80px; text-align: center;"><%= c.getEid()%></td>
                <td><img src="img/<%=c.getImgsrc()%>" width="80px" height="80px" alt="alt"/></td>
                <td><%=c.getEname()%></td>

            </tr>
            <% }%>
        </table> 
        <% if (action.equalsIgnoreCase("approvelist")) {
                if (Order.getStatus().equalsIgnoreCase("1")) {
        %>
        <form action="<%=request.getContextPath()%>/HandleStatus">
            <input type="hidden" name="action" value="UpApprove" />
            <input type="hidden" name="orderid" value="<jsp:getProperty name="or" property="orderId" />" />
            <input type="submit" value="批准"/>
        </form>
        <%} else { %>
        <input type="submit" value="已批准" disabled/>

        <%}
                            }%>
    </body>
</html>
