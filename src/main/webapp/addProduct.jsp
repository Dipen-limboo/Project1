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

<section class= container>
	<form action = "add" method="post" enctype="multipart/form-data"> 
	<h2>Add Products</h2> <br>
	<div class="form">
	<div class="left-side">
	<label>Product Title </label>
	<input type="text"  name="product_name" required><br><br>
	
	<label>Price:</label>
    <input type="number" name="product_price" required><br><br>
    
	<label >Keywords:</label>
	<input type="text" id="product_keywords" name="product_keyword"> <br><br>
	
	<label >Description:</label> <br>
	<textarea  name="product_description" rows="4"></textarea> <br><br>
	
	<label for="color">Color:</label>  <br>        
  	<input type="text" name="color">
	
	
	<label for="size">Size:</label>    <br>        
    <input type="text" name="size">
	</div>
	<div class= "right-side">
	<label for="product_image">Product Image:</label><br>
    <input type="file" id="product_image" name="product_image" accept="image/*" required><br>
    <img id="image_preview" src="#" alt="Product Image Preview" style="height: 350px; width: 300px">         
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