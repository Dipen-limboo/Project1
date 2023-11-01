package product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.UserDao;

/**
 * Servlet implementation class reviewServlet
 */
@WebServlet("/reviewServlet")
public class reviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("orderDetails_id"));
		int userId = Integer.parseInt(request.getParameter("user_id"));
		
		Connection conn= null;
		PreparedStatement ps = null;
		int status = 0;
		
		try {
			conn = UserDao.getConnection();
			ps = conn.prepareStatement("insert into ");
		} catch (Exception e) {
			System.out.println("Message: " + e.getMessage());
			e.printStackTrace();
		}
	}
}




