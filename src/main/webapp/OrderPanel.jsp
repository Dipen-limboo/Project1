<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "Frontend.Orders" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Order</title>
<link rel="stylesheet" href="./CSS/styles.css">

</head>
<body>
<jsp:include page="./Header/header.jsp"></jsp:include>
<div class="orderPanel" style="min-height: 80vh">
		<table border= 1 style="margin-top: 20px; text-align:center; margin-left: 50px;">
			<tr>
				<th style="width: 100px">Order Id </th>
				<th style="width: 350px">Location</th>
				<th style="width: 150px">Phone</th>
				<th style="width: 150px"> Order Date </th>
			</tr>
			<% List <Orders> list = (List <Orders>) request.getAttribute("orderList");
				if (list != null && !list.isEmpty()){
					for (Orders order: list){
			%>
			<tr>
			
				<td><%= order.getOrderId() %></td>
				<td><%= order.getLocation() %></td>
				<td><%= order.getPhone() %></td>
				<td><%= order.getDateOrder() %></td>
			
			</tr>
			<%
					}
				}
			%>
		</table>
	</div>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>