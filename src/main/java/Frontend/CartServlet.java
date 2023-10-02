package Frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.User;

@WebServlet("/cartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html"); 

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");

        if (usr != null) {
            int productID = Integer.parseInt(request.getParameter("productID"));
            int userID = Integer.parseInt(request.getParameter("userID"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false",
                        "root", "0564");

                PreparedStatement pps = conn.prepareStatement("select product_price, product_image, product_name from products where product_id = ?");
                pps.setInt(1, productID);

                ResultSet rs = pps.executeQuery();
                double price = 0.00;
                while (rs.next()) {
                    price = rs.getDouble("product_price");
                    String image = rs.getString("product_image");
                    String name = rs.getString("product_name");
                }

                double total = price * quantity;
  
                PreparedStatement ps = conn.prepareStatement(
                        "insert into carts (user_id, product_id, quantity, total_price ) values (?, ?, ?, ?)");
                ps.setInt(1, userID);
                ps.setInt(2, productID);
                ps.setInt(3, quantity);
                ps.setDouble(4, total);

                int status = ps.executeUpdate();

                if (status > 0) {
                    PreparedStatement pss = conn.prepareStatement(
                            "SELECT c.cart_id, c.user_id, c.product_id, c.quantity, p.product_price, p.product_image, p.product_name, c.total_price "
                                    + "FROM carts AS c JOIN products p ON c.product_id = p.product_id JOIN users u ON c.user_id = u.id WHERE c.user_id = ?");
                    pss.setInt(1, userID);
                    List<Cart> list = new ArrayList<>();
                    ResultSet res = pss.executeQuery();
                    double sumtotal = 0.0;
                    while (res.next()) {
                        Cart cart = new Cart();
                        cart.setCartId(res.getInt("cart_id"));
                        cart.setUser_id(res.getInt(userID));
                        cart.setProduct_id(res.getInt("product_id"));
                        cart.setQuantity(res.getInt("quantity"));
                        cart.setTotalPrice(res.getDouble("total_price"));
                        cart.setProduct_price(res.getInt("product_price"));
                        cart.setProductImage(res.getString("product_image"));
                        cart.setProduct_name(res.getString("product_name"));
                        list.add(cart);
                        sumtotal += cart.getTotalPrice();
                    }

                    request.setAttribute("cartList", list);
                    request.setAttribute("sumtotal", sumtotal);
                    request.getRequestDispatcher("./cart.jsp").forward(request, response);
                } else {
                    response.sendRedirect("./showProduct.jsp");
                }

            } catch (Exception e) {
                System.out.println("message" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("./login.jsp?source=cartServlet");
        }
    }
}
