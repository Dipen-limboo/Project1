<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="product.Product" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Product</title>
    <link rel="stylesheet" href="./CSS/styles.css">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    
</head>
<body style="min-height: 100vh">
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
	                <li><a href="" style="color: white; text-decoration: none">In-Sourcing transaction</a></li>
	                <li><a href="" style="color: white; text-decoration: none">Out-Sourcing transaction</a></li>
	            </ul>
	        </section>
	        </div>
	        <div class="dashboard-content"> 
	        	<h2>All Products</h2>
	       
		    <table >
		        <tr>
		            <th>Name</th>
		            <th>Image</th>
		            <th>Price</th>
		            <th>Quantity</th>
		            <th>Keywords</th>
		            <th>Description</th>
		            <th>Colors</th>
		            <th>Sizes</th>
		            <th colspan="2">Action</th>
		            
		        </tr>
		        <%
		            ArrayList<Product> productList = (ArrayList<Product>) request.getAttribute("productList");
		            if (productList != null) {
		                for (Product product : productList) {
		        %>
		        <tr>
		            <td><%= product.getProductName() %></td>
		            <td><img src="data:image/jpeg;base64, <%= product.getProductImage() %>" alt="Product-image" style="height: 100px; width: 250px"></td>
		            <td><%= product.getProductPrice() %></td>
		            <td><%= product.getProductQuantity() %> </td>
		            <td><%= product.getProductKeyword() %></td>
		            <td><%= product.getProductDescription() %></td>
		            <%
		                for (String color : product.getColors()) {
		            %>
		            <td><%= color %></td>
		            <%
		                }
		            %>
		            <%
		                for (String size : product.getSize()) {
		            %>
		            <td><%= size %></td>
		            <%
		                }
		            %>
		            <td><a href='EditServlet?product_id=<%= product.getProductID() %>' style="color: white; text-decoration: none; padding-right: 10px;">Edit</a></td>
		            <td><a href='DeleteServlet?product_id=<%= product.getProductID() %>' style="color: white; text-decoration: none"><i class="fa-solid fa-x" style="color: #fafcff;"></i></a></td>
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
