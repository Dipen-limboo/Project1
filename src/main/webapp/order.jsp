<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Frontend.Cart" %>
<%@ page import ="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./CSS/styles.css">

</head>
<body>
    <jsp:include page="./Header/userHeader.jsp"></jsp:include>
	<div class = order>
		<form class="order_form" method="post" action="order">
			<% List <Cart> list = (List <Cart>) request.getAttribute("list");
				
				if (list != null && !list.isEmpty()){
				Cart cart = list.get(0);
				
			%>
			<h2 style="margin-top:20px; text-align: center; ">Order </h2> <hr>
			<input type="hidden" name="user_id" value = "<%= cart.getUser_id() %>">
			<label style="padding-top: 20px; font-weight: normal"> Deliver to: <span><%= cart.getFirstname() %> <%= cart.getLastname() %></span></label>
			
			<label style="font-weight: normal"> Email to: <span style="text-decoration: underline"><%=  cart.getEmail() %></span></label>
			
			<label style="font-weight: normal">Phone: </label>
			<input type ="text" name= "phone" style="width: 350px" required>
			<br><label style="font-weight: normal">Location: </label>
			<input type = "text" name="Location" style="width: 350px" required>
			<% } %>
			<br><label style="font-weight: normal">Net Price: <span><%= (Double) request.getAttribute("total") %> /-</span></label>
			<label style="font-weight: normal">Vat(13%): <span><%= (Double)request.getAttribute("vat") %> /-</span> </label>
			<hr style="margin-left: 25px; width: 140px; border-radius: 0px;"> 
			<label style="font-weight: normal"> Total Price: <span><%= request.getAttribute("net") %> /-</span></label>
			<button style="margin-left: 70px">Place order </button>
		</form>
	</div>
	<jsp:include page="./Footer/footer.jsp"></jsp:include>
	
</body>
</html>