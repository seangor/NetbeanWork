<%-- 
    Document   : ViewPending
    Created on : 9 Apr 2024, 7:33:08 pm
    Author     : sean3
--%>

<%@page import="ict.bean.RecordBean"%>
<%@page import="ict.bean.EquipmentBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>可取件記錄</h1>
        <%
            out.println("<h1>View Equipment</h1>");
            out.println("<table border='1' >");
            out.println("<tr>");
            out.println("<th>記錄ID</th><th>名字</th><th>租借日期</th><th>截止日期</th><th>狀態</th>");
            out.println("</tr>");
            ArrayList<RecordBean> rbs = (ArrayList<RecordBean>) request.getAttribute("BRecord");
            for (int i = 0; i < rbs.size(); i++) {
                RecordBean c = rbs.get(i);
                out.println("<tr>");
                out.println("<td>" + c.getBid() + "</td>");
                out.println("<td>" + c.getEname() + "</td>");
                out.println("<td>" + c.getBorrowDate() + "</td>");
                out.println("<td>" + c.getDeadline() + "</td>");
                out.println("<td>" + c.getStatus() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        %>   
    </body>
</html>
