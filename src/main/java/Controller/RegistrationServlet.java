package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("html/text/jsp");
		PrintWriter out = response.getWriter();
		
		String f_name = request.getParameter("fname");
		String l_name = request.getParameter("lname");
		String u_email = request.getParameter("email");
		String password = request.getParameter("psw");
		
		User usr = new User();
		usr.setFname(f_name);
		usr.setLname(l_name);
		usr.setEmail(u_email);
		usr.setPassword(password);
		
	int status = UserDao.save(usr);
	
	if(status > 0) {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	} else {
		RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
		rd.forward(request, response);
	}
	out.close();
	}

}
