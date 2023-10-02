<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import= "Frontend.Cart" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" href="./CSS/style.css">
</head>
<body>
<jsp:include page="./Header/userHeader.jsp"></jsp:include>
	<section class="cart">
		<h2>My Cart</h2>
	<div class= "cart-section">
		<% 
		List <Cart> list = (List<Cart>) request.getAttribute("cartList"); 
			if (list != null){
				Double sumTotal =  (Double) request.getAttribute("sumtotal");
			%>
		<br><br><table border= 1 >
		<tr style="text-align: left">
		<th>&nbsp Images</th>
		<th>&nbsp Title</th>
		<th>&nbsp Price</th>
		<th>&nbsp Quantity</th>
		<th>&nbsp Total </th>
		<th>&nbsp Action </th>
		</tr>
		<tr>
		<% 
				for (Cart cart: list){
					
		%>
		<input type="hidden" name="cart_id" value="<%= cart.getCartId() %>">	
		
		<td style="height: 100px; width: 100px"><%= cart.getProductImage() %></td>
		
		<td style="width: 200px"><%= cart.getProduct_name() %> </td>
		
		<td style="width: 100px"><%= cart.getProduct_price() %> </td>
		
		<td style="width: 100px"><%= cart.getQuantity() %> </td>
	
		<td style="width: 100px"><%= cart.getTotalPrice() %> </td>		
			
		<td style="width: 100px"> <a href='RemoveCart?cart_id=	<%= cart.getCartId() %>	' style="color: black; text-decoration: none"> Remove </a></td>
		</tr>
		<% } %>
		<tr>
		<td style="text-align: left; padding-left: 2rem; font-weight: bolder;" colspan= "6">Total: <span>Rs: <%= sumTotal %> /-</span> </td>
		</tr>
		<% 	
					
			} else {%>
			<p style= "padding-top: 50px"> No Item Selected</p>
			<% }%>
		</table>
	</div>
	<div class="cart-foot"> 
	<a href="./products.jsp" style="color: white; text-decoration: none"> <button>CONTINUE SHOPPING </button></a>
	<button> CHECKOUT</button>
	</div>
	</section>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>