package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class updateRole
 */
@WebServlet("/updateRole")
public class updateRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
        User uusr = (User) session.getAttribute("user");

        if (uusr != null) {
		int id = Integer.parseInt(request.getParameter("id"));
		String role = request.getParameter("role");
		User usr = new User();
		usr.setId(id);
		usr.setRole(role);
		
		try {
			Connection conn = UserDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("update users set role= ? where id=?");
			ps.setString(1, usr.getRole());
			ps.setInt(2, usr.getId());
			
			int status = ps.executeUpdate();
			if(status > 0) {
				response.sendRedirect("userServlet");
			} else {
				response.sendRedirect("editUserPanel");
			}
		} catch (Exception e) {
			System.out.println("Message: "+e.getMessage());
			e.printStackTrace();
		}
	} else {
        response.sendRedirect("./login.jsp?source=cartServlet");
    }
	}

}
