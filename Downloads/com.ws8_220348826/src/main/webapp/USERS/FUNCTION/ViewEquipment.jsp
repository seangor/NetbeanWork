<%-- 
    Document   : ViewEquipment
    Created on : 18 Mar 2024, 2:36:53 am
    Author     : sean3
--%>
<%@page import="java.util.ArrayList, ict.bean.EquipmentBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/showEquipment.tld" prefix="ict" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Equipment</title>
    </head>
    <body>
        <%
            ArrayList<EquipmentBean>equipments = (ArrayList<EquipmentBean>) request.getAttribute("Equipments");


        %>   
        <ict:showEquipment list="<%=equipments%>" type="equipment" />
    </body>
</html>
