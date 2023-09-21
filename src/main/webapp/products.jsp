<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>eCommerce Frontend</title>
    <link rel="stylesheet" href="./CSS/styles.css">
</head>
<body>
    <header>
        <h1>eCommerce Store</h1>
        <nav>
            <ul>
            
                <li><a href="./products.jsp">Home</a></li>
                <li><a href="./products.jsp">Products</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="#">Cart</a></li>
                <li><a href="./login.jsp">__</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <section class="product">
            <img src="product1.jpg" alt="Product 1">
            <h2>Product 1</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
            <span class="price">$19.99</span>
            <button>Add to Cart</button>
        </section>
        <section class="product">
            <img src="product2.jpg" alt="Product 2">
            <h2>Product 2</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
            <span class="price">$24.99</span>
            <button>Add to Cart</button>
        </section>
        <!-- Add more product sections here -->
    </main>
    <footer>
        <p>&copy; 2023 eCommerce Store</p>
    </footer>
</body>
</html>
    