<%-- 
    Document   : test.jsp
    Created on : 18 Apr 2024, 12:28:24 pm
    Author     : sean3
--%>

<%@page import="ict.bean.RequestBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<% RequestBean eb = (RequestBean)request.getAttribute("Bean");%>
<%= eb.getDeliverdate() %>
<%= eb.getDelivertime() %>


    </body>
</html>
