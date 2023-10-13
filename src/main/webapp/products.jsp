<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="product.Product" %>
<%@ page import="java.util.List" %>
<%
	String pagename =  "products.jsp";
	session.setAttribute("currentpage", pagename);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>eCommerce Frontend</title>
    <link rel="stylesheet" href="./CSS/styles.css">
</head>
<body>
    <jsp:include page="./Header/userHeader.jsp"></jsp:include>
    <main>
    <div class="left">
    	<div style="margin-top: 1rem; text-align: center;">
    		<h2 style="font-weight: normal; font-family: Times New Roman; letter-spacing: 1.5px; color: green">Categories</h2>
    		<hr style="width: 100px; ">	
   	 	</div>
   	 	 <%
       		request.getRequestDispatcher("/display").include(request, response);
            List<Product> productList = (List<Product>) request.getAttribute("productList");

            if (productList != null && !productList.isEmpty()) {
                Product product = productList.get(0);
        %>
   	 	<div class="cate" style="margin:15px 0 0 10px">
   	 		<ul>
   	 		<li><a href="Categories?product_keyword=Shoes">Shoes</a></li>
   	 		<li><a href="Categories?product_keyword=T-shirt">T-shirt</a></li>
   	 		<li><a href="Categories?product_keyword=Track suit">Track Suit</a></li>
   	 		<li><a href="Categories?product_keyword=Jaama">Jaama</a></li>
   	 		<li><a href="Categories?product_keyword=Medy">Medy</a></li>
   	 		<li><a href="Categories?product_keyword=Tops">Tops</a></li>
   	 		</ul>
   	 	</div>
    </div>
    <div class="main">
    <% int count = 0; %>
    <% for (Product products : productList) { 
    	if(count >= 8){break;}
    %>
       <a href = "show?product_id=<%= products.getProductID() %>" style = "text-decoration: none; color: #333"> 
       <div class="product" id= "product" >  
            <img src="data:image/jpeg;base64, <%= products.getProductImage() %>" alt="here is an image" style= "height: 150px; width: 100%">
            <h2 style="text-align: left; font-size:12px"><%= products.getProductName() %></h2> <br>
			
            <p class="price" style="text-align: left;  font-size: 11px" >Price: Rs <%= products.getProductPrice() %> /-</p>
        </div> </a>
        <%
        count++;
                }
            } 
            %>
            </div>
    </main>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>
