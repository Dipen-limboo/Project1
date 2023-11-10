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
        <a href="./admin.jsp" style="color:white; text-decoration:none">
		<h1 style="letter-spacing: 2px; margin-left: 30px;"><span style="color: #333; background-color: white; border-radius: 50%; padding:1px 10px"> Y</span>akthungs</h1>        </a>
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