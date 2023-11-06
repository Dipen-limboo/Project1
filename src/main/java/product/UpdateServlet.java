package product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("product_name");
        String image = request.getParameter("product_image");
        String priceStr = request.getParameter("product_price");
        String keywords = request.getParameter("selected_keyword");
        String description = request.getParameter("product_description");
        String quantityStr = request.getParameter("product_quantity");
        String idStr = request.getParameter("product_id");


        String[] colors = {
            request.getParameter("color_red"),
            request.getParameter("color_blue"),
            request.getParameter("color_black"),
            request.getParameter("color_white"),
            request.getParameter("color_green"),
            request.getParameter("color_yellow"),
            request.getParameter("color_grey"),
            request.getParameter("color_pink"),
            request.getParameter("color_orange")
        };

        String[] size = {
            request.getParameter("size_small"),
            request.getParameter("size_large"),
            request.getParameter("size_double_extra_large"),
            request.getParameter("size_Medium"),
            request.getParameter("size_extra_large"),
            request.getParameter("size_triple_extra_large"),
            request.getParameter("size_35"),
            request.getParameter("size_36"),
            request.getParameter("size_37"),
            request.getParameter("size_38"),
            request.getParameter("size_39"),
            request.getParameter("size_40"),
            request.getParameter("size_41"),
            request.getParameter("size_42")
        };
        for (int i = 0; i< size.length; i++) {
        	System.out.println(size[i]);
        }
        double price = 0.0;
        int quantity = 0;
        int id = 0;

        if (priceStr != null && !priceStr.isEmpty()) {
            try {
                price = Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                e.printStackTrace(); 
            }
        }

        if (quantityStr != null && !quantityStr.isEmpty()) {
            try {
                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException e) {
                e.printStackTrace(); 
            }
        }

        if (idStr != null && !idStr.isEmpty()) {
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                
            }
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false", "root", "0564");

            ps = conn.prepareStatement("update products set product_name=?, product_image=?, product_price=?, product_keyword=?, product_description=? where product_id=?");
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setDouble(3, price);
            ps.setString(4, keywords);
            ps.setString(5, description);
            ps.setInt(6, id);
            int status = ps.executeUpdate();

            if (colors != null) {
                try (PreparedStatement pss = conn.prepareStatement("update colors set color = ? where product_id = ?");) {
                    for (int i = 0; i < colors.length; i++) {
                        pss.setString(1, colors[i]);
                        pss.setInt(2, id);
                        pss.addBatch();
                    }
                    pss.executeBatch();
                }
            }
               

            if (size != null) {
                try (PreparedStatement prs =  conn.prepareStatement("update sizes set size = ? where product_id = ?");) {
                    for (int i = 0; i < size.length; i++) {
                        prs.setString(1, size[i]);
                        prs.setInt(2, id);
                        prs.addBatch();
                    }
                    prs.executeBatch();
                }
            }
           
            
            ps = conn.prepareStatement("update stocks set buyQuantity = ? where product_id = ?");
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            status = ps.executeUpdate();

            if (status > 0) {
                out.print("Update successful");
                response.sendRedirect("ViewServlet");
            } else {
                out.print("Failed to update");
                request.getRequestDispatcher("editProduct.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.print("Error: " + e.getMessage());
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
