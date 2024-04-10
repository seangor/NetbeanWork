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
                background-color: #4CAF50;
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
        </style>
    </head>
    <body>
        <div class="wrapper d-flex align-items-stretch">
            <nav id="sidebar">
                <div class="custom-menu">
                    <button type="button" id="sidebarCollapse" class="btn btn-primary">
                        <i class="fa fa-bars"></i>
                        <span class="sr-only">Toggle Menu</span>
                    </button>
                </div>
                <div class="p-4">
                    <h1><a href="index.html" class="logo">Portfolic <span>Portfolio Agency</span></a></h1>
                    <ul class="list-unstyled components mb-5">
                        <li class="active">
                            <a href="#"><span class="fa fa-home mr-3"></span> Home</a>
                        </li>
                        <li>
                            <a href="#"><span class="fa fa-user mr-3"></span> About</a>
                        </li>
                        <li>
                            <a href="#"><span class="fa fa-briefcase mr-3"></span> Works</a>
                        </li>
                        <li>
                            <a href="#"><span class="fa fa-sticky-note mr-3"></span> Blog</a>
                        </li>
                        <li>
                            <a href="#"><span class="fa fa-suitcase mr-3"></span> Gallery</a>
                        </li>
                        <li>
                            <a href="#"><span class="fa fa-cogs mr-3"></span> Services</a>
                        </li>
                        <li>
                            <a href="#"><span class="fa fa-paper-plane mr-3"></span> Contacts</a>
                        </li>
                    </ul>

                    <div class="mb-5">
                        <h3 class="h6 mb-3">Subscribe for newsletter</h3>
                        <form action="#" class="subscribe-form">
                            <div class="form-group d-flex">
                                <div class="icon"><span class="icon-paper-plane"></span></div>
                                <input type="text" class="form-control" placeholder="Enter Email Address">
                            </div>
                        </form>
                    </div>

                    <div class="footer">
                        <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            Copyright &copy;
                            <script>document.write(new Date().getFullYear());</script> All rights reserved | This template
                            is made with <i class="icon-heart" aria-hidden="true"></i> by <a href="https://colorlib.com"
                                                                                             target="_blank">Colorlib.com</a>
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        </p>
                    </div>

                </div>
            </nav>

            <!-- Page Content  -->
            <div id="content" class="p-4 p-md-5 pt-5">
                
                <%
                    ArrayList<EquipmentBean> eqs = (ArrayList<EquipmentBean>) request.getAttribute("Equipments");
                    out.println("<h1>查看裝置狀態</h1>");
                    out.println("<table border='1' >");
                    out.println("<tr>");
                    out.println("<th>ID</th><th>名字</th><th>狀態</th><th>數量</th><th>加入願望清單</th>");
                    out.println("</tr>");
                    for (int i = 0; i < eqs.size(); i++) {
                        EquipmentBean c = eqs.get(i);
                        out.println("<tr>");
                        out.println("<td>" + c.getEid() + "</td>");
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
                        out.println("</tr>");
                    }
                    out.println("</table>");
                %>
            </div>
        </div>

        <a href="USERS/FUNCTION/addRecord.jsp">Back to user</a>
        <img src="img/like.png" style="width: 20px; height: 20px" />
        <script src="USERS/FUNCTION/js/jquery.min.js"></script>
        <script src="USERS/FUNCTION/js/popper.js"></script>
        <script src="USERS/FUNCTION/js/bootstrap.min.js"></script>
        <script src="USERS/FUNCTION/js/main.js"></script>
    </body>
</html>
