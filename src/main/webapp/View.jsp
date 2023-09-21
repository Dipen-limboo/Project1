<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="product.Product"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Product</title>
    <link rel="stylesheet" href="./CSS/styles.css">
</head>
<body style="min-height: 100vh">
<jsp:include page="./Header/header.jsp"></jsp:include>
<section style="height: 80vh; width: 90%">
    <table border="1" style="width: 100%">
        <tr>
            <th>Name</th>
            <th>Image</th>
            <th>Price</th>
            <th>Keywords</th>
            <th>Description</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <% 
            ArrayList<Product> pr = (ArrayList<Product>) request.getAttribute("data");
            if (pr != null) {
                for (Product p : pr) {
        %>
        <tr>
            <td><%= p.getProductName() %></td>
            <td><img src="<%= p.getProductImage() %>" alt="Product-image" width="100%"></td>
            <td><%= p.getProductPrice() %></td>
            <td><%= p.getProductKeyword() %></td>
            <td><%= p.getProductDescription() %></td>
            <td><a href='EditServlet?product_id=<%= p.getProductID() %>' style="color: black; text-decoration: none">Edit</a></td>
            <td><a href='DeleteServlet?product_id=<%= p.getProductID() %>' style="color: black; text-decoration: none">Delete</a></td>
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
