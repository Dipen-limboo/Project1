package user_profile_packag;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.User;
import Controller.UserDao;

/**
 * Servlet implementation class UserReview
 */
@WebServlet("/userReview")
public class UserReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");

        if (usr != null) {
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int rating = Integer.parseInt(request.getParameter("rating"));
		String comment = request.getParameter("comment");
		int accept = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		int status = 0;
		try {
			conn =  UserDao.getConnection();
			ps = conn.prepareStatement("insert into reviews (product_id, user_id, rate, message, accept) values (?, ?, ?, ?, ?)");
			ps.setInt(1, product_id);
			ps.setInt(2,  user_id);
			ps.setInt(3, rating);
			ps.setString(4, comment);
			ps.setInt(5, accept);
			
			status = ps.executeUpdate();
			
			if(status > 0) {
				out.println("insert Successfully!!!");
				response.sendRedirect("review?id =" + user_id);
			} else {
				System.out.println("failed to insert in reviews. Print in line 51 of UserReview.java");
				response.sendRedirect("./reviewPanel.jsp");
			}
		} catch(Exception e) {
			System.out.println("Message: " +e.getMessage());
			e.printStackTrace();
		}
	} else {
        response.sendRedirect("./login.jsp?source=cartServlet");
    }
	}

}
