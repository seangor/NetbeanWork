<%-- 
    Document   : ViewReturnCheckout
    Created on : 16 Apr 2024, 1:48:18 am
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
        
                <a href="<%= request.getContextPath()%>/index.jsp">返回Index</a>
        <a href="<%= request.getContextPath()%>/HandleEquipment?action=list">View Equipment</a>
        <a href="<%= request.getContextPath()%>/HandleOrder?action=list">View Order</a>
        <a href="<%= request.getContextPath()%>/HandleBorrowRecord?action=Recordlist">View Record</a>
        <a href="FUNCTION/View_information.jsp">View personal Info & update password/Info</a>
        <a href="<%= request.getContextPath()%>/HandleWishlist?action=notice">Notification</a>


        <%
        ArrayList<RecordBean> rbs = (ArrayList<RecordBean>) request.getAttribute("CheckOutList");
        %>
               
        你的歸還選項：
        <table border='1'>
            <tr>
                <th>記錄ID</th>
                <th>名字</th>
                <th>圖片</th>
                <th>租借日期</th>
                <th>截止日期</th>
                <th>狀態</th>
            </tr>
            <% 
            for (int i = 0; i < rbs.size(); i++) {
                RecordBean c = rbs.get(i);
            %>
            <tr>
                <td><%= c.getBid() %></td>
                <td><%= c.getEname() %></td>
                <td><img src="img/<%= c.getImgsrc() %>" width="80" height="80"></td>
                <td><%= c.getBorrowDate() %></td>
                <td><%= c.getDeadline() %></td>
                <td><%= c.getStatus() %></td>
            </tr>
            <% } %>
        </table>
        
                        <label for="month">取件日期：</label>
                        <select id="month" name="month">
                            <% for (int i = 1; i <= 12; i++) {%>
                            <option value="<%= i%>"><%= i%></option>
                            <% } %>
                        </select>(Month)/
                        <select id="day" name="day">
                            <% for (int i = 1; i <= 31; i++) {%>
                            <option value="<%= i%>"><%= i%></option>
                            <% }%>
                        </select>(Day)
                        <br>
                        <label for="month">取件時間：</label>
                        <select id="hourSelect" name="hour">
                            <% for (int i = 9; i <= 18; i++) {%>
                            <option value="<%= i%>"><%= i%></option>
                            <% } %>
                        </select>:
                        <select id="minuteSelect" name="minute">
                            <% for (int i = 0; i < 60; i += 15) {%>
                            <option value="<%= i%>"><%= String.format("%02d", i)%></option>
                            <% }%>
                        </select>(The opening hours is between 09:00-18:00)                        <br>

                </body>
</html>
