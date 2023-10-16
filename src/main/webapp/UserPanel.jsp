<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "Controller.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Panel</title>
<link rel="stylesheet" href="./CSS/styles.css">

</head>
<body>
<jsp:include page="./Header/header.jsp"></jsp:include>
	<div class="orderPanel">
		<table border= 1 style="margin-top: 20px; text-align:center; margin-left: 50px;">
			<tr>
				<th style="width: 100px; padding: 10px">User Id </th>
				<th style="width: 100px; padding: 10px">First Name </th>
				<th style="width: 350px; padding: 10px">Last Name</th>
				<th style="width: 150px; padding: 10px">Email</th>
				<th style="width: 100px; padding: 10px">Password</th>
				<th style="width: 100px; padding: 10px">Role</th>
				<th colspan="2">Action</th>
			</tr>
			<% List <User> list = (List <User>) request.getAttribute("userList");
				if (list != null && !list.isEmpty()){
					for (User usr: list){
			%>
			<tr>
			
				<td><%= usr.getId() %></td>
				<td><%= usr.getFname() %></td>
				<td><%= usr.getLname() %></td>
				<td><%= usr.getEmail() %></td>
				<td><%= usr.getPassword() %></td>
				<td><%= usr.getRole() %></td>
				<td><a href="editUserPanel?id=<%= usr.getId() %>"style="color: black; text-decoration: none">Change</a></td>
				<td><a href="deleteUserPanel?id=<%= usr.getId() %>" style="color: black; text-decoration: none">delete</a></td>
			
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