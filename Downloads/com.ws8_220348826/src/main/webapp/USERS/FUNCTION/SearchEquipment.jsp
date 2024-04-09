<%-- 
    Document   : SearchEquipment
    Created on : 3 Apr 2024, 3:08:27 am
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
        <h1>搜查裝備</h1>
        <form method="get" action="/com.ws8_220348826/HandleEquipment" >
            <input type="hidden" name="action" value="search"/>
            <label>裝備名稱: </label>
            <input type="text" name="equipments" />
            <input type="submit" value="search" />
        </form>
        <a href="USERS/User.jsp">Back to user</a>

    </body>
</html>
