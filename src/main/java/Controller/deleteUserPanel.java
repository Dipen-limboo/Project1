package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteUserPanel")
public class deleteUserPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Connection conn = UserDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from users where id =?");
			ps.setInt(1, id);
			
			int status = ps.executeUpdate();
			
			if(status > 0) {
				request.getRequestDispatcher("userServlet").forward(request, response);
			} else {
				response.sendRedirect("error.jsp");
			}
		} catch (Exception e) {
			System.out.println("Message: " +e.getMessage());
			e.printStackTrace();
		}
	}

}
