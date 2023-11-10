<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "Order_package.Orders" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Order</title>
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
	      		<h2>All Products</h2>
				<table>
					<tr>
						<th style="">Order Id </th>
						<th>User Id</th>
						<th>Phone</th>
						<th style="">Location</th>
						<th style="">Order Date </th>
						<th>State</th>
					</tr>
					<% List <Orders> list = (List <Orders>) request.getAttribute("orderList");
						if (list != null && !list.isEmpty()){
							for (Orders order: list){
					%>
					<tr>
					
						<td><%= order.getOrderId() %></td>
						<td><%= order.getUserId() %></td>
						<td><%= order.getPhone() %></td>
						<td><%= order.getLocation() %></td>
						<td><%= order.getDateOrder() %></td>
						<td><a href="state?order_id=<%= order.getOrderId() %>&&h3w=<%= order.getUserId() %>" ><button ><%= order.getState() %></button></a></td>
					
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