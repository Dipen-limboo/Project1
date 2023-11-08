<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="product.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Transaction</title>
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
	        <h2>In-Sourcing Transactions</h2>
	        <table>
	        	<tr>
	        		
	        		<th>Product Id</th>
	        		<!-- <th>Product Image</th> -->
	        		<th>Product Name</th>
	        		<th>Types</th>
	        		<th>Quantity</th>
	        		<th>User name</th>
	        		<th>Date</th>
	        		
	        	</tr>
	        	<% 
	        	 ArrayList<Product> productList = (ArrayList<Product>) request.getAttribute("stocks");
	            if (productList != null) {
	                for (Product product : productList) {
	        	%>
	        	<tr>
	        		<td><%= product.getProductID() %></td>
	        	<%-- 	<td>
					<img src="data:image/jpeg;base64, <%= product.getProductImage() %>" alt="here is an image" style="height: 100px; width: 150px; margin-left:15px">
					</td> --%>
	        		<td><%= product.getProductName() %></td>
	        		<td><%= product.getType() %></td>
	        		<td><%= product.getProductQuantity() %></td>
	        		<td><%= product.getFirstname() %> <%= product.getLastname() %></td>
	        		<td><%= product.getDateOrder() %></td>
	        	</tr>
	        	<%	}
	        	}%>
	        </table>
	        </div>
	</div>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>