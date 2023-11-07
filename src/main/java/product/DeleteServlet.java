package product;

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

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("html/text");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
	    User usr = (User) session.getAttribute("user");

	    if (usr != null) {
		int id = Integer.parseInt(request.getParameter("product_id"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false", "root", "0564");
			PreparedStatement ps = conn.prepareStatement("delete from products where product_id = ?");
			ps.setInt(1, id);
			int status = ps.executeUpdate();
			if (status > 0) {
				out.print("Deleted successfully!!");
				response.sendRedirect("ViewServlet");
			} else {
				out.print("Failed to delete");
				response.sendRedirect("ViewServlet");
			}
		} catch (Exception e) {
			System.out.println("message" +e.getMessage());
			e.printStackTrace();
		}
	  } else {
            response.sendRedirect("./login.jsp?source=cartServlet");
        }
	}


}
