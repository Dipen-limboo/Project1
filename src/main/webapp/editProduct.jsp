<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="product.Product" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <link rel="stylesheet" href="./CSS/styles.css">
</head>
<body style="min-height: 100vh">
<jsp:include page="./Header/header.jsp"></jsp:include>
<section class="container" style="width: 90%">
    <form action="UpdateServlet" method="post" enctype="multipart/form-data" style="width:100%">
       <%
            Product product = (Product) request.getAttribute("product");
            if (product != null) {
        %>
        <h2>Edit Product</h2>
        <div class="form">
            <div class="left-side">
               
                <input type="hidden" name="product_id" value="<%= product.getProductID() %>">
                <label>Product Title</label>
                <input type="text" name="product_name" value="<%= product.getProductName() %>"><br><br>

                <label>Price:</label>
                <input type="number" name="product_price" value="<%= product.getProductPrice() %>"><br><br>

                <label>Quantity:</label>
                <input type="number" name="product_quantity" value="<%= product.getProductQuantity() %>"><br><br>

                <label>Description:</label> <br>
                <textarea name="product_description" rows="4"><%= product.getProductDescription() %></textarea> <br><br>

                <label for="color">Color:</label>
                <div style="display: flex; justify-content: space-between">
                    <div>
                        <input type="checkbox" name="color_red" value="true" <%= product.getColors() != null && product.getColors().contains("red") ? "checked='checked'" : "" %>> Red <br>
                        <input type="checkbox" name="color_blue" value="true" <%= product.getColors() != null && product.getColors().contains("blue") ? "checked='checked'" : "" %>> Blue <br>
                        <input type="checkbox" name="color_black" value="true" <%= product.getColors() != null && product.getColors().contains("black") ? "checked='checked'" : "" %>> Black <br>
                    </div>
                    <div>
                        <input type="checkbox" name="color_white" value="true" <%= product.getColors() != null && product.getColors().contains("white") ? "checked='checked'" : "" %>> White <br>
                        <input type="checkbox" name="color_green" value="true" <%= product.getColors() != null && product.getColors().contains("green") ? "checked='checked'" : "" %>> Green <br>
                        <input type="checkbox" name="color_yellow" value="true" <%= product.getColors() != null && product.getColors().contains("yellow") ? "checked='checked'" : "" %>> Yellow <br>
                    </div>
                    <div>
                        <input type="checkbox" name="color_grey" value="true" <%= product.getColors() != null && product.getColors().contains("grey") ? "checked='checked'" : "" %>> Grey <br>
                        <input type="checkbox" name="color_pink" value="true" <%= product.getColors() != null && product.getColors().contains("pink") ? "checked='checked'" : "" %>> Pink <br>
                        <input type="checkbox" name="color_orange" value="true" <%= product.getColors() != null && product.getColors().contains("orange") ? "checked='checked'" : "" %>> Orange <br>
                    </div>
                </div>
                <br><label for="size">Size:</label> <br>
        
                <div style="display: flex; justify-content: space-between">
                    <div>
                        <input type="checkbox" name="size_small" value="true" <%= product.getSize().contains("small") ? "checked='checked'" : "" %>> S <br>
                        <input type="checkbox" name="size_large" value="true" <%= product.getSize().contains("large") ? "checked='checked'" : "" %>> L <br>
                        <input type="checkbox" name="size_double_extra_large" value="true" <%= product.getSize().contains("double extra large") ? "checked='checked'" : "" %>> XXL <br>
                    </div>
                    <div>
                        <input type="checkbox" name="size_Medium" value="true" <%= product.getSize().contains("Medium") ? "checked='checked'" : "" %>> M <br>
                        <input type="checkbox" name="size_extra_large" value="true" <%= product.getSize().contains("extra large") ? "checked='checked'" : "" %>> XL <br>
                        <input type="checkbox" name="size_triple_extra_large" value="true" <%= product.getSize().contains("triple extra large") ? "checked='checked'" : "" %>> XXXL <br>
                    </div>
                    <div>
                        <input type="checkbox" name="size_35" value="true" <%= product.getSize().contains("35") ? "checked='checked'" : "" %>> 35 <br>
                        <input type="checkbox" name="size_36" value="true" <%= product.getSize().contains("36") ? "checked='checked'" : "" %>> 36 <br>
                        <input type="checkbox" name="size_37" value="true" <%= product.getSize().contains("37") ? "checked='checked'" : "" %>> 37 <br>
                    </div>
                    <div>
                        <input type="checkbox" name="size_38" value="true" <%= product.getSize().contains("38") ? "checked='checked'" : "" %>> 38 <br>
                        <input type="checkbox" name="size_39" value="true" <%= product.getSize().contains("39") ? "checked='checked'" : "" %>> 39 <br>
                        <input type="checkbox" name="size_40" value="true" <%= product.getSize().contains("40") ? "checked='checked'" : "" %>> 40 <br>
                    </div>
                    <div>
                        <input type="checkbox" name="size_41" value="true" <%= product.getSize().contains("41") ? "checked='checked'" : "" %>> 41 <br>
                        <input type="checkbox" name="size_42" value="true" <%= product.getSize().contains("42") ? "checked='checked'" : "" %>> 42 <br>
                    </div>
                </div>
            </div>
            <div class="right-side">

                <label>Keywords:</label>
                <!-- Store the selected keyword as a hidden input -->
                <input type="hidden" name="product_keyword" value="<%= product.getProductKeyword() %>">
                <select name="selected_keyword" style="width: 500px">
                    <option> --- Select Keywords ---</option>
                    <option value="shoes" <%= product.getProductKeyword().contains("SHOES")? "selected" : "" %>>Shoes</option>
                    <option value="T-shirt" <%= product.getProductKeyword().contains("T-SHIRT")? "selected" : "" %>>T-shirt</option>
                    <option value="Track Suit" <%= product.getProductKeyword().contains("TRACK SUIT")? "selected" : "" %>>Track Suit</option>
                    <option value="Jaama" <%= product.getProductKeyword().contains("JAAAMA")? "selected" : "" %>>Jaama</option>
                    <option value="Medy" <%= product.getProductKeyword().contains("MEDY")? "selected" : "" %>>Medy</option>
                    <option value="Tops" <%= product.getProductKeyword().contains("TOPS")? "selected" : "" %>>Tops</option>
                </select> <br><br>
				
                <label for="product_image">Product Image:</label><br>
                <input type="file" id="product_image" name="product_image" accept="image/*"
                       value="<%= product.getProductImage() %>" style="width: 470px"><br>
                 <img id="image_preview" src="data:image/jpeg;base64, <%= product.getProductImage() %>"
                     alt="Product Image Preview" style="height: 350px; width: 300px"> 
            </div>
        </div>
        <button type="submit">Update</button>
        <% } %>
    </form>
</section>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
<!-- ... JavaScript code remains the same ... -->
</body>
</html>
