<%-- 
    Document   : ViewRecord
    Created on : 4 Apr 2024, 2:28:40 am
    Author     : sean3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, ict.bean.RecordBean"%>
<%@taglib uri="/WEB-INF/tlds/showEquipment.tld" prefix="ict" %>
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

        <form action="<%=request.getContextPath()%>/HandleBorrowRecord" method="get">
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
                </tr>
                <%
                    ArrayList<RecordBean> Record = (ArrayList<RecordBean>) request.getAttribute("BRecord");
                    ArrayList<RecordBean> rbs = Record;
                    for (int i = 0; i < rbs.size(); i++) {
                        RecordBean c = rbs.get(i);
                %>
                <tr>
                    <td><%= c.getBid()%></td>
                    <td><%= c.getEname()%></td>
                    <td><img src="img/<%= c.getImgsrc()%>" alt="alt" height="80" width="80"/></td>
                    <td><%= c.getBorrowDate()%></td>
                    <td><%= c.getDeadline()%></td>
                    <td><%= c.getStatus()%></td>
                    <td><input type="checkbox" name="selectedEq" onclick="toggleButton()" value="<%= c.getBid()%>" />歸還</td>
                </tr>
                <% }%>
            </table>
            <br>
            <input name="action" type="hidden" value="checkout" />

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
    </body>
</html>