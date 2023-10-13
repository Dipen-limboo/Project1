<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Frontend.Cart" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order</title>
<link rel="stylesheet" href="./CSS/styles.css">
</head>
<body>
<jsp:include page="./Header/userHeader.jsp"></jsp:include>
<div class="order">
    <form class="order_form" method="post" action="order">
        <%
        List<Cart> list = (List<Cart>) request.getAttribute("list");

        if (list != null && !list.isEmpty()) {
        %>
        <input type="hidden" name="user_id" value="<%= list.get(0).getUser_id() %>">
        <h2 style="margin-top: 20px; text-align: center;">Order</h2>
        <hr>
        <%
            for (Cart carts : list) {
        %>
        <input type="hidden" name="product_id" value="<%= carts.getProduct_id() %>">
    
        <input type="hidden" name="cart_id" value="<%= carts.getCartId() %>">
     
        <%
            }
        %>
        <label style="padding-top: 20px; font-weight: normal">Deliver to: <span><%= list.get(0).getFirstname() %> <%= list.get(0).getLastname() %></span></label>
        <label style="font-weight: normal">Email to: <span style="text-decoration: underline"><%= list.get(0).getEmail() %></span></label>
        <label style="font-weight: normal">Phone:</label>
        <input type="text" name="phone" style="width: 350px" required>
        <br>
        <label style="font-weight: normal">Location:</label>
        <input type="text" name="Location" style="width: 350px" required>
        <%
        }
        %>
        <br>
        <label style="font-weight: normal">Net Price: <span><%= request.getAttribute("total") %> /-</span></label>
        <label style="font-weight: normal">Vat(13%): <span><%= request.getAttribute("vat") %> /-</span></label>
        <hr style="margin-left: 25px; width: 140px; border-radius: 0px;">
        <label style="font-weight: normal">Total Price: <span><%= request.getAttribute("net") %> /-</span></label>
        <button style="margin-left: 70px">Place order</button>
    </form>
</div>
<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>
