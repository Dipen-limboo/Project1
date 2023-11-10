<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Controller.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./CSS/style.css">
<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<body>
    <header class="head">
        <a href="./products.jsp" style="color:white; text-decoration:none">
        <h1 style="letter-spacing: 2px; margin-left: 30px;"><span style="color: #333; background-color: white; border-radius: 50%; padding:1px 10px"> Y</span>akthungIpa</h1></a>
        <nav>
		    <ul>
		        <li><a href="./products.jsp">Home</a></li>
		        <li><a href="./display.jsp">Products</a></li>
		        <li><a href="./ContactUs.jsp">Contact Us</a></li>
		        <li><a href="showCart"><i class="fa-solid fa-cart-shopping" style="color: #f3f4f7;font-size:21px"></i></a></li>
		
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
		            <a href="#" class="dropbtn" style="background-color:blue; border-radius: 50%; padding:5px 12px; color:white; font-size:19px"><%= initEmail %></a>
		            <div class="dropdown-content" style="margin-top: 20px">
		                <a href="profie">Profile</a>
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
