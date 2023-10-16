<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Controller.User" %>
<%@ page import="java.util.List" %>
<%@ page import="Frontend.Orders" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review</title>
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
    		<h2 style="text-align: left; margin-bottom: 0.5rem">My Reviews</h2>
    	</div>
    	<% 
    		List<Orders> list = (List<Orders>) request.getAttribute("orderList"); 
    		if (list != null && !list.isEmpty()){
    			for (Orders order: list){
    	%>
    	<div>
    		<h4 style="font-weight: normal; padding: 10px 0 0 18px">YakthungIpa</h4>
    		<p style="font-size: 12px">Purchased on <%= order.getDateOrder() %></p>
    		<div style="display: flex; padding: 20px" class="table">
    			<table>
    				<tr>
    					<td><img src="data:image/jpeg;base64, <%= order.getProductImage() %>" style="height:100px; width: 100px"></td>
    					<td><%= order.getProductName() %></td>
    					<td style="padding-left:20px"><button style="text-align: center">Review</button></td>
    				</tr>
    			</table>
    		</div>
    	</div>
    	<%
    			}
    		}
    	%>
     </div> 
</main>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>