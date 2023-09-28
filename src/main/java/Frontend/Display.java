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
                product.setProductImage(rs.getString("product_image"));
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
            response.sendRedirect("./products.jsp");
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
