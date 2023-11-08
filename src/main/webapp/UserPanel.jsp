<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "Controller.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Panel</title>
<link rel="stylesheet" href="./CSS/styles.css">
<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<body>

<jsp:include page="./Header/header.jsp"></jsp:include>
 <div class="transactionPanel">
		<div class ="dashboard">
			<section class="product-management" id="Dashboard">
	            <h2>Product Management</h2>
	            <ul>
	                <li><a href="./addProduct.jsp" style="color: white; text-decoration: none">Add Product</a></li>
	                <li><a href="ViewServlet" style="color: white; text-decoration: none">Edit Product</a></li>
	                <li><a href="ViewServlet" style="color: white; text-decoration: none">Delete Product</a></li>
	            </ul>
	        </section>
	        <section class="order-management">
	            <h2>Order Management</h2>
	            <ul>
	                <li><a href="#" style="color: white; text-decoration: none">View Orders</a></li>
	                <li><a href="#"style="color: white; text-decoration: none">Process Orders</a></li>
	            </ul>
	        </section>
	        <section class="user-management">
	            <h2>User Management</h2>
	            <ul>
	                <li><a href="userServlet" style="color: white; text-decoration: none">View Users</a></li>
	                <li><a href="userServlet" style="color: white; text-decoration: none">Edit Users</a></li>
	                <li><a href="userServlet" style="color: white; text-decoration: none">Delete Users</a></li>
	            </ul>
	        </section>
	        <section class="stock-management">
	            <h2>Transaction Management</h2>
	             <ul>
	                <li><a href="transaction" style="color: white; text-decoration: none">All transaction</a></li>
	                <li><a href="insourcing" style="color: white; text-decoration: none">In-Sourcing transaction</a></li>
	                <li><a href="outsourcing" style="color: white; text-decoration: none">Out-Sourcing transaction</a></li>
	            </ul>
	        </section>
	        </div>
	<div class="dashboard-content" >
	<h2>All Users</h2>
		<table style= "text-align:center; margin-left: 10px;">
			<tr>
				<th style="width: 100px; padding: 0 10px">User Id </th>
				<th style="width: 100px; padding: 10px">First Name </th>
				<th style="width: 350px; padding: 10px">Last Name</th>
				<th style="width: 150px; padding: 10px">Email</th>
				<th style="width: 100px; padding: 10px">Password</th>
				<th style="width: 100px; padding: 10px">Role</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<% List <User> list = (List <User>) request.getAttribute("userList");
				if (list != null && !list.isEmpty()){
					for (User usr: list){
			%>
			<tr>
			
				<td><%= usr.getId() %></td>
				<td><%= usr.getFname() %></td>
				<td><%= usr.getLname() %></td>
				<td><%= usr.getEmail() %></td>
				<td><%= usr.getPassword() %></td>
				<td><%= usr.getRole() %></td>
				<td><a href="editUserPanel?id=<%= usr.getId() %>"style="color: black; text-decoration: none;padding-right: 10px;">Change</a></td>
				<td><a href="deleteUserPanel?id=<%= usr.getId() %>" style="color: black; text-decoration: none"><i class="fa-solid fa-x" style="color: #333;"></i></a></td>
			
			</tr>
			<%
					}
				}
			%>
		</table>
	</div>
	</div>
<jsp:include page="./Footer/footer.jsp"></jsp:include>

</body>
</html>