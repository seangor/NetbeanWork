<%-- 
    Document   : welcome
    Created on : 18 Mar 2024, 2:22:08 pm
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
        <span>Welome to the ICT</span>
        <ul>
            <li><a href="editCustomer.jsp">add Customer</a></li>
            <li><a href="handleCustomer?action=list">list Customer</a></li>
                <jsp:include page="searchCustomers.jsp" />

        </ul>
        <input  type="button" value="Logout"/>
    </body>
</html>
