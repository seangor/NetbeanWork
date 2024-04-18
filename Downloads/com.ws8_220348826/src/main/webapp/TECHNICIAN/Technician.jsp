<%-- 
    Document   : Technician
    Created on : 17 Mar 2024, 2:52:46 am
    Author     : sean3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
                Account Type: <%=session.getAttribute("Account")%>

        <a href="<%= request.getContextPath()%>/index.jsp">返回Index</a>
        <a href="<%= request.getContextPath()%>/HandleRequest?action=list">View Request</a>
        <a href="<%= request.getContextPath()%>/HandleTechnicianOrder?action=approvelist">View Order</a>
        <a href="<%= request.getContextPath()%>/HandleBorrowRecord?action=checkReturn">View Return Record</a>

        <ul><li>查看訂購通知記錄和狀態</li>
            <li>查看訂購運送過程記錄和狀態</li>
            <li>批准訂購通知</li>
            <li>安排送貨</li>
            <li>更新設備為借出狀態</li>
            <li>更新設備為歸還狀態</li>
            <li>更新該學園目前設備數量</li>
            <li>報告受損設備</li>
        </ul>
    </body>
</html>
