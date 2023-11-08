<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "Controller.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Changing Role Panel</title>
<link rel="stylesheet" href="./CSS/styles.css">

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
	        <div class="dashboard-content"> 
	        	<h2>Change Role</h2>
				<form class="userPanel" action="updateRole" method="Post">
				<% User user = (User)request.getAttribute("role");
					
				%>
					<input type="hidden" name="id" value="<%= user.getId() %>">
					
					<label style="margin-top:20px">First name: <%= user.getFname() %></label><br>
					<label>Last name: <%= user.getLname() %></label><br>
					<label>Email: <%= user.getEmail() %></label><br>
					
					<label>Role: </label>
					<%-- <p>(If you want to change the role of user to admin or vice versa)</p>
					<input type="text" name="role" value="<%= user.getRole() %>">
					<br> --%>
					<select name="role" style="margin-left: 10px">
					<option value = "user">User</option>
					<option value = "admin">Admin</option>
					</select>
					<button>Change</button>
				</form>
	</div>
	
	</div>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>