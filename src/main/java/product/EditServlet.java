package product;

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

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        response.setContentType("text/html;charset=UTF-8");
        PreparedStatement ps = null;
        ResultSet rs=null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false", "root", "0564");

            int productId = Integer.parseInt(request.getParameter("product_id"));

//            String productQuery = "SELECT p.product_id, p.product_name, p.product_image, p.product_price, p.product_keyword, p.product_description, GROUP_CONCAT(DISTINCT c.color) AS colors, GROUP_CONCAT(DISTINCT s.size) AS sizes, st.buyQuantity "
//                    + "FROM products AS p "
//                    + "LEFT JOIN colors c ON p.product_id = c.product_id "
//                    + "LEFT JOIN sizes s ON p.product_id = s.product_id "
//                    + "LEFT JOIN stocks st ON p.product_id = st.product_id "
//                    + "WHERE p.product_id = ? "
//                    + "GROUP BY p.product_id, p.product_name, p.product_image, p.product_price, p.product_keyword, p.product_description, st.buyQuantity;";
            String productQuery  = "select p.product_id, p.product_name, p.product_image, p.product_price, p.product_keyword, p.product_description, st.quantity "
            						+ "FROM products AS p "
            						+ "LEFT JOIN stocks st ON p.product_id = st.product_id "
            						+ "WHERE p.product_id = ? "
									+ "GROUP BY p.product_id, p.product_name, p.product_image, p.product_price, p.product_keyword, p.product_description, st.quantity;";
            PreparedStatement productStatement = conn.prepareStatement(productQuery);
            productStatement.setInt(1, productId);
            ResultSet productResultSet = productStatement.executeQuery();

            Product product = null;

            if (productResultSet.next()) {
                product = new Product();
                product.setProductID(productId);
                
                product.setProductName(productResultSet.getString("product_name"));
                
                Blob blob = productResultSet.getBlob("product_image");
                InputStream ins = blob.getBinaryStream();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                byte[] byt = new byte[4096];
                int byteread = -1;
                while ((byteread = ins.read(byt)) != -1) {
                    os.write(byt, 0, byteread);
                }
                byte[] imageByte = os.toByteArray();
                String image = Base64.getEncoder().encodeToString(imageByte);
                product.setProductImage(image);
               
                product.setProductPrice(productResultSet.getDouble("product_price"));
                product.setProductQuantity(productResultSet.getInt("quantity")); 
                product.setProductKeyword(productResultSet.getString("product_keyword"));
                System.out.println(productResultSet.getString("product_keyword"));
                product.setProductDescription(productResultSet.getString("product_description"));
				
                //category or keywords
                String category = (productResultSet.getString("product_keyword"));
                request.setAttribute("category", category);
                
                //color table
                ps = conn.prepareStatement("select color from colors where product_id = ?");
                ps.setInt(1, productId);
                rs=ps.executeQuery();
                List<String> colors = new ArrayList<>();
                while (rs.next()) {
                    colors.add(rs.getString("color"));
                }
                product.setColors(colors);
				/* request.setAttribute("color", colors) */;

                
                //size table
                ps = conn.prepareStatement("select size from sizes where product_id = ?");
                ps.setInt(1,  productId);
                rs = ps.executeQuery();
                List<String> sizes = new ArrayList<>();
                while (rs.next()) {
                	sizes.add(rs.getString("size"));
                }
                product.setSize(sizes);
//                request.setAttribute("size", sizes);
                
            }

            request.setAttribute("product", product);
            request.getRequestDispatcher("/editProduct.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
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
