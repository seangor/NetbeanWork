<%-- 
    Document   : listCustomer
    Created on : 28 Mar 2024, 4:34:19 pm
    Author     : sean3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, ict.bean.CustomerBean"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ArrayList<CustomerBean> customers = (ArrayList<CustomerBean>) request.getAttribute("customers");
            out.println("<h1>Customer</h1>");
            out.println("<table border='1' >");
            out.println("<tr>");
            out.println("<th>CustId</th><th>name</th><th>tel</th><th>age</th>");
            out.println("</tr>");
            for (int i = 0; i < customers.size(); i++) {
                CustomerBean c = customers.get(i);
                out.println("<tr>");
                out.println("<td>" + c.getCustid() + "</td>");
                out.println("<td>" + c.getName() + "</td>");
                out.println("<td>" + c.getTel() + "</td>");
                out.println("<td>" + c.getAge() + "</td>");
                out.println("<td><a href=\"handleCustomer?action=delete&id="+c.getCustid()+"\" >delete</a></td>");
                                out.println("<td><a href=\"handleCustomer?action=getEditCustomer&id="+c.getCustid()+"\">delete</a></td>");

                out.println("</tr>");
            }
            out.println("</table>");

        %>
    </body>
</html>
