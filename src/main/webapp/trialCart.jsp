<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import= "Frontend.Cart" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<!-- <link rel="stylesheet" href="./CSS/styles.css"> -->
<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<jsp:include page="./Header/userHeader.jsp"></jsp:include>
	<section style="padding: 5px 0 10px 0; background-color:#ddd">
	<div style="min-height: 500px;margin: auto;width: 1000px;background-color: #fff;margin-top: 20px;margin-bottom: 20px;border: 1px solid #ddd;border-radius: 20px;box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);display:flex;flex-direction: row">
		<div style="width:660px;">
			<div style="display: flex;flex-direction: row;justify-content: space-between;padding:20px 0 0 50px">
				<h2 style="color: #111; font-size: 24px; letter-spacing: 1.5px;">Shopping Cart</h2>
					<% 
						List <Cart> list = (List<Cart>) request.getAttribute("cartList"); 
							if (list != null){
								Double sumTotal =  (Double) request.getAttribute("sumtotal");
					%>
				<p style="padding:10px 50px 0 0;"> items</p>
			</div>
				<% 
						for (Cart cart: list){
							
				%>
		<div style="display: flex;flex-direction: row;justify-content: space-between;padding: 20px 0;height: 100px;border-top: 1.5px solid #999;margin: 50px 35px 0 50px;border-bottom: 1.5px solid #999">
    		
    		<input type="hidden" name ="user_id" value = "<%= cart.getUser_id() %>">
    		<input type="hidden" name ="product_id" value = "<%= cart.getProduct_id() %>">
			
			<div style="text-align:center;display: flex;flex-direction: column;justify-content: center;">
				<img src="data:image/jpeg;base64, <%= cart.getProductImage() %>" alt="Product-image" style = "height: 100px; width: 120px">
			</div>
			
			<div style="text-align:left;display: flex;flex-direction: column;justify-content: center; width: 136px">
				<p style="color: #333;font-weight: 550;"><%= cart.getProductKeyword() %></p>
				<h4 style="font-size: 16px;font-weight: 600;color: #111;padding-top:5px"><%= cart.getProduct_name() %><h4>
			</div>
			
			<div style="padding-top: 39px;display:flex; flex-direction:row"> 
				<a href="diminishQuantity?product_id=<%=cart.getProduct_id() %>&&quantity=<%= cart.getQuantity() %>&&userid=<%= cart.getUser_id() %> " style="text-decoration:none;font-size: 18px;">-  </a>
				<form action="cartQuantity" method="get">
				<input type="hidden" name ="user_id" value="<%= cart.getUser_id() %>">
    			<input type="hidden" name ="product_id" value = "<%= cart.getProduct_id() %>">
				  <input type="number" name="quantity" value="<%= cart.getQuantity() %>" style="width:42px; text-align:center; height:15px; border: 1.5px solid black; border-radius: 5px;margin:0 5px;" onchange="this.form.submit()">
				</form>
				<a href="increasingQuantity?product_id=<%=cart.getProduct_id() %>&&quantity=<%= cart.getQuantity() %>&&userid=<%= cart.getUser_id() %> "style="text-decoration:none;font-size: 18px;"> +</a>
			</div>
			
			<div style="text-align:center;display: flex;flex-direction: column;justify-content: center;">
				<h4 style="color:#111"><%= cart.getTotalPrice() %></h4>
			</div>
			
			<div style="text-align:center;display: flex;flex-direction: column;justify-content: center; width: 100px">
				<a href='RemoveCart?cart_id=	<%= cart.getCartId() %>	' style="color: black; text-decoration: none">
				<i class="fa-solid fa-x" style="color: #333;"></i>
				</a>
			</div>
		</div>
		<% } 
			} else {%>
				<p style= "padding-top: 50px"> No Item Selected</p>
				<% }%>
		<div style="display: flex;flex-direction: row;padding: 17px 47px;">
			<a href="./products.jsp" style="text-decoration: none; color:#333">
			<p> <i class="fa-solid fa-arrow-left"></i>  Back To Home</p>
			</a>
		</div>
		</div>
		
		<div style="width: 340px;background-color: #ddd;border-radius: 0 20px 20px 0;color: #333;">
			<h2 style="color: #333;font-size: 22px;padding: 44px 0 0 50px;letter-spacing: 1.5px;">Summary</h2>
		
			<div style="margin: 27px 17px 0 17px;padding: 20px 10px;border-top: 1.5px solid #999;border-bottom: 1.5px solid #999">
				<div style="display: flex;flex-direction: row;justify-content: space-between;font-weight: 400;font-size: 15px;padding: 0 0 10px 0;">
					<h4 style="font-weight:550;">ITEMS</h4>
					<% Double sumTotal =  (Double) request.getAttribute("sumtotal"); %>
					<p style="font-weight:550;">Rs <%= sumTotal %> /-</p>
				</div>
				
				<div style=" padding: 8px 0;">
					<h3 style="font-weight: 550;font-size: 16px;padding: 0 0 10px 0;">SHIPPING</h3>
					<form action="cartShipping" method="get">
						<input type="hidden" name="total" value="<%= sumTotal %>">
					    <select name="shipping" style="height: 30px;width: 286px;text-align: center;font-size: 15px;color: #333;font-family: cursive;" onchange="this.form.submit()">
					        <option> Choose Shipping </option>
					        <option value="65">Standard-Delivery-Rs65</option>
					        <option value="75">Quick_Delivery_Rs75</option>
					    </select>
					</form>
				</div>
				
				<div style="padding: 10px 0 19px 0;">
					<h3 style="font-weight: 550;font-size: 16px;padding: 10px 0 10px 0;">GIVE CODE</h3>
					<input type="text" name="code" placeholder="Enter your code" style="height: 29px;width: 276px;font-size: 15px;padding-left: 10px;color: #333;font-family: cursive;">
				</div>
			</div>
			<div style="display: flex;flex-direction: row;justify-content: space-between;font-weight: 400;font-size: 15px;padding:  10px 20px 10px 20px;">
				<h4 style="font-weight:550;">Total Price</h4>
				<%  double netTotal = (Double)request.getAttribute("total"); %>
				<p style="font-weight:550;">Rs <%= netTotal %> /-</p>
			</div>
			<div >
			<a href="check?user_id = <%= (Integer)session.getAttribute("userId") %>"><button style="width:300px; margin: 30px 8px 0 21px">Checkout</button></a></div>
		</div>
	</div>
	
	
	</section>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>