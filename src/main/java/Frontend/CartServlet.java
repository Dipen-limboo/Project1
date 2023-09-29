package Frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.User;

@WebServlet("/cartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("html/text");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		User usr = (User)session.getAttribute("user");
		
		if(usr != null) {
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false", "root", "0564");
		        PreparedStatement ps = conn.prepareStatement("");
			} catch (Exception e) {
				System.out.println("message" +e.getMessage());
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("./login.jsp?source=cartServlet");
		}
	}

}
