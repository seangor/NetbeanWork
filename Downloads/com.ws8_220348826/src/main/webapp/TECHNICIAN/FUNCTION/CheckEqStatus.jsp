<%-- 
    Document   : CheckEqStatus
    Created on : 22 Apr 2024, 5:17:52 am
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
                                      <jsp:include page="/WEB-INF/header.jsp"  />

        
               <jsp:include page="${request.getContextPath()}/WEB-INF/CheckEquipments.jsp"  />

        
    </body>
</html>
