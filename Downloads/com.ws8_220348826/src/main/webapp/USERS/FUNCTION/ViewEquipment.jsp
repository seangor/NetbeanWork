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
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="USERS/FUNCTION/css/style.css">
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f4f4f4;
                margin: 0;

            }
            h1 {
                color: #333;
                text-align: center;
            }
            table {
                width: 80%;
                margin-left: auto;
                margin-right: auto;
                border-collapse: collapse;
                background-color: #fff;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #3445b4;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            tr:hover {
                background-color: #ddd;
            }
            a {
                text-decoration: none;
                color: #333;
            }
            .back-link {
                display: block;
                width: fit-content;
                margin: 20px auto;
                padding: 10px 15px;
                background-color: #4CAF50;
                color: white;
                text-align: center;
                border-radius: 5px;
            }
            .back-link:hover {
                background-color: #45a049;
            }
            .icon-img {
                width: 20px;
                height: 20px;
                vertical-align: middle;
            }

            .btn-1,
            .btn-1 *,
            .btn-1 :after,
            .btn-1 :before,
            .btn-1:after,
            .btn-1:before {
                border: 0 solid;
                box-sizing: border-box;
            }
            .btn-1 {
                -webkit-tap-highlight-color: transparent;
                -webkit-appearance: button;
                background-color: #000;
                background-image: none;
                color: #fff;
                cursor: pointer;
                font-family: ui-sans-serif, system-ui, -apple-system, BlinkMacSystemFont,
                    Segoe UI, Roboto, Helvetica Neue, Arial, Noto Sans, sans-serif,
                    Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol, Noto Color Emoji;
                font-size: 100%;
                font-weight: 900;
                line-height: 1.5;
                margin: 0;
                -webkit-mask-image: -webkit-radial-gradient(#000, #fff);
                padding: 0;
                text-transform: uppercase;
                margin-top:20px;
            }

            .button-container {
                display: flex;
                justify-content: flex-end; /* 使子元素对齐到容器的右侧 */
            }
            .btn-1:disabled {
                cursor: default;
            }
            .btn-1:-moz-focusring {
                outline: auto;
            }
            .btn-1 svg {
                display: block;
                vertical-align: middle;
            }
            .btn-1 [hidden] {
                display: none;
            }
            .btn-1 {
                border-radius: 99rem;
                border-width: 2px;
                padding: 0.8rem 3rem;
            }
            .btn-1:hover {
                color: #999;
            }


        </style>

    </head>
    <body>
        <jsp:include page="/WEB-INF/header.jsp"  />


        <%
            ArrayList<EquipmentBean> eqs = (ArrayList<EquipmentBean>) request.getAttribute("Equipments");
            if (session.getAttribute("equipments") == null) {
                session.setAttribute("equipments", new ArrayList<EquipmentBean>());
            }
        %>
        <h1>View Equipment</h1>
        <div class='button-container'>
            <button class='btn-1' onclick='redirectToViewCart()'>View Cart</button>
        </div>

        <form  action="<%=request.getContextPath()%>/HandleEquipment" method="get" >
            <input type="hidden" name="action" value="filter" />

            <span>Campus:</span>
            <select name="campusOp" >
                <option value="0">All</option>
                <option value="1">Chai Wan</option>
                <option value="2">Lee Wai Lee</option>
                <option value="3">Sha Tin</option>
                <option value="4">Tuen Mun</option>
                <option value="5">Tsing Yi</option>

            </select>
            <input type="submit" />

        </form>

        <table border='1' >
            <tr>
                <th>ID</th><th>Image</th><th>Name</th><th>Campus</th><th>Status</th><th>Stock</th><th>Add to Wishlist</th><th>Add to Cart</th>
            </tr>
            <%
                for (int i = 0; i < eqs.size(); i++) {
                    EquipmentBean c = eqs.get(i);
            %>
            <tr>

                <td><%=c.getEid()%></td>
                <td><img width=108px height=80px src="img/<%=c.getImgsrc()%>"/></td>
                <td><%=c.getEName()%></td>
                <td><%=c.getCampus()%></td>
                <td><%=c.getCampusid()%></td>
                <td><%=c.getEstatus()%></td>
                <td><%=c.getQuantity()%></td>
                <td style="text-align: center; "><a href="HandleWishlist?action=add&eid=<%=c.getEid()%>&wid=<%=c.getWid()%>" >
                        <% if (c.getWid() != 0) { %>
                        <img src="img/heart.png" style="width: 20px; height: 20px; padding-top: 3px; " />

                        <% } else { %>
                        <img src="img/like.png" style="width: 20px; height: 20px; alignContent: center; padding-top: 3px; " />

                        <%}%>
                    </a></td>
                    <% if (c.getQuantity() == 0) {
                                out.println("<td>"
                                        + "<input type=submit value=沽清 disabled />"
                                        + "</td>");
                            } else if (session.getAttribute("E" + c.getEid()) == null && session.getAttribute("C" + c.getCampusid()) == null) {
                                out.println("<td><form action=handleEdit method=get >"
                                        + "<input type=hidden name=action value=addCart />"
                                        + "<input type=hidden name=eid value=" + c.getEid() + " />"
                                        + "<input type=hidden name=cid value=" + c.getCampusid() + " />"
                                        + "<input type=submit value=預訂 />"
                                        + "</form></td>");
                            } else if (session.getAttribute("E" + c.getEid()) != null && session.getAttribute("C" + c.getCampusid()) != null) {
                                out.println("<td><form action=handleEdit method=get >"
                                        + "<input type=hidden name=action value=removeCart />"
                                        + "<input type=hidden name=eid value=" + c.getEid() + " />"
                                        + "<input type=hidden name=cid value=" + c.getCampusid() + " />"
                                        + "<input type=submit value=取消選擇 />"
                                        + "</form></td>");
                            } else {
                                out.println("<td><form action=handleEdit method=get >"
                                        + "<input type=hidden name=action value=addCart />"
                                        + "<input type=hidden name=eid value=" + c.getEid() + " />"
                                        + "<input type=hidden name=cid value=" + c.getCampusid() + " />"
                                        + "<input type=submit value=預訂 />"
                                        + "</form></td>");
                            }

                            out.println("</tr>");
                        }
                        out.println("</table>");
                    %>

            <script>
                function redirectToViewCart() {
                    window.location.href = '/com.ws8_220348826/HandleEquipment?action=cart';
                }
            </script>
            <a href="<%=request.getContextPath()%>/USERS/User.jsp">返回User</a>


            <jsp:include page="/WEB-INF/footer.jsp"  />


    </body>
</html>
