<%-- 
    Document   : ViewRequestItem
    Created on : 17 Apr 2024, 1:19:49 pm
    Author     : sean3
--%>

<%@page import="ict.bean.RequestBean"%>
<%@page import="ict.bean.RequestitemBean"%>
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

        <%
            ArrayList<RequestitemBean> eqs = (ArrayList<RequestitemBean>) request.getAttribute("RequestItemList");
            RequestBean BorrowRequest = (RequestBean) request.getAttribute("BorrowRequest");
            String userType = (String) session.getAttribute("Account");
        %> 
        <h1>查看訂單物品</h1>
        <span>Order ID: <%=eqs.get(0).getRequestid()%></span><br>
        <span>Campus: LWL</span> <br>
        <span>User: Him</span><br>
        <table border='1' >
            <tr>
                <th>ID</th><th>eid</th>
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
                            }%>    </body>
</html>
