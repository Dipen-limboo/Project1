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
<div class="transactionPanel">
		<div class ="dashboard">
			<section class="product-management" id="Dashboard">
	            <h2>Product Management</h2>
	            <ul>
	                <li><a href="./addProduct.jsp" style="color: white; text-decoration: none">Add Product</a></li>
	                <li><a href="ViewServlet" style="color: white; text-decoration: none">Edit Product</a></li>
	                <li><a href="ViewServlet" style="color: white; text-decoration: none">Delete Product</a></li>
	            </ul>
	        </section>
	        <section class="order-management">
	            <h2>Order Management</h2>
	            <ul>
	                <li><a href="#" style="color: white; text-decoration: none">View Orders</a></li>
	                <li><a href="#"style="color: white; text-decoration: none">Process Orders</a></li>
	            </ul>
	        </section>
	        <section class="user-management">
	            <h2>User Management</h2>
	            <ul>
	                <li><a href="userServlet" style="color: white; text-decoration: none">View Users</a></li>
	                <li><a href="userServlet" style="color: white; text-decoration: none">Edit Users</a></li>
	                <li><a href="userServlet" style="color: white; text-decoration: none">Delete Users</a></li>
	            </ul>
	        </section>
	        <section class="stock-management">
	            <h2>Transaction Management</h2>
	             <ul>
	                <li><a href="transaction" style="color: white; text-decoration: none">All transaction</a></li>
	                <li><a href="insourcing" style="color: white; text-decoration: none">In-Sourcing transaction</a></li>
	                <li><a href="outsourcing" style="color: white; text-decoration: none">Out-Sourcing transaction</a></li>
	            </ul>
	        </section>
	        </div>
		 <div class="dashboard-content"> 
	      <h2>All Products</h2>
	<form action = "add" method="post" enctype="multipart/form-data" style="width: 100%"> 
	
	<div class="form">
	<div class="left-side">
	<label>Product Title </label>
	<input type="text"  name="product_name" required><br><br>
	
	<label>Price:</label>
    <input type="number" name="product_price" required><br><br>
    
    <label>Quantity:</label>
    <input type="number" name="product_quantity" required><br><br>
	
	<label >Description:</label> 
	<textarea  name="product_description" rows="4" required></textarea> <br><br>
	
	<label for="color">Color:</label>   
  	<div style="display: flex; justify-content: space-between">
  	<div>
  	<input type="checkbox" name="color" value="red"> Red <br>
	<input type="checkbox" name="color" value="blue"> Blue <br>
	<input type="checkbox" name="color" value="black"> Black <br>
	</div>
	<div>
	<input type="checkbox" name="color" value="white"> White <br>
	<input type="checkbox" name="color" value="green"> Green <br>
	<input type="checkbox" name="color" value="yellow"> Yellow <br>
	</div>
	<div>
	<input type="checkbox" name="color" value="grey"> Grey <br>
	<input type="checkbox" name="color" value="pink"> Pink <br>
	<input type="checkbox" name="color" value="orange"> Orange <br>
	</div>
	</div>
	
	<br><label for="size">Size:</label>          
    <div style="display: flex; justify-content: space-between">
  	<div>
  	<input type="checkbox" name="size" value="small"> S <br>
	<input type="checkbox" name="size" value="large"> L <br>
	<input type="checkbox" name="size" value="double extra large"> XXL <br>
	</div>
	<div>
	<input type="checkbox" name="size" value="Medium"> M <br>
	<input type="checkbox" name="size" value="extra large"> XL <br>
	<input type="checkbox" name="size" value="triple extra large"> XXXL <br>
	
	</div>
	<div>
	<input type="checkbox" name="size" value="35"> 35 <br>
	<input type="checkbox" name="size" value="36"> 36 <br>
	<input type="checkbox" name="size" value="37"> 37 <br>
	</div>
	<div>
	<input type="checkbox" name="size" value="38"> 38 <br>
	<input type="checkbox" name="size" value="39"> 39 <br>
	<input type="checkbox" name="size" value="40"> 40 <br>
	</div>
	<div>
	<input type="checkbox" name="size" value="41"> 41 <br>
	<input type="checkbox" name="size" value="42"> 42 <br>
	</div>
	</div> 
	</div>
	<div class= "right-side">
	
	<label >Keywords:</label>
	<select name="product_keyword" style= "width: 500px "required>
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
	<button type="submit" style="margin-left: 10px;">Add Product</button>
	</form>
	

</div>
</div>
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