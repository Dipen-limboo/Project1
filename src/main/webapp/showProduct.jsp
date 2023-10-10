<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="product.Product" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Details</title>
    <link rel="stylesheet" href="./CSS/styles.css">
</head>
<body>
    <jsp:include page="./Header/userHeader.jsp"></jsp:include>
    <form class="frm" action="cartServlet" method="post">
        <div class="product" style="width: 400px; height: 480px; text-align: center; margin: 1rem; padding: 2rem">
            <%
                List<Product> productList = (List<Product>) request.getAttribute("productList");
            
                if (productList != null && !productList.isEmpty()) {
                    Product product = productList.get(0); 
                    
            %>
            <input type="hidden" name="productID" value="<%= product.getProductID() %>">
            <% session.setAttribute("page", product.getProductID()); %>
            
   			<input type="hidden" name="userID" value="<%= session.getAttribute("userId") %>">
            <img src="data:image/jpeg;base64, <%= product.getProductImage() %>" alt="Product Image" style="height:230px; width:230px">
            <h1 style="text-decoration: underline; font-size: 24px; padding:5px"><%= product.getProductName() %></h1>
            <p style=" padding:5px" ><%= product.getProductDescription() %></p>
            <p style=" padding:5px" >Color:<span style="font-style:italic"> <%= product.getColor() %></span></p>
            <p style=" padding:5px" >Size: <span style="font-style:italic"><%= product.getSize() %></span></p>
            <p style="font-weight: bolder; padding: 5px">Price: Rs <%= product.getProductPrice() %> /-</p>
            
            <p style=" padding:5px"> Quantity: <input type="number" name ="quantity" style="width: 80px; padding-left: 5px;" value="1"  ></p>
            <button id="addToCartBtn">Add to Cart</button>
            <%
                } else {
            %>
            <p>No product found</p>
            <%
                }
            %>
        </div>
    </form>
    <jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>
