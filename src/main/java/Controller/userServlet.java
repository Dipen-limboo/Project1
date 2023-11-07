package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/userServlet")
public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		try {
			Connection conn = UserDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from users");
			ResultSet rs = ps.executeQuery();
			
			List <User> list = new ArrayList<>();
			User usr = null;
			
			while(rs.next()) {
				usr = new User();
				usr.setId(rs.getInt("id"));
				usr.setFname(rs.getString("fname"));
				usr.setLname(rs.getString("lname"));
				usr.setEmail(rs.getString("email"));
				usr.setPassword(rs.getString("psw"));
				usr.setRole(rs.getString("role"));
				list.add(usr);
			}
			request.setAttribute("userList", list);
			request.getRequestDispatcher("UserPanel.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Message: " +e.getMessage());
			e.printStackTrace();
		}
       
	}
}
