<%-- 
    Document   : CheckEquipments
    Created on : 22 Apr 2024, 3:22:25 am
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
        <%
            ArrayList<EquipmentBean> equipmentlist = (ArrayList<EquipmentBean>) request.getAttribute("Equipments");
            String userType = (String) session.getAttribute("Account");
            String action = (String) request.getParameter("action");
        %>

        <table border='1' >
            <tr>
                <% if (action.equalsIgnoreCase("list")) { %>
                <th>ID</th>
                <th>Image</th>
                <th>Equipment Name</th>
                <th>Quantity After Borrowing</th>
                <th>Equipment Damaged</th>
                <th>Total</th>
                    <% } else if (action.equalsIgnoreCase("listStatus")) {%>
                <th>ID</th>
                <th>Image</th>
                <th>Equipment Name</th>
                <th>Equipment Borrowed</th>
                <th>Equipment Damaged</th>
                <th>Stock</th>
                <th>Total</th>
                    <%}%>
            </tr>
            <%                         for (int i = 0; i < equipmentlist.size(); i++) {
                    EquipmentBean c = equipmentlist.get(i);


            %> 
            <tr>
                <% if (action.equalsIgnoreCase("list")) {%>
                <td style="width: 80px; text-align: center;"><%= c.getEid()%></td>
                <td><img src="img/<%=c.getImgsrc()%>" width="80px" height="80px" alt="alt"/></td>
                <td><%=c.getEName()%></td>
                <td><%=c.getQuantity()%> => <%=c.getQuantity() - 1%></td>
                <td><%=c.getDamagedQty()%></td>
                <td><%=c.getTotalQty()%></td>

                <% } else if (action.equalsIgnoreCase("listStatus")) {%>
                <td style="width: 80px; text-align: center;"><%= c.getEid()%></td>
                <td><img src="img/<%=c.getImgsrc()%>" width="80px" height="80px" alt="alt"/></td>
                <td><%=c.getEName()%></td>
                <td><%=c.getBorrowQty()%></td>
                <td><%=c.getDamagedQty()%></td>
                <td><%=c.getQuantity()%> </td>  
                <td><%=c.getTotalQty()%></td>
                <% } %>
            </tr>

            <%}%>
        </table>    
    </body>
</html>
