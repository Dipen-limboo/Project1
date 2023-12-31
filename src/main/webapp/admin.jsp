<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <% 	request.getRequestDispatcher("/admin").include(request, response); %>
 --%>
 <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin</title>
    <link rel="stylesheet" href="./CSS/styles.css">
</head>
<body>
<jsp:include page="./Header/header.jsp"></jsp:include>
    <main class="admin-main" style="gap:5rem">
        <section class="product-management" id="Dashboard">
            <h2>Product Management</h2>
            <ul>
                <li><a href="./addProduct.jsp" style="color: #333; text-decoration: none">Add Product</a></li>
                <li><a href="ViewServlet" style="color: #333; text-decoration: none">Edit Product</a></li>
                <li><a href="ViewServlet" style="color: #333; text-decoration: none">Delete Product</a></li>
            </ul>
        </section>
        <section class="order-management">
            <h2>Order Management</h2>
            <ul>
                <li><a href="#" style="color: #333; text-decoration: none">View Orders</a></li>
                <li><a href="#"style="color: #333; text-decoration: none">Process Orders</a></li>
            </ul>
        </section>
        <section class="user-management">
            <h2>User Management</h2>
            <ul>
                <li><a href="userServlet" style="color: #333; text-decoration: none">View Users</a></li>
                <li><a href="userServlet" style="color: #333; text-decoration: none">Edit Users</a></li>
                <li><a href="userServlet" style="color: #333; text-decoration: none">Delete Users</a></li>
            </ul>
        </section>
        <section class="stock-management">
            <h2>Transaction Management</h2>
            <ul>
                <li><a href="transaction" style="color: #333; text-decoration: none">All transaction</a></li>
                <li><a href="" style="color: #333; text-decoration: none">In-Sourcing transaction</a></li>
                <li><a href="" style="color: #333; text-decoration: none">Out-Sourcing transaction</a></li>
            </ul>
        </section>
    </main>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>

    