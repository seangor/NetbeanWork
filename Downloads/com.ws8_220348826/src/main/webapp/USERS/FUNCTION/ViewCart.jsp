<%-- 
    Document   : ViewCart
    Created on : 12 Apr 2024, 2:53:05 am
    Author     : sean3
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.EquipmentBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>查看訂單</h1>
        <table border='1' >
            <tr>
                <th>ID</th><th>圖片</th><th>名字</th>
            </tr>
            <%
                ArrayList<EquipmentBean> eqs = (ArrayList<EquipmentBean>) session.getAttribute("equipments");
                if (session.getAttribute("equipments") == null) {
                    session.setAttribute("equipments", new ArrayList<EquipmentBean>());
                }
                for (int i = 0; i < eqs.size(); i++) {
                    EquipmentBean c = eqs.get(i);
            %>
            <tr>
                <td><%= c.getEid() != 0 ? c.getEid() : ""%></td>
                <td><img width=108px height=80px src="<%= request.getContextPath()%>/img/<%= c.getImgsrc()%>"/></ntd>
                <td><%= c.getEName() != "" ? c.getEName() : ""%></td>
            </tr>
            <%
                }
            %>
        </table><br/>
        <form action="<%= request.getContextPath()%>/handleEdit" method="get" >
            <table>
                <tr>
                    <td>
                        <input type="hidden" name="action"  value="add" />
                    </td></tr>
                <tr><td>
                        取件日期 & 時間： 
                        <label for="month">Month:</label>
                        <select id="month" name="month">
                            <% for (int i = 1; i <= 12; i++) {%>
                            <option value="<%= i%>"><%= i%></option>
                            <% } %>
                        </select>

                        <label for="day">Day:</label>
                        <select id="day" name="day">
                            <% for (int i = 1; i <= 31; i++) {%>
                            <option value="<%= i%>"><%= i%></option>
                            <% }%>
                        </select>

                        <label for="hourSelect">Select Hour:</label>    
                        <select id="hourSelect" name="hour">
                           <% for (int i = 0; i <= 23; i++) {%>
                            <option value="<%= i%>"><%= i%>：00</option>
                            <% } %>
                        </select>

                        <label for="minuteSelect">Select Minute:</label>
                        <select id="minuteSelect" name="minute">
                            <% for (int i = 0; i < 60; i += 15) {%>
                            <option value="<%= i%>"><%= String.format("%02d", i)%></option>
                            <% }%>
                        </select>
            </table>    


            <%if (!eqs.isEmpty()) {%>
            <input type="submit" value="confirm" />
            <%} else {%>
              <input type="submit" value="confirm" disabled />
              <% } %>
        </form>
    </body>
</html>