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
<header class="header">
 <h1>eCommerce Store</h1>
        <nav>
            <ul>
                <li><a href="./products.jsp">Home</a></li>
                <li><a href="./products.jsp">Products</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="#">Cart</a></li>
                <li><a href="#">__<a>
            </ul>
        </nav>
</header>
<section class="login-form">

   <div class="login-Container">
    <div class="login-content">
        <form method='post' action="login" class="register_form"><br><br>
            <h4>Log In</h4> <hr> <br>
            <div class="container">
                <label>Email:</label><br>
                <input type="text" placeholder="Enter Email" name="email" required><br>
            
                <label >Password:</label><br>
                <input type="password" placeholder="Enter Password" name="psw" required><br><br>
                <div class="psw">
                    <span>Forget <a href="#">password?</a></span><br><br>
                </div>
                <button type="submit">Login</button>
            </div><br><br>
            <p>Don't have an account? <span><a href="./register.jsp">Register Now</a></span></p>
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
<footer>
        <p>&copy; 2023 eCommerce Store</p>
    </footer>
</body>
</html>