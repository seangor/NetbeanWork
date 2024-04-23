<%-- 
    Document   : ViewEquipment
    Created on : 18 Mar 2024, 2:36:53 am
    Author     : sean3
--%>
<%@page import="java.util.ArrayList, ict.bean.EquipmentBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Equipment</title>

    </head>
    <body>
        <jsp:include page="/WEB-INF/header.jsp"  />



                <%
                    ArrayList<EquipmentBean> eqs = (ArrayList<EquipmentBean>) request.getAttribute("Equipments");
                    if (session.getAttribute("equipments") == null) {
                        session.setAttribute("equipments", new ArrayList<EquipmentBean>());
                    }

                    out.println("<h1>View Equipment</h1>");
                    out.println("<a href=\"" + request.getContextPath() + "/USERS/FUNCTION/ViewCart.jsp\">Check Cart</a> <br>");
                    out.println("<a href=\"" + request.getContextPath() + "/USERS/User.jsp\">Back to User</a>");

                    out.println("<table border='1' >");
                    out.println("<tr>");
                    out.println("<th>ID</th><th>Image</th><th>Name</th><th>Campus</th><th>Status</th><th>Stock</th><th>Add to Wishlist</th><th>Add to Cart</th>");
                    out.println("</tr>");
                    for (int i = 0; i < eqs.size(); i++) {
                        EquipmentBean c = eqs.get(i);
                        out.println("<tr>");
                        out.println("<td>" + c.getEid() + "</td>");
                        out.println("<td><img width=108px height=80px src=\"img/" + c.getImgsrc() + "\"/></td>");
                        out.println("<td>" + c.getEName() + "</td>");
                        out.println("<td>" + c.getCampus() + "</td>");
                        out.println("<td>" + c.getEstatus() + "</td>");
                        out.println("<td>" + c.getQuantity() + "</td>");
                        out.println("<td style=\"text-align: center; \"><a href=\"HandleWishlist?action=add&eid=" + c.getEid() + "&wid=" + c.getWid() + "\" >");
                        if (c.getWid() != 0) {
                            out.println("<img src=\"img/heart.png\" style=\"width: 20px; height: 20px; padding-top: 3px; \" />");

                        } else {
                            out.println("<img src=\"img/like.png\" style=\"width: 20px; height: 20px; alignContent: center; padding-top: 3px; \" />");

                        }
                        out.println("</a></td>");
                        if (c.getQuantity() == 0) {
                            out.println("<td>"
                                    + "<input type=submit value=沽清 disabled />"
                                    + "</td>");
                        } else if (session.getAttribute("E" + c.getEid()) == null) {
                            out.println("<td><form action=handleEdit method=get >"
                                    + "<input type=hidden name=action value=addCart />"
                                    + "<input type=hidden name=eid value=" + c.getEid() + " />"
                                    + "<input type=submit value=預訂 />"
                                    + "</form></td>");
                        } else {
                            out.println("<td><form action=handleEdit method=get >"
                                    + "<input type=hidden name=action value=removeCart />"
                                    + "<input type=hidden name=eid value=" + c.getEid() + " />"
                                    + "<input type=submit value=取消選擇 />"
                                    + "</form></td>");
                        }

                        out.println("</tr>");
                    }
                    out.println("</table>");
                %>

        <jsp:include page="/WEB-INF/footer.jsp"  />


    </body>
</html>
