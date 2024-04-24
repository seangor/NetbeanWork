<%-- 
    Document   : ViewRecord
    Created on : 4 Apr 2024, 2:28:40 am
    Author     : sean3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, ict.bean.RecordBean"%>
<%@ taglib uri="/WEB-INF/tlds/showStatus" prefix="ict" %>

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
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #3445b4;
                color: white;
                text-align: center;  /* 確保標題文字居中對齊 */
                font-size: 16px;    /* 設定一個適中的字體大小 */
                padding: 12px 15px; /* 增加內距使文字顯得更加寬敞 */
                white-space: nowrap;  /* 防止文字換行 */
                transform: rotate(0deg); /* 確保文字不被旋轉 */
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
                margin-left: 200px;
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
            <div>
                <div class="abc">
                    <div id="content" class="p-4 p-md-5 pt-5">
                        <%                String action = request.getParameter("action");
                            String userType = (String) session.getAttribute("Account");

                        %>
                        <h1>查看租借記錄</h1>
                        <table border='1'>

                            <tr>
                                <th>記錄ID</th>
                                <th>名字</th>
                                <th>圖片</th>
                                <th>租借日期</th>
                                <th>截止日期</th>
                                <th>狀態</th>
                                <th>行動</th>
                                    <%if (action.equalsIgnoreCase("checkReturn")) { %>
                                <th>Report Damage</th> 
                                    <%}%>
                            </tr>
                            <%
                                ArrayList<RecordBean> Record = (ArrayList<RecordBean>) request.getAttribute("BRecord");
                                for (int i = 0; i < Record.size(); i++) {
                                    RecordBean c = Record.get(i);
                            %>
                            <% if (action.equalsIgnoreCase("Recordlist")) {%>
                            <form action="<%=request.getContextPath()%>/HandleBorrowRecord" method="get">
                                <%} else if (action.equalsIgnoreCase("checkReturn")) {%>
                                <form action="<%=request.getContextPath()%>/handleEdit" method="get">

                                    <%}%>
                                    <tr>
                                        <td><%= c.getBid()%></td>
                                        <td><%= c.getEname()%></td>
                                        <td><img src="img/<%= c.getImgsrc()%>" alt="alt" height="80" width="80"/></td>
                                        <td><%= c.getBorrowDate()%></td>
                                        <td><%= c.getDeadline()%></td>
                                        <td><ict:showStatus item="borrowrecord" status="<%=c.getStatus()%>" /></td>
                                        <td>
                                            <% if (action.equalsIgnoreCase("Recordlist")) {
                                                    if (c.getStatus().equalsIgnoreCase("1")) {
                                            %>

                                            <input type="checkbox" name="selectedEq" onclick="toggleButton()" value="<%= c.getBid()%>" />
                                            歸還
                                            <input name="action" type="hidden" value="checkout" />
                                            <% } else {%>
                                            <input type="checkbox" name="selectedEq" onclick="toggleButton()" value="<%= c.getBid()%>" disabled/>
                                            歸還(已處理)                                                   
                                            <% } %>
                                            <%} else if (action.equalsIgnoreCase("checkReturn")) {
                                                if (c.getStatus().equalsIgnoreCase("3")) {
                                            %>
                                            <input type="checkbox" name="selectedEq" onclick="toggleButton()" value="<%= c.getBid()%>" />
                                            確認收到
                                            <input name="action" type="hidden" value="UpdateReturn" />
                                            <% } else {%>
                                            <input type="checkbox" name="selectedEq" onclick="toggleButton()" value="<%= c.getBid()%>" disabled/>
                                            確認收到(已處理)
                                            <%}
                                                }%>
                                        </td>
                                        <%if (action.equalsIgnoreCase("checkReturn")) {
                                                if (c.getStatus().equalsIgnoreCase("3")) {
                                        %>
                                        <td>
                                            <a href="<%= request.getContextPath()%>/handleEdit?bid=<%= c.getBid()%>&action=reportDamage">Report Damage</a>
                                        </td> 
                                        <%} else {%>
                                        <td> <u><b>Report Damage</b></u></td>
                                                <% }%>
                                    </tr>        
                                    <% }
                                        }%>
                                    </table>
                                    <br>


                                    <div style="text-align: right;">
                                        <button id="submitButton" class="btn-1" type="submit" disabled>確認</button>
                                    </div>
                                </form>


                                <script>
                                    function toggleButton() {
                                        var checkbox = document.getElementsByName('selectedEq');
                                        var button = document.getElementById('submitButton');
                                        var checked = false;

                                        for (var i = 0; i < checkbox.length; i++) {
                                            if (checkbox[i].checked) {
                                                checked = true;
                                                break;
                                            }
                                        }

                                        if (checked) {
                                            button.disabled = false;
                                        } else {
                                            button.disabled = true;
                                        }
                                    }
                                </script>


                                </div>
                                </div>
                                </div>
                                </div>
                                </body>
                                </html>