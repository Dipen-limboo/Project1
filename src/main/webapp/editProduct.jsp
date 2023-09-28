<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="product.Product" %>
<%@ page import ="java.util.List" %>
<%@ page import="product.ProductVarient" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <link rel="stylesheet" href="./CSS/styles.css">
</head>
<body style="min-height: 100vh">
<jsp:include page="./Header/header.jsp"></jsp:include>
<section style="min-height: 80vh; width: 90%">
    <form action="UpdateServlet" method="post">
        <div class="form">
            <div class="left-side">
                <%
                    Product product = (Product) request.getAttribute("product");
                    if (product != null) {
                %>
                <input type="hidden" name="product_id" value="<%= product.getProductID() %>">
                <label>Product Title </label>
                <input type="text" name="product_name" value="<%= product.getProductName() %>"><br><br>

                <label>Price:</label>
                <input type="number" name="product_price" value="<%= product.getProductPrice() %>"><br><br>

                <label>Keywords:</label>
                <input type="text" id="product_keywords" name="product_keyword"
                       value="<%= product.getProductKeyword() %>"> <br><br>

                <label>Description:</label> <br>
                <textarea name="product_description" rows="4"><%= product.getProductDescription() %></textarea>
                <br><br>
                <label for="color">Color:</label>  <br>        
				<input type="text" name="color" value= "<%= product.getColor() %>">				
				
				<label for="size">Size:</label>    <br>        
				<input type="text" name="size" value=" <%= product.getSize() %>">
            	</div>
           		 <div class="right-side">
                	<label for="product_image">Product Image:</label><br>
                	<input type="file" id="product_image" name="product_image" accept="image/*"
                       value="<%= product.getProductImage() %>"><br>
                	<img id="image_preview" src="<%= product.getProductImage() %>"
                    	 alt="Product Image Preview" style="height: 350px; width: 300px">
            	</div>
        	</div>
        	<button type="submit">Update</button>
        		<%
            		}
        		%>
    </form>
</section>
<footer>
    <p>&copy; 2023 eCommerce Admin Panel</p>
</footer>
<script>
    const imageInput = document.getElementById("product_image");
    const imagePreview = document.getElementById("image_preview");

    imageInput.addEventListener("change", function () {
        const file = this.files[0];

        if (file) {
            const reader = new FileReader();

            reader.onload = function (e) {
                imagePreview.src = e.target.result;
            };

            reader.readAsDataURL(file);
        } else {
            imagePreview.src = "#";
        }
    });
