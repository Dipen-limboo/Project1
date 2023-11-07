<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="./CSS/style.css">

</head>
<body>

<jsp:include page="./Header/userHeader.jsp"></jsp:include>

    

<section class="login-form">

   <div class="login-Container" style="height: 500px">
    <div class="login-content">
        <form method='post' action="login" class="register_form"><br><br>
         <% 
    	String source = request.getParameter("source");
    	if (source != null && source.equals("cartServlet")) {
    	%>
        <p style="text-align: center; font-size: 20px; font-weight: bold">(You need to log in to access)</p>
    		<% } %>
            <h4>Log In</h4> <hr> <br>
            <div class="container" style="margin-left: 2rem">
                <label>Email:</label><br>
                <input type="text" placeholder="Enter Email" name="email" required><br>
            
                <label >Password:</label><br>
                <input type="password" placeholder="Enter Password" name="psw" required><br><br>
                <div class="psw">
                    <span>Forget <a href="#">password?</a></span><br><br>
                </div>
                <button type="submit">Login</button>
            </div><br><br>
            <p style="margin-left: 2rem">Don't have an account? <span><a href="./register.jsp">Register Now</a></span></p>
        </form>
    </div>
    <div class="content">
        <div class="content-header">
            <h4>
                Welcome back!
            </h4><br>
            <p>To keep connected with us please login with your valid information.</p><br>
            <button type="submit"><a href="./register.jsp">Register</a></button>
        </div>
    </div>
   </div>
</section>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>