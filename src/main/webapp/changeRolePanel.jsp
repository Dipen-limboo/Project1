<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "Controller.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Changing Role Panel</title>
<link rel="stylesheet" href="./CSS/styles.css">

</head>
<body>
<jsp:include page="./Header/header.jsp"></jsp:include>
	<div class="orderPanel" style="min-height: 80vh">
	<h2 style="text-align: center; font-style: italic">Change Role</h2>
		<form class="userPanel" action="updateRole" method="Post">
		<% User user = (User)request.getAttribute("role");
			
		%>
			<input type="hidden" name="id" value="<%= user.getId() %>">
			
			<label style="margin-top:20px">First name: <%= user.getFname() %></label><br>
			<label>Last name: <%= user.getLname() %></label><br>
			<label>Email: <%= user.getEmail() %></label><br>
			
			<label>Role: </label>
			<p>(If you want to change the role of user to admin or vice versa)</p>
			<input type="text" name="role" value="<%= user.getRole() %>">
			<br>
			<button>Change</button>
		</form>
	</div>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>