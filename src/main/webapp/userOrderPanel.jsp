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
<jsp:include page="./Header/userHeader.jsp"></jsp:include>
	<main>
    <div class="left">
    	<div style="margin-top: 1rem; text-align: center;">
    		<% String username= (String)session.getAttribute("username"); %>
    		<p style="text-align: left; color: green; font-style:italic">Welcome, <%= username %></p>
    		<h2 style="font-weight: normal; font-family: Times New Roman; color: #333; font-size: 22px; font-weight: bold">Manage your Account</h2>
   	 	</div>
   	 	 
   	 	<div class="cate" style="margin:10px 0 0 5px; margin-bottom: 1px">
   	 		<ul>
   	 		<li style="padding: 0 0 0 15px; font-size: 15px"><a href="profie" style="color:#333">My Profile</a></li>
   	 		<li style="padding: 0 0 0 15px; font-size: 15px"><a href="viewOrder" style="color:#333">My orders</a></li>
   	 		</ul>
   	 	</div>
   	 	<div style="margin-top: 0.5rem; text-align: left; font-family:caliber; padding-left: 13px">
   	 	<% int userId = (Integer) session.getAttribute("userId"); %>
    		<h2 style="font-weight: normal; color: #333; font-size: 22px; font-weight: bold"><a href="review?id=<%= userId %>" style="text-decoration: none; color:#333">My reviews</a> </h2>
   	 	</div>
    </div>
    
 	<div class="review_right">
    	<div style="margin-top: 1rem; color: #333">
    		<h2 style="text-align: left; margin-bottom: 0.5rem">My Orders</h2>
    	</div>
    		<% List <Orders> list = (List<Orders>) request.getAttribute("orderList");
		if (list != null && !list.isEmpty()){
			Orders order = list.get(0);
			%>
			<div>
				<p> Order Id #<%= order.getOrderId() %></p>
				<p style="font-size: 12px"> Order Date: <%= order.getDateOrder() %></p>		
			<% 
			for (Orders orders: list){
	%>
		<div style="display: flex;flex-direction: row;justify-content: space-between;padding: 15px;height: 100px;border-top: 1.5px solid #999;margin: 10px 35px 20px 50px;border-bottom: 1.5px solid #999">
    		
			
			<div style="text-align:center;display: flex;flex-direction: column;justify-content: center;">
				<img src="data:image/jpeg;base64, <%= orders.getProductImage() %>" alt="Product-image" style = "height: 100px; width: 120px">
			</div>
			
			<div style="text-align:left;display: flex;flex-direction: column;justify-content: center; width: 136px">
				<p style="color: #333;font-weight: 550;"><%= orders.getKeyword() %></p>
				<h4 style="font-size: 16px;font-weight: 600;color: #111;padding-top:5px"><%= orders.getProductName() %><h4>
			</div>
			
			<div style="text-align:left;display: flex;flex-direction: column;justify-content: center; width: 136px">
			<h4 style="font-size: 16px;font-weight: 600;color: #111;padding-top:5px"><%= orders.getQuantity() %><h4>
			</div>
			
			<div style="text-align:center;display: flex;flex-direction: column;justify-content: center;">
				<h4 style="color:#111"><%= orders.getState() %></h4>
			</div>
			
			<div style="text-align:center;display: flex;flex-direction: column;justify-content: center; width: 100px">
				<a href='RemoveCart?cart_id=	<%= order.getOrderId() %>	' style="color: black; text-decoration: none">
				<i class="fa-solid fa-x" style="color: #333;"></i> Cancel 
				</a>
			</div>
		</div>
		</div>
		<% 		}
			}
		%>
	</div>
</main>
	
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>