package product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.UserDao;

@WebServlet("/remaining")
public class remaining extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String[] productId = request.getParameterValues("product_id");
        String[] quantity = request.getParameterValues("quantity");
        Connection conn = null;
        try {
            conn = UserDao.getConnection();
            for (int i = 0; i < productId.length; i++) {
                PreparedStatement ps = conn.prepareStatement("select quantity from stocks where type = 'buy' && product_id = ?");
                ps.setInt(1, Integer.parseInt(productId[i]));

                ResultSet rs = ps.executeQuery();
                int buyQuantity = 0;
                while (rs.next()) {
                    buyQuantity = rs.getInt("quantity");
                }
                int remaining = buyQuantity - Integer.parseInt(quantity[i]);

             
                PreparedStatement remainPs = conn.prepareStatement("update products set remain = ? where product_id = ?");
                remainPs.setInt(1, remaining);
                remainPs.setInt(2, Integer.parseInt(productId[i]));

                int status = remainPs.executeUpdate();
                if (status > 0) {
                    response.sendRedirect("SuccessfullyOrder.jsp");
                }
            }
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
