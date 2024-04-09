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
            ArrayList<EquipmentBean> eqs = (ArrayList<EquipmentBean>) request.getAttribute("Equipments");
            out.println("<h1>查看裝置狀態</h1>");
            out.println("<table border='1' >");
            out.println("<tr>");
            out.println("<th>ID</th><th>名字</th><th>狀態</th><th>數量</th><th>加入願望清單</th>");
            out.println("</tr>");
            for (int i = 0; i < eqs.size(); i++) {
                EquipmentBean c = eqs.get(i);
                out.println("<tr>");
                out.println("<td>" + c.getEid() + "</td>");
                out.println("<td>" + c.getEName() + "</td>");
                out.println("<td>" + c.getEstatus() + "</td>");
                out.println("<td>" + c.getQuantity() + "</td>");
                out.println("<td style=\"text-align: center; \"><a href=\"HandleWishlist?action=add&eid=" + c.getEid() + "&wid="+c.getWid()+"\" >");
                if (c.getWid() != 0) {
                    out.println("<img src=\"img/heart.png\" style=\"width: 20px; height: 20px; padding-top: 3px; \" />");

                } else {
                    out.println("<img src=\"img/like.png\" style=\"width: 20px; height: 20px; alignContent: center; padding-top: 3px; \" />");

                }
                out.println("</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        %>   
        <a href="USERS/FUNCTION/addRecord.jsp">Back to user</a>
        <img src="img/like.png" style="width: 20px; height: 20px" />

    </body>
</html>
