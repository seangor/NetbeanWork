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
                <%
            ArrayList<RecordBean> Record = (ArrayList<RecordBean>) request.getAttribute("BRecord");


        %>   
        <ict:showEquipment list="<%=Record%>" type="BRecord" />
        <a href="USERS/User.jsp">Back to user</a>

    </body>
</html>
