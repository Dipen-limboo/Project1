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
<jsp:include page="./Header/userHeader.jsp"></jsp:include>
	<div class="orderPanel" style="min-height: 80vh; color: green; font-weight:normal; Text-align:center">
	<% List <Orders> list = (List<Orders>) request.getAttribute("orderList");
		if (list != null && !list.isEmpty()){
			for (Orders order: list){
	%>
		<div style="margin-top: 15px; background-color:f5f5f5; margin-bottom: 3px">
		<p> Order Id #<%= order.getOrderId() %></p>
		<p> Order Date: <%= order.getDateOrder() %></p>
		</div>
		<div class="order_div" style="border: 1px solid green;background-color:f5f5f5; text-align: center; width: 85%; margin: auto" >
		<table style="padding: 20px">
		<tr>
		<td><img alt="" src="data:image/jpeg;base64, <%= order.getProductImage() %> " style="width:100px; height:100px"></td>
		<td style="width:400px;padding-left: 5rem"><%= order.getProductName() %></td>
		<td style="width:300px;padding-left: 5rem"><span>Qty:</span> <%= order.getQuantity() %></td>
		</tr>
		</table>
		</div>
		<% 		}
			}
		%>
	</div>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>