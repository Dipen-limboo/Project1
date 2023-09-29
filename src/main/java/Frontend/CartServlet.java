package Frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

@WebServlet("/cartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("html/text");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		User usr = (User)session.getAttribute("user");
		
		if(usr != null) {
			int productID = Integer.parseInt(request.getParameter("productID"));
	        int userID = Integer.parseInt(request.getParameter("userID"));
	        
	        int quantity = Integer.parseInt(request.getParameter("quantity"));
	        
	        
	        try {
				Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false", "root", "0564");
		        
		        //fetching the data of price from the product table
		        PreparedStatement pps = conn.prepareStatement("select product_price from products where product_id = ?");
		        pps.setInt(1, productID);
		        
		        ResultSet rs = pps.executeQuery();
		        double price = 0.00;
		        while (rs.next()) {
		        	price = rs.getDouble("product_price");
		        }
		        
		        Double total = price * quantity;
		        
		        PreparedStatement ps = conn.prepareStatement("insert into carts (user_id, product_id, quantity, total_price ) values (?, ?, ?, ?)");
		        ps.setInt(1, userID);
		        ps.setInt(2, productID);
		        ps.setInt(3, quantity);
		        ps.setDouble(4, total);
		        
		        int status = ps.executeUpdate();
		        
		        if(status > 0) {
		        	PreparedStatement pss = conn.prepareStatement("select c.cart_id, c.user_id, c.product_id, c.quantity, p.product_price,  c.total_price\r\n"
		        			+ "from carts as c join products p on c.product_id = p.product_id join users u on c.user_id = u.id ");
		        	List <Cart> list = new ArrayList<>();
		        	ResultSet res = pss.executeQuery();
		        	Cart cart = null;
		        	
		        	while (res.next()) {
		        		cart = new Cart();
		        		cart.setUser_id(res.getInt("user_id"));
		        		cart.setProduct_id(res.getInt("product_id"));
		        		cart.setQuantity(res.getInt("quantity"));
		        		cart.setTotalPrice(res.getDouble("total_price"));
		        		cart.setProduct_price(res.getInt("product_price"));
		        		list.add(cart);
		        	}
		        	request.setAttribute("cartList", list);
		        	request.getRequestDispatcher("./cart.jsp").forward(request, response);
		        }else {
		        	response.sendRedirect("./showProduct.jsp");
		        }
		        
			} catch (Exception e) {
				System.out.println("message" +e.getMessage());
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("./login.jsp?source=cartServlet");
		}
	}

}
