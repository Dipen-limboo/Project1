package product;

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

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        response.setContentType("text/html;charset=UTF-8");

        List<Product> productList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false", "root", "0564");

            // Retrieve products and their variants using a SQL JOIN
            String sql = "SELECT p.product_id, p.product_name, p.product_image, p.product_price, " +
                    "p.product_keyword, p.product_description, " +
                    "GROUP_CONCAT(DISTINCT pv.color ORDER BY pv.v_id SEPARATOR ', ') AS color, " +
                    "GROUP_CONCAT(DISTINCT pv.size ORDER BY pv.v_id SEPARATOR ', ') AS size " +
                    "FROM products p " +
                    "INNER JOIN product_variants pv ON p.product_id = pv.product_id " +
                    "GROUP BY p.product_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            int currentProductId = -1;
            Product currentProduct = null;

            while (rs.next()) {
                int productId = rs.getInt("product_id");

                if (productId != currentProductId) {
                    currentProduct = new Product();
                    currentProduct.setProductID(productId);
                    currentProduct.setProductName(rs.getString("product_name"));
                    currentProduct.setProductImage(rs.getString("product_image"));
                    currentProduct.setProductPrice(rs.getDouble("product_price"));
                    currentProduct.setProductKeyword(rs.getString("product_keyword"));
                    currentProduct.setProductDescription(rs.getString("product_description"));
                    currentProduct.setVariants(new ArrayList<>());
                    productList.add(currentProduct);
                    currentProductId = productId;
                }

                ProductVarient productVariant = new ProductVarient("color", "size");// 
                productVariant.setColor(rs.getString("color"));
                productVariant.setSize(rs.getString("size"));

                currentProduct.getVariants().add(productVariant);
            }

            request.setAttribute("productList", productList);
            request.getRequestDispatcher("./View.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Print the exception stack trace for debugging
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
