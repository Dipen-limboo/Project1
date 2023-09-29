<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Controller.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./CSS/style.css">

</head>
<body>
    <header class="head">
        <h1 style="letter-spacing: 2px; margin-left: 30px;"><span style="color: #333; background-color: white; border-radius: 50%; padding:5px"> Y</span>akthungIpa</h1>
        <nav>
            <ul>
                <li><a href="./products.jsp">Home</a></li>
                <li><a href="./products.jsp">Products</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="#">Cart</a></li>
                <%
                    User user = (User) session.getAttribute("user");

                    if (user != null) {
                        String userEmail = user.getEmail();
                        String initEmail = userEmail.substring(0, 1).toUpperCase();
                %>
                <li class="dropdown">
                    <a href="#" class="dropbtn"><%= initEmail %></a>
                    <div class="dropdown-content">
                        <a href="#">Orders</a>
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
