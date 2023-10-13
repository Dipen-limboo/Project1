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
import javax.servlet.http.HttpSession;

import product.Product;

@WebServlet("/display")
public class Display extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps =  null;
        List<Product> productList = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false", "root", "0564");
            ps = conn.prepareStatement("SELECT * FROM products");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Product product = new Product();
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
                product.setColor(rs.getString("color"));
                product.setSize(rs.getString("size"));
                productList.add(product);
              
            }
            
            request.setAttribute("productList", productList);
			/*
			 * request.getRequestDispatcher("/products.jsp").forward(request, response);
			 */
            HttpSession session = request.getSession();
            String name = (String)session.getAttribute("currentpage");
            if (name == "products") {
            response.sendRedirect("./products.jsp");}
            else {
            	response.sendRedirect("display.jsp");
            }
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
