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

@WebServlet("/editUserPanel")
public class editUserPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			Connection conn = UserDao.getConnection();
			PreparedStatement ps= conn.prepareStatement("select * from users where id = ?");
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			User user = null;
			
			while (rs.next()) {
				user = new User();
				user.setId(id);
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("psw"));
				user.setRole(rs.getString("role"));
			}
			request.setAttribute("role", user);
			request.getRequestDispatcher("changeRolePanel.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Message: " +e.getMessage());
			e.printStackTrace();
		}
        
	}
}
