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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
        <link rel="stylesheet" href="/USERS/FUNCTION/css/style.css">
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
            #content {

                flex-direction: column;
                justify-content: center; /* 垂直居中 */
                align-items: center; /* 水平居中 */
                min-height: 100vh; /* 確保div至少和視窗一樣高 */
                text-align: center; /* 確保文本對齊中心 */
            }

            .abc{
                margin-left: 200px;
            }

            table {
                width: 100%; /* 或你期望的其他寬度 */
                margin-top: 20px; /* 如果需要，在表格與標題之間添加一些間距 */

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

            .wrapper {
                width: 100%;               /* 设置宽度为视口的100% */
                min-height: 100vh;         /* 设置最小高度为视口的100% */
                display: flex;             /* 使用弹性盒模型 */
                align-items: stretch;      /* 子项沿交叉轴的拉伸 */
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
                            <a href="#"><span class="fa fa-eye mr-3"></span>View Equipment</a>
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
                            <a href="#"><span class="fa fa-sticky-note mr-3"></span> View personal Info & update password/Info</a>
                        </li>
                        <li>
                            <a href="#"><span class="fa fa-suitcase mr-3"></span> Gallery</a>
                        </li>
                        <li>
                            <a href="#"><span class="fa fa-cogs mr-3"></span> Services</a>
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
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
