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


@WebServlet("/RemoveCart")
public class RemoveCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    HttpSession session = request.getSession();
	        User usr = (User) session.getAttribute("user");

	        if (usr != null) {
		    int id = Integer.parseInt(request.getParameter("cart_id"));
		    
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false", "root", "0564");
			
					PreparedStatement pss = conn.prepareStatement("delete from carts where cart_id = ?");
					pss.setInt(1, id);
					
					int state = pss.executeUpdate();
					if(state > 0) {
					
						System.out.println("Cart Deleted Successfully");
						response.sendRedirect("showCart");
					} 
					else {
						System.out.println("Failed To Delete Cart"); 
						response.sendRedirect("showCart");			
					}
					
		        
		    } catch (Exception e) {
		        System.out.println("message" + e.getMessage());
		        e.printStackTrace();
		    }
	   } else {
	            response.sendRedirect("./login.jsp?source=cartServlet");
	     }
	}
}

