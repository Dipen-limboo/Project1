package user_profile_packag;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.User;
import Controller.UserDao;

/**
 * Servlet implementation class passwordChange
 */
@WebServlet("/passwordChange")
public class passwordChange extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
        User uusr = (User) session.getAttribute("user");

        if (uusr != null) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = UserDao.getConnection();
			ps = conn.prepareStatement("select psw from users where id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			User user = null;
			while (rs.next()) {
				user = new User();
				user.setId(id);
				user.setPassword(rs.getString("psw"));
			}
			request.setAttribute("password", user);
			request.getRequestDispatcher("./passwordChange.jsp").forward(request, response);
		} catch(Exception e) {
			System.out.println("Message: "+e.getMessage());
			e.printStackTrace();
		}
        } else {
            response.sendRedirect("./login.jsp?source=cartServlet");
        }
	}
}
