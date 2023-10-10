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
        <%
       		request.getRequestDispatcher("/display").include(request, response);
            List<Product> productList = (List<Product>) request.getAttribute("productList");

            if (productList != null && !productList.isEmpty()) {
                for (Product product : productList) {
        %>
       <a href = "show?product_id=<%= product.getProductID() %>" style = "text-decoration: none; color: #333"> 
       <div class="product" id= "product" >  
            <img src="data:image/jpeg;base64, <%= product.getProductImage() %>" alt="here is an image" style= "height: 150px; width: 100%">
            <h2 style="text-align: left; color: #333; text-decoration: underline; font-size:12px"><%= product.getProductName() %></h2> <br>
			
            <p class="price" style="text-align: left; color: #333; font-size: 11px" >Price: Rs <%= product.getProductPrice() %> /-</p>
        </div> </a>
        <%
                }
            } 
            %>
    </main>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>
