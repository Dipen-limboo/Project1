package Frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.UserDao;


@WebServlet("/order")
public class order extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int userId = Integer.parseInt(request.getParameter("user_id"));
		String[] productId= request.getParameterValues("product_id");
		String[] cartId = request.getParameterValues("cart_id");
		String phone = request.getParameter("phone");
		String Location = request.getParameter("Location");
		
		try {
			Connection conn = UserDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into orders (Location, phone, order_date) values ( ?, ?, ?)");
			
			ps.setString(1, Location);
			ps.setString(2, phone);
			
			Timestamp currentTimestamp = new Timestamp(new Date().getTime());
	        ps.setTimestamp(3, currentTimestamp);
			
			int status = ps.executeUpdate();
			if (status > 0) {
				ps = conn.prepareStatement("select LAST_INSERT_ID()");
				ResultSet rs = ps.executeQuery();
				
				int order_id = -1;
				while (rs.next()) {
					order_id = rs.getInt(1);
				}
				if (productId.length == cartId.length) {
                    for (int i = 0; i < productId.length; i++) {
                        ps = conn.prepareStatement("insert into order_details (order_id, product_id, cart_id, user_id) values (?, ?, ?, ?)");
                        ps.setInt(1, order_id);
                        ps.setInt(2, Integer.parseInt(productId[i]));
                        ps.setInt(3, Integer.parseInt(cartId[i]));
                        ps.setInt(4, userId);

                        status = ps.executeUpdate();
                    }
                } else {
                    out.println("Product and cart ID arrays have different lengths.");
                }
				if (status > 0) {		
						out.println("You have places order!!!");
					} 
					else {
						out.println("Your ordered has been cancelled!!!");
					}
			}
		} catch (Exception e) {
			System.out.println("Message: " +e.getMessage());
			e.printStackTrace();
		}
	}

}
