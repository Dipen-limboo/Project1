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
import javax.servlet.http.HttpSession;

import Controller.User;

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

            String sql = "SELECT p.product_id, p.product_name, p.product_image, p.product_price, p.product_keyword, p.product_description, st.quantity " +
                    "FROM products AS p JOIN stocks st ON p.product_id = st.product_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product currentProduct = new Product();
                currentProduct.setProductID(rs.getInt("product_id"));
                currentProduct.setProductName(rs.getString("product_name"));

                Blob blob = rs.getBlob("product_image");
                InputStream ins = blob.getBinaryStream();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                byte[] byt = new byte[4096];
                int byteread = -1;
                while ((byteread = ins.read(byt)) != -1) {
                    os.write(byt, 0, byteread);
                }
                byte[] imageByte = os.toByteArray();
                String image = Base64.getEncoder().encodeToString(imageByte);
                currentProduct.setProductImage(image);

                currentProduct.setProductPrice(rs.getDouble("product_price"));
                currentProduct.setProductKeyword(rs.getString("product_keyword"));
                currentProduct.setProductDescription(rs.getString("product_description"));

                int buyQuantity = rs.getInt("quantity");
                currentProduct.setProductQuantity(buyQuantity);

                List<String> colors = new ArrayList<>();
                String colorsSql = "SELECT GROUP_CONCAT(DISTINCT color) AS colors FROM colors WHERE product_id = ?";
                PreparedStatement colorsPS = conn.prepareStatement(colorsSql);
                colorsPS.setInt(1, currentProduct.getProductID());
                ResultSet colorsRS = colorsPS.executeQuery();
                while (colorsRS.next()) {
                    colors.add(colorsRS.getString("colors"));
                }
                currentProduct.setColors(colors);

                List<String> sizes = new ArrayList<>();
                String sizesSql = "SELECT GROUP_CONCAT(DISTINCT size) AS sizes FROM sizes WHERE product_id = ?";
                PreparedStatement sizesPS = conn.prepareStatement(sizesSql);
                sizesPS.setInt(1, currentProduct.getProductID());
                ResultSet sizesRS = sizesPS.executeQuery();
                while (sizesRS.next()) {
                    sizes.add(sizesRS.getString("sizes"));
                }
                currentProduct.setSize(sizes);

                productList.add(currentProduct);
            }

            request.setAttribute("productList", productList);
            request.getRequestDispatcher("./View.jsp").forward(request, response);
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

