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

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false", "root", "0564");

            int productId = Integer.parseInt(request.getParameter("product_id"));

            String productQuery = "SELECT * FROM products WHERE product_id=?";
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
                byte [] byt = new byte[4096];
                int byteread = -1;
                while ((byteread = ins.read(byt))!= -1) {
                	os.write(byt, 0, byteread);
                }
                byte[] imageByte = os.toByteArray();
                String image = Base64.getEncoder().encodeToString(imageByte);
                product.setProductImage(image);
                product.setProductPrice(productResultSet.getDouble("product_price"));
                product.setProductQuantity(productResultSet.getInt("product_quantity"));
                product.setProductKeyword(productResultSet.getString("product_keyword"));
                product.setProductDescription(productResultSet.getString("product_description"));
                product.setColor(productResultSet.getString("color"));
                product.setSize(productResultSet.getString("size"));
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
