package Frontend;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.UserDao;

/**
 * Servlet implementation class increasingQuantity
 */
@WebServlet("/increasingQuantity")
public class increasingQuantity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int productId = Integer.parseInt(request.getParameter("product_id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int userId = Integer.parseInt(request.getParameter("userid"));
		Connection conn = null; 
		PreparedStatement ps = null;
		int qty = quantity + 1;
		try {
			conn = UserDao.getConnection();
			ps = conn.prepareStatement("update carts set quantity = ? where product_id = ? and user_id = ? ");
			ps.setInt(1, qty);
			ps.setInt(2, productId);
			ps.setInt(3,  userId);
			
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
