<%-- 
    Document   : ViewRequestItem
    Created on : 17 Apr 2024, 1:19:49 pm
    Author     : sean3
--%>

<%@page import="ict.bean.EquipmentBean"%>
<%@page import="ict.bean.RequestBean"%>
<%@page import="ict.bean.RequestitemBean"%>
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
            ArrayList<RequestitemBean> eqs = (ArrayList<RequestitemBean>) request.getAttribute("RequestItemList");
            ArrayList<EquipmentBean> equipmentlist = (ArrayList<EquipmentBean>) request.getAttribute("Equipments");

            RequestBean BorrowRequest = (RequestBean) request.getAttribute("BorrowRequest");
            String userType = (String) session.getAttribute("Account");
        %> 
        <jsp:useBean class="ict.bean.RequestBean" id="Br" scope="page" />
        <jsp:setProperty name="Br" property="requestId"  value="<%=BorrowRequest.getRequestId()%>" />

        <h1>View Request</h1>
        <span>Request ID: <jsp:getProperty name="Br" property="requestId" /></span><br>
        <span>Campus: LWL</span> <br>
        <span>User: Him</span><br>
        <span>Status:         <ict:showStatus item="request" status="<%=BorrowRequest.getStatus()%>" /></span><br>
        <table border='1' >
            <tr>
                <th>ID</th><th>Image</th><th>Equipment</th>
            </tr>
            <%                         for (int i = 0; i < eqs.size(); i++) {
                    RequestitemBean c = eqs.get(i);
            %> 
            <tr>
                <td style="width: 80px; text-align: center;"><%= c.getEid()%></td>
                <td><img src="img/<%=c.getImgsrc()%>" width="80px" height="80px" alt="alt"/></td>
                <td><%=c.getEname()%></td>

            </tr>
            <% }%>
        </table> 
        <% if (userType.equalsIgnoreCase("Technician")) {
                if (BorrowRequest.getStatus().equalsIgnoreCase("1")) {
        %>
        <form action="<%=request.getContextPath()%>/handleEdit">
            <input type="hidden" name="action" value="UpApprove" />
            <input type="hidden" name="requestid" value="<%=eqs.get(0).getRequestid()%>" />
            <input type="submit" value="批准"/>
        </form>
        <%} else { %>
        <input type="submit" value="已批准" disabled/>

        <%}
            }%> 
        <% if (userType.equalsIgnoreCase("Technician")) {%>
        <jsp:include page="${request.getContextPath()}/WEB-INF/CheckEquipments.jsp"  />
        <%}%>
                <jsp:include page="/WEB-INF/footer.jsp"  />

    </body>
</html>
