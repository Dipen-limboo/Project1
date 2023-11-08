<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="./CSS/styles.css"> -->
<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<jsp:include page="./Header/userHeader.jsp"></jsp:include>
	<section style="padding: 5px 0 10px 0; background-color:#ddd">
	<div style="height: 500px;
    margin: auto;
    width: 1100px;
    background-color: #fff;
    margin-top: 20px;
    border: 1px solid #ddd;
    border-radius: 20px;
    box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
    display:flex;
    flex-direction: row">
		<div style="
		width:750px;
		
		">
		<div style="
    	display: flex;
    	flex-direction: row;
	    justify-content: space-between;
	    padding:20px 0 0 50px
		">
		<h2 style="color: #333; font-size: 24px">Shopping Cart</h2>
		<p style="padding:10px 50px 0 0;"> items</p>
		</div>
		<div style="    
			display: flex;
    		flex-direction: row;
    		justify-content: space-evenly;
    		padding: 20px 0;
		    height: 100px;
    		">
			<div style="text-align:center;display: flex;flex-direction: column;justify-content: center;"><h4>Image</h4></div>
			<div style="text-align:center;display: flex;flex-direction: column;justify-content: center;"><p>Shirt</p>
			<h4>Product Name<h4></div>
			
			<div style="padding-top: 39px;">
			<a href="" style="text-decoration:none;font-size: 18px;">-  </a>
			<input type="number" name= "quantity" value="1" style="width:42px; text-align:center; height:15pxborder: 1.5px solid black;border-radius: 5px;">
			<a href=""style="text-decoration:none;font-size: 18px;"> +</a></div>
			<div style="text-align:center;display: flex;flex-direction: column;justify-content: center;">Price</div>
			<div style="text-align:center;display: flex;flex-direction: column;justify-content: center;"><i class="fa-solid fa-x" style="color: #333;"></i></div>
		</div>
		</div>
		
		<div>
		<h2>Summary</h2>
		</div>
	</div>
	
	</section>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>