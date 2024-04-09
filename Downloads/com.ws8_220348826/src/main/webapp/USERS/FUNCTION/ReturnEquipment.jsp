<%-- 
    Document   : ReturnEquipment
    Created on : 9 Apr 2024, 1:29:39 am
    Author     : sean3
--%>
<%@page import="java.util.ArrayList, ict.bean.EquipmentBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>歸還裝備</h1>
        <form  method=“get" action="handleEdit">
            <table>
                <tr>
                    <td>
                        <input type="hidden" name="action"  value="return" />
                    </td></tr><tr><td>
                        ID：  <input name="bid"  type="text" value="" /> 
                    </td></tr>
                <tr><td>
                        <input type="submit" value="submit"/> <br></td>
                </tr>
            </table>
        </form>
    </body>
</html>
