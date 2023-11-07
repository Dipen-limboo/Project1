package Frontend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.User;
import Controller.UserDao;

@WebServlet("/check")
public class check extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");

        if (usr != null) {
		int id = (Integer) session.getAttribute("userId");
		
        try {
        	Connection conn = UserDao.getConnection();
        	PreparedStatement ps = conn.prepareStatement("select c.user_id, c.product_id, c.cart_id, u.fname, u.lname, u.email, c.total_price, c.quantity from carts as c join users u on c.user_id = u.id where c.user_id = ?");
        	ps.setInt(1,  id);
        	List <Cart> list = new ArrayList<>();
        	ResultSet rs = ps.executeQuery();
        	double sumtot = 0.0;
        	double vat = 0.0;
        	double netTot = 0.0;
        	while (rs.next()) {
        		Cart cart = new Cart();
        		cart.setUser_id(rs.getInt("user_id"));
        		cart.setCartId(rs.getInt("cart_id"));
        		cart.setProduct_id(rs.getInt("product_id"));
        		cart.setQuantity(rs.getInt("quantity"));
        		cart.setFirstname(rs.getString("fname"));
        		cart.setLastname(rs.getString("lname"));
        		cart.setEmail(rs.getString("email"));
        		cart.setTotalPrice(rs.getDouble("total_price"));
        		list.add(cart);
        		sumtot += cart.getTotalPrice();
        		vat = (sumtot * 13)/100;
        		netTot = sumtot + vat;

        	}
        	request.setAttribute("list", list);
        
        	request.setAttribute("total", sumtot);
        	request.setAttribute("vat", vat);
        	request.setAttribute("net", netTot);

        	request.getRequestDispatcher("order.jsp").forward(request, response);
        } catch (Exception e) {
        	System.out.println("message" +e.getMessage());
        	e.printStackTrace();
        }
	} else {
        response.sendRedirect("./login.jsp?source=cartServlet");
    }
	}
}
