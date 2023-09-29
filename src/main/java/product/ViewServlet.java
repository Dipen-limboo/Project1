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
            String sql = "select * from products";
 
                    
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Product currentProduct = null;

            while (rs.next()) {
             
                    currentProduct = new Product();
                    currentProduct.setProductID(rs.getInt(1));
                    currentProduct.setProductName(rs.getString("product_name"));
                    currentProduct.setProductImage(rs.getString("product_image"));
                    currentProduct.setProductPrice(rs.getDouble("product_price"));
                    currentProduct.setProductKeyword(rs.getString("product_keyword"));
                    currentProduct.setProductDescription(rs.getString("product_description"));
                    currentProduct.setColor(rs.getString("color"));
                    currentProduct.setSize(rs.getString("size"));
                    currentProduct.setProductQuantity(rs.getInt("product_quantity"));
                    productList.add(currentProduct);
                   
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
