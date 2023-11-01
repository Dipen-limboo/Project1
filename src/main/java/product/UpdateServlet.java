package product;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("product_name");
	    InputStream ins = null;
	        Part file = request.getPart("product_image");
	        if (file != null) {
	        	ins = file.getInputStream();
	        }
		double price = Double.parseDouble(request.getParameter("product_price"));
		int quantity =  Integer.parseInt(request.getParameter("product_quantity"));
		String keywords = request.getParameter("product_keyword");
		String description = request.getParameter("product_description");
		String colors = request.getParameter("color");
		String size = request.getParameter("size");
		int id = Integer.parseInt(request.getParameter("product_id"));
		
		Product pr = new Product();
		pr.setProductID(id);
		pr.setProductName(name);
		pr.setProductImage(ins);
		pr.setProductPrice(price);
		pr.setProductQuantity(quantity);
		pr.setProductKeyword(keywords);
		pr.setProductDescription(description);
		pr.setColor(colors);
		pr.setSize(size);
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false", "root", "0564");
	         ps = conn.prepareStatement("update products set product_name=? , product_image=?, product_price = ?, product_keyword = ?, product_description = ?, color = ?, size = ?, product_quantity = ? where product_id= ?");
	         ps.setString(1, pr.getProductName());
	        //here logic to insert the image or update the image
	         ps.setDouble(3, pr.getProductPrice());
	         ps.setString(4, pr.getProductKeyword());
	         ps.setString(5, pr.getProductDescription());
	         ps.setString(6, pr.getColor());
	         ps.setString(7, pr.getSize());
	         ps.setInt(8, pr.getProductQuantity());
	         ps.setInt(9, pr.getProductID());
	         int status = ps.executeUpdate();
	         if (status > 0) {
	        	 request.setAttribute("update", pr);
	        	 out.print("update succesfully");
	        	 response.sendRedirect("ViewServlet");
	 
	         } else {
	        	 out.print("Failed to update");
	        	 request.getRequestDispatcher("edit.jsp").forward(request, response);
	        	 
	         }
		} catch (Exception e ) {
			System.out.println("message" +e.getMessage());
		}
	}

}
