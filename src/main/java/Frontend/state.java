package Frontend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.UserDao;

@WebServlet("/state")
public class state extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("html/text");
		
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		int id = Integer.parseInt(request.getParameter("h3w"));
		String state = "Deliverd";
		try {
			Connection conn = UserDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("update orders set state=? where order_id = ?");
			ps.setString(1, state);
			ps.setInt(2, orderId);
			
			int status = ps.executeUpdate();
			if(status > 0) {
				System.out.println("Succesfully changed the state");
				request.getRequestDispatcher("orderServlet").forward(request, response);
			} else {
				System.out.println("Failed to change the state");
				request.getRequestDispatcher("orderServlet").forward(request, response);
			}
			
		} catch (Exception e) {
			System.out.println("Message: " +e.getMessage());
			e.printStackTrace();
		}
	}
}
