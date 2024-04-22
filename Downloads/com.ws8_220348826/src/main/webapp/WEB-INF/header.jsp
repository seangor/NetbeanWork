<%-- 
    Document   : header
    Created on : 22 Apr 2024, 5:49:59 am
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
        <%if (session.getAttribute("Account") == "Technician") {%>
        Account Type: <%=session.getAttribute("Account")%>

        <a href="<%= request.getContextPath()%>/index.jsp">返回Index</a>
        <a href="<%= request.getContextPath()%>/HandleRequest?action=list">View Request</a>
        <a href="<%= request.getContextPath()%>/HandleTechnicianOrder?action=approvelist">View Order</a>
        <a href="<%= request.getContextPath()%>/HandleBorrowRecord?action=checkReturn">View Return Record</a>
        <a href="<%= request.getContextPath()%>/HandleEquipment?action=listStatus">Check Equipment Status</a>  
        <% } else if (session.getAttribute("Account") == "User") {%>
        Account Type: <%=session.getAttribute("Account")%>
        <a href="<%= request.getContextPath()%>/index.jsp">返回Index</a>
        <a href="<%= request.getContextPath()%>/HandleRequest?action=list">View request</a>
        <a href="<%= request.getContextPath()%>/HandleEquipment?action=list">View Equipment</a>
        <a href="<%= request.getContextPath()%>/HandleOrder?action=list">View Order</a>
        <a href="<%= request.getContextPath()%>/HandleBorrowRecord?action=Recordlist">View Record</a>
        <a href="FUNCTION/View_information.jsp">View personal Info & update password/Info</a>
        <a href="<%= request.getContextPath()%>/HandleWishlist?action=notice">Notification</a>
        <%}
        if (session.getAttribute("Account") == "Courier") {%>
        Account Type: <%=session.getAttribute("Account")%>
        <a href="<%= request.getContextPath()%>/index.jsp">返回Index</a>

        <a href="<%=request.getContextPath()%>/HandleCourierOrder?action=CourierOrder">View Order</a>
        <a href="<%=request.getContextPath()%>/HandleCourierOrder?action=Courierdeliver">your Delivery</a>
        <%}%>
    </body>
</html>
