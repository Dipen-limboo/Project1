package cart_packag;

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

@WebServlet("/cartRemove")
public class cartRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        User uusr = (User) session.getAttribute("user");

        if (uusr != null) {
		try {
			Connection conn = UserDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from carts");
			ResultSet rs =  ps.executeQuery();
			
			while(rs.next()) {
				request.getRequestDispatcher("./products.jsp").forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("Message:  " +e.getMessage());
			e.printStackTrace();
		}
	  } else {
          response.sendRedirect("./login.jsp?source=cartServlet");
      }
	}


}
