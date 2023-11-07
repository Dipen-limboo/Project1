<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="product.Product" %>
<%@ page import="java.util.List" %>
<%
    String pagename = "display.jsp";
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
    String source = request.getParameter("source");

    if (source != null && source.equals("category")) {
       
    } else {
      
        request.getRequestDispatcher("/display").include(request, response);
    }

    List<Product> productList = (List<Product>) request.getAttribute("productList");

    if (productList != null && !productList.isEmpty()) {
        for (Product products : productList) { %>
            <a href="show?product_id=<%= products.getProductID() %>" style="text-decoration: none; color: #333">
                <div class="product" id="product">
                    <img src="data:image/jpeg;base64, <%= products.getProductImage() %>" alt="here is an image" style="height: 150px; width: 100%">
                    <h2 style="text-align: left; font-size: 12px"><%= products.getProductName() %></h2> <br>
                    <p class="price" style="text-align: left; font-size: 11px">Price: Rs <%= products.getProductPrice() %> /-</p>
                </div>
            </a>
            <%
        }
    }
    %>
</main><br><br>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>
