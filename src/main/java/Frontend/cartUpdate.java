package Frontend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class cartUpdate
 */
@WebServlet("/cartUpdate")
public class cartUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		int cartId = Integer.parseInt(request.getParameter( "cart_id"));
		int quantity = Integer.parseInt(request.getParameter( "quantity"));
		
//		Cart cart = new Cart();
//		cart.setCartId(cartId);
//		cart.setQuantity(quantity);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false",
                    "root", "0564");
            PreparedStatement ps = conn.prepareStatement("select c.cart_id, p.product_price from carts as c join products p on c.product_id = p.product_id where c.cart_id = ? ");
            ps.setInt(1, cartId);
            
            ResultSet rs = ps.executeQuery();
             while (rs.next()){
            	 Cart cart = new Cart();
            	 cart.setCartId(rs.getInt(cartId));
            	 cart.setProduct_price(rs.getDouble("product_price"));
            	 
             }
//            	response.sendRedirect("showCart");
//            } else {
//            	response.sendRedirect("./cart.jsp");
//            }
		} catch (Exception e) {
			System.out.println("message" +e.getMessage());
			e.printStackTrace();
		}
	}

}
