<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Controller.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
<link rel="stylesheet" href="./CSS/styles.css">
</head>
<body>
<jsp:include page="./Header/userHeader.jsp"></jsp:include>
<main>
    <div class="left">
    	<div style="margin-top: 1rem; text-align: center;">
    		<% String username= (String)session.getAttribute("username"); %>
    		<p style="text-align: left; color: green; font-style:italic">Welcome, <%= username %>!</p>
    		<h2 style="font-weight: normal; font-family: Times New Roman; color: #333; font-size: 22px; font-weight: bold">Manage your Account</h2>
   	 	</div>
   	 	 
   	 	<div class="cate" style="margin:10px 0 0 5px; margin-bottom: 1px">
   	 		<ul>
   	 		<li style="padding: 0 0 0 15px; font-size: 15px"><a href="profie" style="color: #333">My Profile</a></li>
   	 		<li style="padding: 0 0 0 15px; font-size: 15px"><a href="viewOrder" style="color: #333">My orders</a></li>
   	 		</ul>
   	 	</div>
    </div>
    
    <div class="main">
    	
   			<form class= "profile_form" action="changeProfile" method="post">
   			<% User user = (User) request.getAttribute("userList"); %>
   				<h2>My profile</h2>
   				<input type="hidden" name="id" value ="<%= user.getId() %>">
   				<label>First Name: </label>
   				<input type="text" name="fname" value = "<%= user.getFname() %>" >
   				<label>Last Name: </label>
   				<input type="text" name="lname" value = "<%= user.getLname() %>">
   				<label>Email: </label>
   				<input type="text" name="email" value= "<%= user.getEmail() %>">
   				<button>Save</button>
   				
   			</form>
     </div> 
</main>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>