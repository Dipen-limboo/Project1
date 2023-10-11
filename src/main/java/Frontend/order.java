package Frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
		String phone = request.getParameter("phone");
		String Location = request.getParameter("Location");
		
		try {
			Connection conn = UserDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into orders (user_id, Location, phone) values (?, ?, ?)");
			ps.setInt(1, userId);
			ps.setString(2, Location);
			ps.setString(3, phone);
			
			int status = ps.executeUpdate();
			if (status > 0) {
				out.println("You have places order!!!");
			} 
			else {
				out.println("Your ordered has been cancelled!!!");
			}
			
		} catch (Exception e) {
			System.out.println("Message: " +e.getMessage());
			e.printStackTrace();
		}
	}

}
