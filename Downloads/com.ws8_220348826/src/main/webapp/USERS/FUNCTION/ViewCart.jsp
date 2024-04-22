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
                       <jsp:include page="/WEB-INF/header.jsp"  />

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
                        <input type="hidden" name="type"  value="1" />

                    </td></tr>
                <tr><td>
                        取件日期 & 時間： <br>
                        <label for="month">Month/Day:</label>
                        <select id="month" name="month">
                            <% for (int i = 1; i <= 12; i++) {%>
                            <option value="<%= i%>"><%= i%></option>
                            <% } %>
                        </select>/
                        <select id="day" name="day">
                            <% for (int i = 1; i <= 31; i++) {%>
                            <option value="<%= i%>"><%= i%></option>
                            <% }%>
                        </select>
                        <br>
                        <label for="hourSelect">Select Time:</label>    
                        <select id="hourSelect" name="hour">
                            <% for (int i = 9; i <= 18; i++) {%>
                            <option value="<%= i%>"><%= i%></option>
                            <% } %>
                        </select>:
                        <select id="minuteSelect" name="minute">
                            <% for (int i = 0; i < 60; i += 15) {%>
                            <option value="<%= i%>"><%= String.format("%02d", i)%></option>
                            <% }%>
                        </select>
            </table>    
            <% %>

            <%if (!eqs.isEmpty()) {%>
            <input type="submit" value="confirm" />
            <%} else {%>
            <input type="submit" value="confirm" disabled />
            <% }%>
        </form>
    </body>
</html>
