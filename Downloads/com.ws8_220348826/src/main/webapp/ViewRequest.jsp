<%-- 
    Document   : ViewRequest
    Created on : 17 Apr 2024, 3:14:56 am
    Author     : sean3
--%>

<%@page import="ict.bean.RequestBean"%>
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
            ArrayList<RequestBean> eqs = (ArrayList<RequestBean>) request.getAttribute("RequestList");
            String action = request.getParameter("action");
        %> 
        <h1>View Request</h1>
        <table border='1' >
            <tr>
                <th>ID</th><th>送貨日期</th><th>送貨時間</th><th>訂單創建日期</th><th>狀態</th><th>查看細節</th>
            </tr>
            <%                         for (int i = 0; i < eqs.size(); i++) {
                    RequestBean c = eqs.get(i);
            %> 
            <tr>
                <td><%=c.getRequestId()%></td>
                <td><%=c.getDeliverdate()%></td>
                <td><%=c.getDelivertime()%></td>
                <td><%=c.getCreatedTime()%></td>
                <td><ict:showStatus item="request" status="<%=c.getStatus()%>" /></td>

                <td><form action="<%=request.getContextPath()%>/HandleRequestItem">
                        <%if (action.equalsIgnoreCase("approvelist")) {%>
                        <input type="hidden" name="action" value="approvelist" >
                        <% } else if (action.equalsIgnoreCase("list")) {%>
                        <input type="hidden" name="action" value="list" >
                        <% }%>
                        <input type="hidden" name="requestid" value="<%=c.getRequestId()%>" >
                        <input type="submit" value="查看細節" style="background:none!important; border:none; padding:0!important; color:blue; text-decoration:underline; cursor:pointer;"></form></td>
            </tr>
            <% }%>
            

        </table>    
    </body>
</html>
