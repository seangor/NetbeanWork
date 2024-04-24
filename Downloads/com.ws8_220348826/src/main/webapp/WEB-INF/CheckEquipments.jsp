<%-- 
    Document   : CheckEquipments
    Created on : 22 Apr 2024, 3:22:25 am
    Author     : sean3
--%>

<%@page import="ict.bean.EquipmentBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            
            .abc{
                margin-left: 100px;
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
            <div>
                <div class="abc">
                    <div id="content" class="p-4 p-md-5 pt-5">
                        <%
                            ArrayList<EquipmentBean> equipmentlist = (ArrayList<EquipmentBean>) request.getAttribute("Equipments");
                            String userType = (String) session.getAttribute("Account");
                            String action = (String) request.getParameter("action");
                        %>
                        <h1>View Equipment Status</h1>
                        <table border='1' >
                            <tr>
                                <% if (action.equalsIgnoreCase("list")) { %>
                                <th>ID</th>
                                <th>Image</th>
                                <th>Equipment Name</th>
                                <th>Quantity After Borrowing</th>
                                <th>Equipment Damaged</th>
                                <th>Total</th>
                                    <% } else if (action.equalsIgnoreCase("listStatus")) {%>
                                <th>ID</th>
                                <th>Image</th>
                                <th>Equipment Name</th>
                                <th>Equipment Borrowed</th>
                                <th>Equipment Damaged</th>
                                <th>Stock</th>
                                <th>Total</th>
                                    <%}%>
                            </tr>
                            <%                         for (int i = 0; i < equipmentlist.size(); i++) {
                                    EquipmentBean c = equipmentlist.get(i);


                            %> 
                            <tr>
                                <% if (action.equalsIgnoreCase("list")) {%>
                                <td style="width: 80px; text-align: center;"><%= c.getEid()%></td>
                                <td><img src="img/<%=c.getImgsrc()%>" width="80px" height="80px" alt="alt"/></td>
                                <td><%=c.getEName()%></td>
                                <td><%=c.getQuantity()%> => <%=c.getQuantity() - 1%></td>
                                <td><%=c.getDamagedQty()%></td>
                                <td><%=c.getTotalQty()%></td>

                                <% } else if (action.equalsIgnoreCase("listStatus")) {%>
                                <td style="width: 80px; text-align: center;"><%= c.getEid()%></td>
                                <td><img src="img/<%=c.getImgsrc()%>" width="80px" height="80px" alt="alt"/></td>
                                <td><%=c.getEName()%></td>
                                <td><%=c.getBorrowQty()%></td>
                                <td><%=c.getDamagedQty()%></td>
                                <td><%=c.getQuantity()%> </td>  
                                <td><%=c.getTotalQty()%></td>
                                <% } %>
                            </tr>

                            <%}%>

                        </table>    
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
