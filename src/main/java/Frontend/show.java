package Frontend;

import java.io.IOException;
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

import product.Product;

@WebServlet("/show")
public class show extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("html/text");
		
		int id = Integer.parseInt(request.getParameter("product_id"));
		
		Connection conn = null;
		PreparedStatement ps = null;
		Product product =null;
		
		List<Product> list = new ArrayList<>();
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false", "root", "0564");
	         ps = conn.prepareStatement("select * from products where product_id = ?");

	         ps.setInt(1, id);
	         ResultSet rs = ps.executeQuery();
	         while (rs.next()) {
	                product = new Product();
	                product.setProductID(rs.getInt("product_id"));
	                product.setProductName(rs.getString("product_name"));
	                product.setProductImage(rs.getString("product_image"));
	                product.setProductPrice(rs.getDouble("product_price"));
	                product.setProductKeyword(rs.getString("product_keyword"));
	                product.setProductDescription(rs.getString("product_description"));
	                product.setColor(rs.getString("color"));
	                product.setSize(rs.getString("size"));
	                list.add(product);
	                
	            }
	           
	            request.setAttribute("productList", list);
				
				request.getRequestDispatcher("./showProduct.jsp").forward(request, response);
				 
	            //response.sendRedirect("./showProduct.jsp");
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            // Handle exceptions appropriately for production code
	        } finally {
	            try {
	                if (ps != null) {
	                    ps.close();
	                }
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
		
	}


}
