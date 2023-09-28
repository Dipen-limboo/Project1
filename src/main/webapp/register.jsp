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
   <div class="login-Container">
        <div class="content">
        <div class="content-header">
            <h4>
                Welcome back!
            </h4><br>
            <p>To keep connected with us please login with your personal information.</p><br>
            <button type="submit"><a href="./login.jsp">Login</a></button>
        </div>
    </div>
    <div class="register-content">
        <form  class="form" action="register" method = "post" >
            <h4>Register</h4> <hr> 
            <div class="container">
                <label >First Name</label><br>
                <input type="text" placeholder="First name" name="fname" required><br>
                
                <label >Last Name</label><br>
                <input type="text" placeholder="Last name" name="lname" required><br>
                
                <label >Email</label><br>
                <input type="text" placeholder="Enter your email" name="email" required><br>
            
                <label >Password</label><br>
                <input type="password" placeholder="Enter your password" name="psw" required><br>
              
                <label >Confirm Password</label><br>
                <input type="password" placeholder="Confirm password" name="cpsw" required><br>
                
            </div>
            <button type="submit">Register</button>
        </form>
    </div>
   </div>
</section>
<jsp:include page="./Footer/footer.jsp"></jsp:include>


</body>
</html>