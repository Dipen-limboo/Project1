<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="product.Product" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Product</title>
    <link rel="stylesheet" href="./CSS/styles.css">
</head>
<body style="min-height: 100vh">
<jsp:include page="./Header/header.jsp"></jsp:include>
<section style=" width: 90%">
    <table border="1" style="width: 100%; margin-left: 50px; margin-top:20px; text-align:center; margin-bottom: 20px; background-color: white;">
        <tr>
            <th>Name</th>
            <th>Image</th>
            <th style="width: 70px">Price</th>
            <th>Quantity</th>
            <th>Keywords</th>
            <th>Description</th>
            <th>Colors</th>
            <th>Sizes</th>
            <th style="width: 70px">Edit</th>
            <th style="width: 70px">Delete</th>
        </tr>
        <%
            ArrayList<Product> productList = (ArrayList<Product>) request.getAttribute("productList");
    

            if (productList != null) {
                for (Product product : productList) {
        %>
        <tr>
            <td><%= product.getProductName() %></td>
            <td><img src="data:image/jpeg;base64, <%= product.getProductImage() %>" alt="Product-image" style = "height: 100px; width: 250px"></td>
            <td><%= product.getProductPrice() %></td>
            <td><%= product.getProductQuantity() %> </td>
            <td><%= product.getProductKeyword() %></td>
            <td><%= product.getProductDescription() %></td>
            <td><%= product.getColor() %></td>
            <td><%= product.getSize() %></td>
            <td><a href='EditServlet?product_id=<%= product.getProductID() %>' style="color: black; text-decoration: none">Edit</a></td>
            <td><a href='DeleteServlet?product_id=<%= product.getProductID() %>' style="color: black; text-decoration: none">Delete</a></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</section>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>
