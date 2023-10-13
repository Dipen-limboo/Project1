<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Controller.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./CSS/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <header class="head">
        <h1 style="letter-spacing: 2px; margin-left: 30px;"><span style="color: #333; background-color: white; border-radius: 50%; padding:5px"> Y</span>akthungIpa</h1>
        <nav>
		    <ul>
		        <li><a href="./products.jsp">Home</a></li>
		        <li><a href="./display.jsp">Products</a></li>
		        <li><a href="./ContactUs.jsp">Contact</a></li>
		        <li><a href="showCart">Cart</a></li>
		
		        <li>
		            <form action="Categories" method="GET">
		                <input type="text" name="product_keyword" placeholder="Search">
		                <button type="submit"><i class="fa fa-search"></i></button>
		            </form>
		        </li>
		
		        <%
		            User user = (User) session.getAttribute("user");
		
		            if (user != null) {
		                String userEmail = user.getEmail();
		                String initEmail = userEmail.substring(0, 1).toUpperCase();
		        %>
		        <li class="dropdown">
		            <a href="#" class="dropbtn" style="color: #333; background-color:white; border-radius: 50%; padding: 10px;"><%= initEmail %></a>
		            <div class="dropdown-content" style="margin-top: 20px">
		                <a href="viewOrder">Orders</a>
		                <a href="logOut">Logout</a>
		            </div>
		        </li>
		        <% } else {
		        %>
		        <li><a href="login.jsp">Login</a></li>
		        <%
		        }
		        %>
		    </ul>
		</nav>
    </header>
</body>
</html>
