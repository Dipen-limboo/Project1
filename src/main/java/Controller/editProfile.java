package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/editProfile")
public class editProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Connection conn = null;
		PreparedStatement ps = null; 
		ResultSet rs = null;
		try {
		  conn = UserDao.getConnection();
		  ps = conn.prepareStatement("select * from users where id = ?");
		  ps.setInt(1, id);
		  
		  rs = ps.executeQuery();
		  
		  User usr = null;
		  while(rs.next()) {
			  usr = new User();
			  usr.setId(id);
			  usr.setFname(rs.getString("fname"));
			  usr.setLname(rs.getString("lname"));
			  usr.setEmail(rs.getString("email"));
			  usr.setPassword(rs.getString("psw"));
		  }
		  request.setAttribute("userList", usr);
		  request.getRequestDispatcher("./editProfile.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Message: " +e.getMessage());
			e.printStackTrace();
		}
	}
}


