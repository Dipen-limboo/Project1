package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/changeProfile")
public class changeProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setContentType("text/html");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");

		Connection conn = null;
		PreparedStatement ps = null; 
		int status = 0;
		
		try {
			 conn = UserDao.getConnection();
			  ps = conn.prepareStatement("update users set fname= ?, lname=?, email=? where id = ?");
			  ps.setString(1, fname);
			  ps.setString(2, lname);
			  ps.setString(3, email);
			  ps.setInt(4, id);
			  
			  status = ps.executeUpdate();
			  if (status> 0) {
				  response.sendRedirect("profie");
			  } else {
				  response.sendRedirect("changeProfile");
			  }
		} catch (Exception e) {
			System.out.println("Message: " +e.getMessage());
			e.printStackTrace();
		}
	}

}
