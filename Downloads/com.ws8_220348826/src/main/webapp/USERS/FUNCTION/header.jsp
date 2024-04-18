<%-- 
    Document   : header
    Created on : 15 Apr 2024, 10:38:54 pm
    Author     : sean3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="/USERS/FUNCTION/css/style.css">
    </head>
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
                </body>
                </html>