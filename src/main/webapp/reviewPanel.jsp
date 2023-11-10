<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Controller.User" %>
<%@ page import="java.util.List" %>
<%@ page import="Order_package.Orders" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review Panel</title>
<link rel="stylesheet" href="./CSS/styles.css">
<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
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
    		<form method="post" action="userReview" id="reviewForm">
    		<div style="display: flex; padding: 20px; width: 700px" class="table">
    			<table>
    				<tr>
    				<th>Product Image</th>
    				<th>Product Name</th>
    				<th style="">Review</th>
    				</tr>
    				<tr>
    					
    					<td style="text-align:center"><img src="data:image/jpeg;base64, <%= order.getProductImage() %>" style="height:100px; width: 100px"></td>
    					<td style="text-align:center"><%= order.getProductName() %></td>
						<td class="icon">
						
						<% 
			                String value = "<i class='far fa-star'></i>";
			                for (int i = 0; i < 5; i++) {
			                %>
			                <span class="star" data-value="<%= i + 1 %>"><%= value %></span>
			                <%
			                } 
			                %>
						</td>  					
    					<!-- <td style="padding-left:20px"><a href="reviewPanel.jsp"><button style="text-align: center">Review</button></a></td> -->
    				</tr>
    			</table>
    		</div> 
    		<input type= "hidden" name = "orderDetails_id" value="<%= order.getOrderDetails_id() %>">
    		<input type= "hidden" name = "product_id" value="<%= order.getProductId() %>">
    		<input type ="hidden" name="user_id" value="<%= order.getUserId() %>">
    		 <input type="hidden" name="rating" id="rating">
    		<br>
    		<p style="font-weight: bolder">Add comment </p>
    		<textarea rows="7" cols="8" style="width: 500px" placeholder="Write your comment" name ="comment"></textarea><br>
    		<button type="button" onclick="submitForm()">Send</button>
    		</form>
    	</div>
    	<%
    			}
    		}
    	%>
     </div> 
</main>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
<script>
    function submitForm() {
        var rating = document.querySelector('input[name="rating"]').value;
        if (rating !== "") {
            document.forms["reviewForm"].submit();
        } else {
            alert("Please select a rating before submitting.");
        }
    }

    var stars = document.querySelectorAll('.star');

    stars.forEach(function(star) {
        star.addEventListener('click', function() {
            var value = star.getAttribute('data-value');
            document.querySelector('input[name="rating"]').value = value;

            // Update star colors based on the user's selection
            stars.forEach(function(s) {
                if (s.getAttribute('data-value') <= value) {
                    s.innerHTML = '<i class="fas fa-star"></i>';
                } else {
                    s.innerHTML = '<i class="far fa-star"></i>';
                }
            });
        });
    });
</script>

</body>
</html>