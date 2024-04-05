<%-- 
    Document   : editCustomer
    Created on : 29 Mar 2024, 11:19:19 pm
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
        <jsp:useBean id="c" scope="request" class="ict.bean.CustomerBean"/>
        <%
            String type = c.getCustid() != null ? "edit" : "add";
            String id = c.getCustid() != null ? c.getCustid() : "";
            String name = c.getName() != null ? c.getName() : "";
            String tel = c.getTel() != null ? c.getTel() : "";
            String age = String.valueOf(c.getAge()) != null ? String.valueOf(c.getAge()) :"";
        %>
        <form  method=“get" action="handleEdit">
            <table>
                <tr>
                    <td>
                        <input type="hidden" name="action"  value="<%=type%>" />
                    </td></tr><tr><td>
                        ID  <input name="id"  type="text" value="<%=id%>" /> 
                    </td></tr><tr><td>
                        Name <input name="name"  type="text" value="<%=name%>" /> <br>
                    </td></tr><tr><td>
                        Tel <input name="tel"  type="text" value="<%=tel%>" /> <br>
                    </td></tr><tr><td>
                        Age <input name="age"  type="text" value="<%=age%>" /> <br>
                    </td></tr><tr><td>
                        <input type="submit" value="submit"/> <br></td>
                </tr>
            </table>
        </form>

    </body>
</html>
