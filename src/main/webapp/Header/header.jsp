<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Controller.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<link rel="stylesheet" href="../CSS/styles.css">
</head>
<body>
	<header class = "heads">
        <h1 style="font-size: 30px;">  Welcome To Admin Panel</h1>
        <nav>
            <ul>
                <li><a href="admin.jsp">Dashboard</a></li>
                <li><a href="ViewServlet">Products</a></li>
                <li><a href="orderServlet">Orders</a></li>
                <li><a href="userServlet">Users</a></li>
                <li class="dropdown">
                    <a href="#" class="dropbtn">Profile</a>
                    <div class="dropdown-content">
                        <a href="logOut">Logout</a>
                    </div>
                </li>
            </ul>
        </nav>
    </header>
</body>
</html>