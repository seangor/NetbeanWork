<%-- 
    Document   : addRecord
    Created on : 18 Mar 2024, 2:32:39 am
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
            <h1>Add Record</h1>
                    <form  method=“get" action="handleEdit">
            <table>
                <tr>
                    <td>
                        <input type="hidden" name="action"  value="add" />
                    </td></tr><tr><td>
                        Equipment ID  <input name="eid"  type="text" value="" /> 
                    </td></tr><tr><td>
                        <input type="submit" value="submit"/> <br></td>
                </tr>
            </table>
        </form>
                </body>
</html>
