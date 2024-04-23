<%-- 
    Document   : ViewRecord
    Created on : 4 Apr 2024, 2:28:40 am
    Author     : sean3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, ict.bean.RecordBean"%>
<%@ taglib uri="/WEB-INF/tlds/showStatus" prefix="ict" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
                       <jsp:include page="/WEB-INF/header.jsp"  />


        <%                String action = request.getParameter("action");
                    String userType = (String) session.getAttribute("Account");

        %>
        <h1>查看租借記錄</h1>
        <table border='1'>
            
            <tr>
                <th>記錄ID</th>
                <th>名字</th>
                <th>圖片</th>
                <th>租借日期</th>
                <th>截止日期</th>
                <th>狀態</th>
                <th>行動</th>
                    <%if (action.equalsIgnoreCase("checkReturn")) { %>
                <th>Report Damage</th> 
                    <%}%>
            </tr>
            <%
                ArrayList<RecordBean> Record = (ArrayList<RecordBean>) request.getAttribute("BRecord");
                for (int i = 0; i < Record.size(); i++) {
                    RecordBean c = Record.get(i);
            %>
            <% if (action.equalsIgnoreCase("Recordlist")) {%>
            <form action="<%=request.getContextPath()%>/HandleBorrowRecord" method="get">
                <%} else if (action.equalsIgnoreCase("checkReturn")) {%>
                <form action="<%=request.getContextPath()%>/handleEdit" method="get">

                    <%}%>
                    <tr>
                        <td><%= c.getBid()%></td>
                        <td><%= c.getEname()%></td>
                        <td><img src="img/<%= c.getImgsrc()%>" alt="alt" height="80" width="80"/></td>
                        <td><%= c.getBorrowDate()%></td>
                        <td><%= c.getDeadline()%></td>
                        <td><ict:showStatus item="borrowrecord" status="<%=c.getStatus()%>" /></td>
                        <td>
                            <% if (action.equalsIgnoreCase("Recordlist")) {
                                    if (c.getStatus().equalsIgnoreCase("1")) {
                            %>

                            <input type="checkbox" name="selectedEq" onclick="toggleButton()" value="<%= c.getBid()%>" />
                            歸還
                            <input name="action" type="hidden" value="checkout" />
                            <% } else {%>
                            <input type="checkbox" name="selectedEq" onclick="toggleButton()" value="<%= c.getBid()%>" disabled/>
                            歸還(已處理)                                                   
                            <% } %>
                            <%} else if (action.equalsIgnoreCase("checkReturn")) {
                                if (c.getStatus().equalsIgnoreCase("3")) {
                            %>
                            <input type="checkbox" name="selectedEq" onclick="toggleButton()" value="<%= c.getBid()%>" />
                            確認收到
                            <input name="action" type="hidden" value="UpdateReturn" />
                            <% } else {%>
                            <input type="checkbox" name="selectedEq" onclick="toggleButton()" value="<%= c.getBid()%>" disabled/>
                            確認收到(已處理)
                            <%}
                                }%>
                        </td>
                        <%if (action.equalsIgnoreCase("checkReturn")) {
                                if (c.getStatus().equalsIgnoreCase("3")) {
                        %>
                        <td>
                         <a href="<%= request.getContextPath()%>/handleEdit?bid=<%= c.getBid()%>&action=reportDamage">Report Damage</a>
                        </td> 
                        <%} else {%>
                   <td> <u><b>Report Damage</b></u></td>
                            <% }%>
                    </tr>        
                    <% }
                        }%>
                    </table>
                    <br>


                    <input id="submitButton" type="submit" value="確認" disabled />
                </form>
                    

                <script>
                    function toggleButton() {
                        var checkbox = document.getElementsByName('selectedEq');
                        var button = document.getElementById('submitButton');
                        var checked = false;

                        for (var i = 0; i < checkbox.length; i++) {
                            if (checkbox[i].checked) {
                                checked = true;
                                break;
                            }
                        }

                        if (checked) {
                            button.disabled = false;
                        } else {
                            button.disabled = true;
                        }
                    }
                </script>
                <a href="USERS/User.jsp">Back to user</a>
                        <jsp:include page="/WEB-INF/footer.jsp"  />

                </body>
                </html>