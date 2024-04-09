
<%@page import="ict.bean.EquipmentBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h2>notification</h2>
                        <%
            ArrayList<EquipmentBean> eqs = (ArrayList<EquipmentBean>) request.getAttribute("notification");
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
                out.println("</tr>");
            }
            out.println("</table>");
        %>   

