package product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn =null;
		response.setContentType("html/text/jsp; charset= UTF-8");
		
		List<Product> list = new ArrayList<>();
		try(PrintWriter out = response.getWriter()) {
		    out.println("<!DOCTYPE html>");
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>View Product</title>");
	        out.println("</head>");
	        out.println("<body>");
	        Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false", "root", "0564");
			PreparedStatement ps = conn.prepareStatement("select * from products");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product pr = new Product();
				pr.setProductID(rs.getInt(1));
				pr.setProductName(rs.getString(2));
				pr.setProductImage(rs.getString(3));
				pr.setProductPrice(rs.getDouble(4));
				pr.setProductKeyword(rs.getString(5));
				pr.setProductDescription(rs.getString(6));
				
				list.add(pr);
			}
			request.setAttribute("data", list);
			request.getRequestDispatcher("View.jsp").forward(request, response);
			out.println("</body>");
	        out.println("</html>");
		} catch (Exception e) {
			System.out.println("message" +e.getMessage());
			e.printStackTrace();
		}  finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		}
	}


	


