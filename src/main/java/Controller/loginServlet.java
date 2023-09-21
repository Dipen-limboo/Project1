package Controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html/jsp");
	
		
		String u_email = request.getParameter("email");
		String u_password = request.getParameter("psw");
		
		User usr = new User();
		usr.setEmail(u_email);
		usr.setPassword(u_password);
		
		boolean userExists = UserDao.check(usr);
		if (userExists == true) {
			response.sendRedirect("products.jsp");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}


}
