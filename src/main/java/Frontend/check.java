package Frontend;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.User;
import Controller.UserDao;

@WebServlet("/check")
public class check extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cartId = request.getParameter("cart_id");
        
        try {
        	Connection conn = UserDao.getConnection();
        	PreparedStatement ps = conn.prepareStatement("select u.fname, u.lname, c.total_price");
        }
	}
}
