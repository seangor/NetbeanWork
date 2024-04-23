<%-- 
    Document   : ViewReturnCheckout
    Created on : 16 Apr 2024, 1:48:18 am
    Author     : sean3
--%>

<%@page import="ict.bean.RecordBean"%>
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
        ArrayList<RecordBean> rbs = (ArrayList<RecordBean>) request.getAttribute("CheckOutList");
        session.setAttribute("Returnequipments", rbs);
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
                <td><ict:showStatus item="borrowrecord" status="<%=c.getStatus()%>" /></td>
            </tr>
            <% } %>
        </table>
        <form action="<%= request.getContextPath()%>/handleEdit" method="get">
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
                        <input type="hidden" name="Ordertype"  value="return" />
                        <input type="hidden" name="type"  value="2" />
                        <input type="hidden" name="action" value="add" />
                         <input type="submit" value="確認" />
</form>
                                <jsp:include page="/WEB-INF/footer.jsp"  />

                </body>
</html>
