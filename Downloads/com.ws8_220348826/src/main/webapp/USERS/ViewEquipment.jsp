<%-- 
    Document   : ViewEquipment
    Created on : 18 Mar 2024, 2:36:53 am
    Author     : sean3
--%>
<%@page import="java.util.ArrayList, ict.bean.EquipmentBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/showEquipment.tld" prefix="ict" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Equipment</title>
    </head>
    <body>
        <%
            ArrayList<EquipmentBean>equipments = (ArrayList<EquipmentBean>) request.getAttribute("Equipments");
            out.println("<h1>View Equipment</h1>");
            out.println("<table border='1' >");
            out.println("<tr>");
            out.println("<th>CustId</th><th>name</th><th>tel</th><th>age</th>");
            out.println("</tr>");
            for (int i = 0; i < equipments.size(); i++) {
                EquipmentBean c = equipments.get(i);
                out.println("<tr>");
                out.println("<td>" + c.getEid() + "</td>");
                out.println("<td>" + c.getEName() + "</td>");
                out.println("<td>" + c.getEstatus() + "</td>");
                out.println("<td>" + c.getQuantity() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

        %>   
    </body>
</html>
