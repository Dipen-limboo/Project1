package cart_packag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

@WebServlet("/cartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html"); 

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");

        if (usr != null) {
            int productID = Integer.parseInt(request.getParameter("productID"));
            int userID = Integer.parseInt(request.getParameter("userID"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double netTotal =0;
            double ship = 0.00;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false",
                        "root", "0564");

                PreparedStatement pps = conn.prepareStatement("select product_price from products where product_id = ?");
                pps.setInt(1, productID);

                ResultSet rs = pps.executeQuery();
                double price = 0.00;
                while (rs.next()) {
                    price = rs.getDouble("product_price");
                    
                   
                }

                double total = price * quantity;
  
                PreparedStatement ps = conn.prepareStatement(
                        "insert into carts (user_id, product_id, total_price, quantity ) values ( ?, ?, ?, ?)");
                ps.setInt(1, userID);
                ps.setInt(2, productID);
                
                ps.setDouble(3, total);
                ps.setInt(4, quantity);

                int status = ps.executeUpdate();
                if(status> 0) { 
            	   PreparedStatement pss = conn.prepareStatement("insert into totals (user_id, nettotal, shipping) values (?, ?, ?)");
                   
                   pss.setInt(1, userID);
                   pss.setDouble(2, netTotal);
                   pss.setDouble(3,  ship);
                   int process = pss.executeUpdate();
                   if(process > 0) {
                	   response.sendRedirect("showCart");
	               } else {
		                    response.sendRedirect("./showProduct.jsp");
	               }
               }

            } catch (Exception e) {
                System.out.println("message" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("./login.jsp?source=cartServlet");
        }
    }
}
