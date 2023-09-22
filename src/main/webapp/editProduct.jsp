<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="product.Product" %>
<%@ page import="product.ProductVarient" %>
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
<section style="min-height: 80vh; width: 90%">
    <form action="UpdateServlet" method = "post">
    	<div class="form">
	<div class="left-side">
	<% Product pr = (Product) request.getAttribute("edit"); %>
	<input type="hidden" name="product_id" value="<%= pr.getProductID() %>" >
	<label>Product Title </label>
	<input type="text"  name="product_name" value="<%= pr.getProductName() %>"><br><br>
	
	<label>Price:</label>
    <input type="number" name="product_price" value="<%= pr.getProductPrice() %>"><br><br>
    
	<label >Keywords:</label>
	<input type="text" id="product_keywords" name="product_keyword" value="<%= pr.getProductKeyword() %>"> <br><br>
	
	<label >Description:</label> <br>
	<textarea  name="product_description" rows="4" ><%= pr.getProductDescription() %></textarea> <br><br>
	
	<label for="color">Color:</label> <br>
		
		<input type="checkbox" id="red" name="color" value="red"> Red <br>
		<input type="checkbox" id="blue" name="color" value="blue"> Blue <br>
		<input type="checkbox" id="green" name="color" value="green"> Green <br>
		<input type="checkbox" id="yellow" name="color" value="yellow"> Yellow <br>
		<input type="checkbox" id="black" name="color" value="black"> Black <br><br>
		


	
		<label for="size">Size:</label>    <br>        
	    <input type="checkbox" id="small" name="size" value="S"> Small <br>
	    <input type="checkbox" id="medium" name="size" value="M"> Medium <br>
	    <input type="checkbox" id="large" name="size" value="X"> Large <br>
	    <input type="checkbox" id="large" name="size" value="XL"> Extra Large <br>
	    <input type="checkbox" id="large" name="size" value="XXL"> Double Extra Large
		</div>
	<div class= "right-side">
	<label for="product_image">Product Image:</label><br>
    <input type="file" id="product_image" name="product_image" accept="image/*" value="<%= pr.getProductImage() %>"><br>
    <img id="image_preview" src="#" alt="Product Image Preview" style="height: 350px; width: 300px">         
	</div>
	</div>
	<button type="submit">Update</button>
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
                // Clear the preview if no file is selected
                imagePreview.src = "#";
            }
        });
    </script>
    </body>
</html>
