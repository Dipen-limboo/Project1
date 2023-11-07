package product;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Controller.User;
import Controller.UserDao;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet("/add")
public class AddProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");

        if (usr != null) {
        String pname = request.getParameter("product_name");
        Part file = request.getPart("product_image");
        InputStream ins = file.getInputStream();

        double pprice = Double.parseDouble(request.getParameter("product_price"));
        int quantity = Integer.parseInt(request.getParameter("product_quantity"));
        String pkeyword = request.getParameter("product_keyword").toUpperCase();
        String pdescription = request.getParameter("product_description");
        String[] colors = request.getParameterValues("color");
        
        String[] sizes = request.getParameterValues("size");
        String type= "buy";

        try (Connection conn = UserDao.getConnection()) {
            conn.setAutoCommit(false);

            // Insert into the products table
            String insertProductSQL = "INSERT INTO products (product_name, product_image, product_price, product_keyword, product_description) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertProductSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, pname);
                ps.setBlob(2, ins);
                ps.setDouble(3, pprice);
                ps.setString(4, pkeyword);
                ps.setString(5, pdescription);

                int status = ps.executeUpdate();

                if (status > 0) {
                    ResultSet generatedKeys = ps.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int product_id = generatedKeys.getInt(1);

                        String insertColorSQL = "INSERT INTO colors (product_id, color) VALUES (?, ?)";
                        try (PreparedStatement psss = conn.prepareStatement(insertColorSQL)) {
                            for (String color : colors) {
                                psss.setInt(1, product_id);
                                psss.setString(2, color);
                                psss.addBatch();
                            }
                            psss.executeBatch();
                        }

                        String insertSizeSQL = "INSERT INTO sizes (product_id, size) VALUES (?, ?)";
                        try (PreparedStatement prss = conn.prepareStatement(insertSizeSQL)) {
                            for (String size : sizes) {
                                prss.setInt(1, product_id);
                                prss.setString(2, size);
                                prss.addBatch();
                            }
                            prss.executeBatch();
                        }
                        
                        
                        int userId = (Integer) session.getAttribute("userId");
                        
                        String insertStockSQL = "INSERT INTO stocks (product_id, buyQuantity, type, user_id) VALUES (?, ?, ?)";
                        try (PreparedStatement prs = conn.prepareStatement(insertStockSQL)) {
                            prs.setInt(1, product_id);
                            prs.setInt(2, quantity);
                            prs.setString(3, type);
                            prs.setInt(4, userId);
                            status = prs.executeUpdate();

                            if (status > 0) {
                                conn.commit();
                                out.print("Successfully Added!");
                                response.sendRedirect("./admin.jsp");
                            } else {
                                conn.rollback();
                                out.print("Sorry, we cannot add");
                                response.sendRedirect("./addProduct.jsp");
                            }
                        }
                    } else {
                        conn.rollback();
                        out.print("Failed to retrieve the generated product ID.");
                        response.sendRedirect("./addProduct.jsp");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.print("An error occurred: " + e.getMessage());
            response.sendRedirect("./addProduct.jsp");
        }
        } else {
            response.sendRedirect("./login.jsp?source=cartServlet");
        }
    }
}
