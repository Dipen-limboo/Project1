package Frontend;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
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
	                Blob blob = rs.getBlob("product_image");
    				InputStream ins = blob.getBinaryStream();
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    byte [] byt = new byte[4096];
                    int byteread = -1;
                    while ((byteread = ins.read(byt))!= -1) {
                    	os.write(byt, 0, byteread);
                    }
                    byte[] imageByte = os.toByteArray();
                    String image = Base64.getEncoder().encodeToString(imageByte);
                    product.setProductImage(image);
	                product.setProductPrice(rs.getDouble("product_price"));
	                product.setProductKeyword(rs.getString("product_keyword"));
	                product.setProductDescription(rs.getString("product_description"));
	                
	                List<String> colors =  new ArrayList<>();
	                PreparedStatement colorPs =  conn.prepareStatement("select GROUP_CONCAT(DISTINCT color) as colors from colors where product_id=?");
	                colorPs.setInt(1, product.getProductID());
	                ResultSet res = colorPs.executeQuery();
	                while(res.next()) {
	              	  colors.add(res.getString("colors"));
	                }
	                product.setColors(colors);
	                
	                List<String> sizes = new ArrayList<>();
	                PreparedStatement sizePs =  conn.prepareStatement("select GROUP_CONCAT(DISTINCT size) as sizes from sizes where product_id=?");
	                sizePs.setInt(1, product.getProductID());
	                ResultSet sizeRs = sizePs.executeQuery();
	                while(sizeRs.next()) {
	              	  sizes.add(sizeRs.getString("sizes"));
	                }
	                product.setSize(sizes);
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
