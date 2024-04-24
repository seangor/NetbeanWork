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



        <div class="wrapper d-flex align-items-stretch">
            <nav id="sidebar">
                <div class="custom-menu">
                    <button type="button" id="sidebarCollapse" class="btn btn-primary">
                        <i class="fa fa-bars"></i>
                        <span class="sr-only">Toggle Menu</span>
                    </button>
                </div>
                <div class="p-4">
                    <h1><a href="index.html" class="logo">Hello, Username <span>Role, Campus</span></a></h1>
                    <ul class="list-unstyled components mb-5">
                        <li class="active">
                            <a href="<%= request.getContextPath()%>/HandleEquipment?action=list"><span class="fa fa-eye mr-3"></span>View Equipment</a>
                        </li>
                        <li>
                            <a href="<%= request.getContextPath()%>/HandleRequest?action=list"><span class="fa fa-eye mr-3"></span>View Request</a>
                        </li>
                        <li>
                            <a href="<%= request.getContextPath()%>/HandleOrder?action=list">
                                <span class="fa fa-eye mr-3"></span>View Order
                            </a>
                        </li>
                        <li>
                            <a href="<%= request.getContextPath()%>/HandleBorrowRecord?action=Recordlist"><span class="fa fa-eye mr-3"></span>View Record</a>
                        </li>
                        <li>
                            <a href="#"><span class="fa fa-eye mr-3"></span>View personal Info & update password/Info</a>
                        </li>
                        <li>
                            <a href="#"><span class="fa fa-bell mr-3"></span>Notification</a>
                        </li>
                        <li>
                            <a href="./USERS/User.jsp"><span class="fa fa-undo mr-3"></span>Back</a>
                        </li>
                    </ul>




                </div>
            </nav>

            <!-- Page Content  -->
            <div id="content" class="p-4 p-md-5 pt-5">

                <%
                    ArrayList<EquipmentBean> eqs = (ArrayList<EquipmentBean>) request.getAttribute("Equipments");
                    if (session.getAttribute("equipments") == null) {
                        session.setAttribute("equipments", new ArrayList<EquipmentBean>());
                    }

                    out.println("<h1>查看裝置狀態</h1>");

                    out.println("<table border='1' >");
                    out.println("<tr>");
                    out.println("<th>ID</th><th>圖片</th><th>名字</th><th>狀態</th><th>數量</th><th>加入願望清單</th><th>加入預訂</th>");
                    out.println("</tr>");
                    for (int i = 0; i < eqs.size(); i++) {
                        EquipmentBean c = eqs.get(i);
                        out.println("<tr>");
                        out.println("<td>" + c.getEid() + "</td>");
                        out.println("<td><img width=108px height=80px src=\"img/" + c.getImgsrc() + "\"/></td>");
                        out.println("<td>" + c.getEName() + "</td>");
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

                    out.println("<div class='button-container'>");  // 开始按钮容器
                    out.println("<button class='btn-1' onclick='redirectToViewCart()'>查看預定列表</button>");  // 按钮
                    out.println("</div>");  // 结束按钮容器
                    out.println("<script>");
                    out.println("function redirectToViewCart() {");
                    out.println("    window.location.href = '" + request.getContextPath() + "/USERS/FUNCTION/ViewCart.jsp';");
                    out.println("}");
                    out.println("</script>");

                %>
            </div>
        </div>

        
        <script src="USERS/FUNCTION/js/jquery.min.js"></script>
        <script src="USERS/FUNCTION/js/popper.js"></script>
        <script src="USERS/FUNCTION/js/bootstrap.min.js"></script>
        <script src="USERS/FUNCTION/js/main.js"></script>
    </body>
</html>
