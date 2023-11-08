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
import javax.servlet.http.HttpSession;

import Controller.User;
import Controller.UserDao;


@WebServlet("/order")
public class order extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");

        if (usr != null) {
		int userId = Integer.parseInt(request.getParameter("user_id"));
		String[] productId= request.getParameterValues("product_id");
		String[] cartId = request.getParameterValues("cart_id");
		String phone = request.getParameter("phone");
		String Location = request.getParameter("Location");
		String type= "sell";
		String[] quantity =request.getParameterValues("quantity");
		double total = Double.parseDouble(request.getParameter("total"));
		
		try {
			Connection conn = UserDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into orders (Location, phone, order_date, total) values ( ?, ?, ?, ?)");
			
			ps.setString(1, Location);
			ps.setString(2, phone);
			
			Timestamp currentTimestamp = new Timestamp(new Date().getTime());
	        ps.setTimestamp(3, currentTimestamp);
			ps.setDouble(4, total);
			int status = ps.executeUpdate();
			if (status > 0) {
				ps = conn.prepareStatement("select LAST_INSERT_ID()");
				ResultSet rs = ps.executeQuery();
				
				int order_id = -1;
				while (rs.next()) {
					order_id = rs.getInt(1);
				}
				if (productId.length == cartId.length && cartId.length == quantity.length) {
                    for (int i = 0; i < productId.length; i++) {
                        ps = conn.prepareStatement("insert into order_details (order_id, product_id, user_id, quantity) values (?, ?, ?, ?)");
                        ps.setInt(1, order_id);
                        ps.setInt(2, Integer.parseInt(productId[i]));
                        
                        ps.setInt(3, userId);
                        ps.setInt(4, Integer.parseInt(quantity[i]));
                        
                        status = ps.executeUpdate();
                    }
                } else {
                    out.println("Product and cart ID arrays have different lengths.");
                }
				if (status > 0) {
					if(productId.length == quantity.length)
					 for (int i = 0; i < productId.length; i++) {
					PreparedStatement stocksPs = conn.prepareStatement("insert into stocks (product_id, user_id, quantity, type, date) values (?,?,?,?,?)");
					stocksPs.setInt(1, Integer.parseInt(productId[i]));
					stocksPs.setInt(2, userId);
					
					stocksPs.setInt(3, Integer.parseInt(quantity[i]));
					stocksPs.setString(4, type);
					Timestamp currentTime = new Timestamp(new Date().getTime());
			        stocksPs.setTimestamp(5, currentTime);
					int stockProcess = stocksPs.executeUpdate();
					
					if(stockProcess > 0) {
						if (productId.length == cartId.length) {
		                    for (int j = 0; j < productId.length; j++) {
						PreparedStatement deletePs = conn.prepareStatement("delete from carts where cart_id = ?");
							deletePs.setInt(1, Integer.parseInt(cartId[j]));
							int process = deletePs.executeUpdate();
		                    	}
							} else {
							System.out.println("Cannot delete the cart");
							}
					}
					for (int x = 0; x < productId.length; x++) {
			            int productIdValue = Integer.parseInt(productId[x]);
			            int orderQuantity = Integer.parseInt(quantity[x]);
			            int remainingQuantity = 0;

			            PreparedStatement stockPs = conn.prepareStatement("SELECT quantity FROM stocks WHERE type = 'buy' AND product_id = ?");
			            stockPs.setInt(1, productIdValue);
			            ResultSet stockRs = stockPs.executeQuery();

			            while (stockRs.next()) {
			                remainingQuantity = stockRs.getInt("quantity");
			            }

			            
			            int newRemainingQuantity = remainingQuantity - orderQuantity;

			            PreparedStatement updateProductPs = conn.prepareStatement("UPDATE products SET remain = ? WHERE product_id = ?");
			            updateProductPs.setInt(1, newRemainingQuantity);
			            updateProductPs.setInt(2, productIdValue);
			            int stats = updateProductPs.executeUpdate();
			        }
					
					 
				}
				

					out.println("You have places order!!");
					request.getRequestDispatcher("sucessfullyOrder.jsp").forward(request, response);		
			/*
			 * out.println("You have places order!!!");
			 * request.getRequestDispatcher("remaining?quantity="+quantity+"&&product_id="+
			 * productId).forward(request, response);
			 */
				} 
				else {
					out.println("Your ordered has been cancelled!!!");
				}
			}
		} catch (Exception e) {
			System.out.println("Message: " +e.getMessage());
			e.printStackTrace();
		}
        } else {
            response.sendRedirect("./login.jsp?source=cartServlet");
        }
	}

}
