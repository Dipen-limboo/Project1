<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Adding Product</title>
<link rel="stylesheet" href="./CSS/styles.css">
</head>
<body>
<jsp:include page="./Header/header.jsp"></jsp:include>

<section class= "container" >
	<form action = "add" method="post" enctype="multipart/form-data" style="width: 100%"> 
	<h2>Add Products</h2> <br>
	<div class="form">
	<div class="left-side">
	<label>Product Title </label>
	<input type="text"  name="product_name" required><br><br>
	
	<label>Price:</label>
    <input type="number" name="product_price" required><br><br>
    
    <label>Quantity:</label>
    <input type="number" name="product_quantity" required><br><br>
	
	<label >Description:</label> <br>
	<textarea  name="product_description" rows="4"></textarea> <br><br>
	
	<label for="color">Color:</label>  <br>        
  	<input type="text" name="color">
	
	
	<label for="size">Size:</label>    <br>        
    <input type="text" name="size">
	</div>
	<div class= "right-side">
	
	<label >Keywords:</label>
	<select name="product_keyword" style= "width: 500px ">
	<option> --- Select Categories ---</option>
	<option value = "shoes">Shoes</option>
	<option value = "T-shirt">T-shirt</option>
	<option value = "Track Suit">Track Suit</option>
	<option value = "Jaama">Jaama</option>
	<option value = "Medy">Medy </option>
	<option value = "Tops">Tops</option>
	</select> <br><br>
	
	<label for="product_image">Product Image:</label>
    <input type="file" id="product_image" name="product_image" accept="image/*" style= "width: 470px " required><br>
    <img id="image_preview" src="#" alt="Product Image Preview" style="height: 350px; width: 300px; display: none">         
	</div>
	</div>
	<button type="submit">Add Product</button>
	</form>
</section>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
<script>
        const imageInput = document.getElementById("product_image");
        const imagePreview = document.getElementById("image_preview");
    
        imageInput.addEventListener("change", function () {
            const file = this.files[0];

            if (file) {
                const reader = new FileReader();

                reader.onload = function (e) {
                    imagePreview.src = e.target.result;
                    imagePreview.style.display = "block"; // Display the image
                };

                reader.readAsDataURL(file);
            } else {
                // Clear the preview if no file is selected
                imagePreview.src = "#";
                imagePreview.style.display = "none"; // Hide the image
            }
        });
    </script>
</body>
</html>