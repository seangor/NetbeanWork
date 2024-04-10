<%-- 
    Document   : ViewOrder
    Created on : 10 Apr 2024, 2:06:04 am
    Author     : sean3
--%>

<%@page import="ict.bean.RecordBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
      <h1>Order</h1>
        <%
            out.println("<table border='1' >");
            out.println("<tr>");
            out.println("<th>記錄ID</th><th>名字</th><th>租借日期</th><th>截止日期</th><th>狀態</th><th>更新狀態</th>");
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
                %> 
    <td><form action="<%=request.getContextPath() %>/handleEdit" method="get"   >
            <input type="hidden" name="action" value="finishOrder" />
             <input type="hidden" name="bid" value="<%=c.getBid() %>" />
            <input type="submit" value="已送達" /></form></td>

                </tr>
           <% } %> 
            </table>    </body>
</html>
