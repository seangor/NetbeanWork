<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, ict.bean.UserBean, ict.db.UserDB"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Users</title>
    </head>
    <body>
        <%
            UserDB userDB = new UserDB("jdbc:mysql://localhost:3306/vtcit", "root", "");
            ArrayList<UserBean> users = userDB.getAllUsers();
            out.println("<h1>Users</h1>");
            out.println("<table border='1'>");
           
            out.println("<tr><th>Uid</th><th>Username</th><th>Identity</th><th>Tel</th><th>Actions</th></tr>");
            for (UserBean user : users) {
                out.println("<tr>");
                out.println("<td>" + user.getUid() + "</td>");
                out.println("<td>" + user.getUsername() + "</td>");
                
                out.println("<td>" + user.getIdentity() + "</td>");
                out.println("<td>" + user.getTel() + "</td>");
                out.println("<td><a href='editUser?id=" + user.getUid() + "'>Edit</a> | <a href='deleteUser?id=" + user.getUid() + "'>Delete</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        %>
    </body>
</html>