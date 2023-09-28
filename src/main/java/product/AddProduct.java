package product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
@WebServlet("/add")
public class AddProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("html/jsp/text");
        PrintWriter out = response.getWriter();

        String pname = request.getParameter("product_name");
        String pimage = request.getParameter("product_image");
       
        double pprice = Double.parseDouble(request.getParameter("product_price"));
        String pkeyword = request.getParameter("product_keyword");
        String pdescription = request.getParameter("product_description");
        String colors = request.getParameter("color");
        String sizes = request.getParameter("size");
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false", "root", "0564");

            // Insert into the products table
            PreparedStatement ps = conn.prepareStatement(
                    "insert into products (product_name, product_image, product_price, product_keyword, product_description, color, size) values (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, pname);
            ps.setString(2, pimage);
            ps.setDouble(3, pprice);
            ps.setString(4, pkeyword);
            ps.setString(5, pdescription);
            ps.setString(6, colors);
            ps.setString(7, sizes);

            int status = ps.executeUpdate();
            if (status > 0) {
                out.print("Successfully Added!");
                response.sendRedirect("./admin.jsp");
            } else {
                out.print("Sorry, we cannot add");
                response.sendRedirect("./addProduct.jsp");
            }
        } catch (Exception e) {
            System.out.print("message" + e.getMessage());
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
