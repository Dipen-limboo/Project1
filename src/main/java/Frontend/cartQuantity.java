package Frontend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.UserDao;

@WebServlet("/cartQuantity")
public class cartQuantity extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("product_id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int userId = Integer.parseInt(request.getParameter("user_id"));
		Connection conn = null; 
		PreparedStatement ps = null;
		if(quantity < 0) {
			request.getRequestDispatcher("showCart").forward(request, response);
		} else {
		try {
			conn = UserDao.getConnection();
			PreparedStatement prs= conn.prepareStatement("select product_price from products where product_id = ?");
			prs.setInt(1, productId);
			
			ResultSet rs = prs.executeQuery(); 
			double price = 0.00;
			while(rs.next()) {
				price = rs.getDouble("product_price");
			}
			
			double total = price * quantity;
			ps = conn.prepareStatement("update carts set quantity = ?, total_price=? where product_id = ? and user_id = ? ");
			ps.setInt(1, quantity);
			ps.setDouble(2, total);
			ps.setInt(3, productId);
			ps.setInt(4,  userId);
			
			int status = ps.executeUpdate();
			if(status > 0 ) {
			request.getRequestDispatcher("showCart").forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("message" +e.getMessage());
			e.printStackTrace();
		}
	}
	}
}
