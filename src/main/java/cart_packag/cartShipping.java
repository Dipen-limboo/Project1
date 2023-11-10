package cart_packag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

@WebServlet("/cartShipping")
public class cartShipping extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setContentType("text/html");

		
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		if (user != null) {
			int userID = (Integer)session.getAttribute("userId");
			double total = Double.parseDouble(request.getParameter("total"));
			double shipingCost = Integer.parseInt(request.getParameter("shipping"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false",
                    "root", "0564");
			PreparedStatement pss = conn.prepareStatement(
	                 "SELECT c.cart_id, c.user_id, c.quantity, c.product_id, p.product_price, p.product_image, p.product_name, p.product_keyword, c.total_price"
	                 + "	                         FROM carts AS c JOIN products p ON c.product_id = p.product_id JOIN users u ON c.user_id = u.id WHERE c.user_id = ?;");
	         pss.setInt(1, userID);
	         List<Cart> list = new ArrayList<>();
	         ResultSet res = pss.executeQuery();
	         double sumtotal = 0.0;
	         double netTotal =0.0;
	         while (res.next()) {
	             Cart cart = new Cart();
	             cart.setCartId(res.getInt("cart_id"));
	             cart.setUser_id(res.getInt("user_id"));
	             cart.setProduct_id(res.getInt("product_id"));
	             
	             cart.setTotalPrice(res.getDouble("total_price"));
	             cart.setProduct_price(res.getInt("product_price"));
	             cart.setProductKeyword(res.getString("product_keyword"));
	            
	             Blob blob = res.getBlob("product_image");
	             InputStream ins = blob.getBinaryStream();
                 ByteArrayOutputStream os = new ByteArrayOutputStream();
                 byte [] byt = new byte[4096];
                 int byteread = -1;
                 while ((byteread = ins.read(byt))!= -1) {
                 	os.write(byt, 0, byteread);
                 }
                 byte[] imageByte = os.toByteArray();
                 String image = Base64.getEncoder().encodeToString(imageByte);
                 
                 cart.setProductImage(image);
	             cart.setProduct_name(res.getString("product_name"));
	             cart.setQuantity(res.getInt("quantity"));
	             
	             list.add(cart);
	             sumtotal += cart.getTotalPrice();
	             netTotal = total + shipingCost;
	             
	             PreparedStatement netPs = conn.prepareStatement("update totals set nettotal = ?, shipping=? where user_id=?");
	             netPs.setDouble(1, netTotal);
	             netPs.setDouble(2, shipingCost);
	             netPs.setInt(3, userID);
	             int process = netPs.executeUpdate();
	             if(process > 0) {
	            	 System.out.println("Updated !!");
	             } else {
	            	 System.out.println("Failed to update");
	             }
	         }
	         
	         request.setAttribute("nettotal", netTotal);
	         request.setAttribute("cartList", list);
	         request.setAttribute("sumtotal", sumtotal);
	         request.getRequestDispatcher("./trialCart.jsp").forward(request, response);
	} catch (Exception e) {
		System.out.println("message" +e.getMessage());
		e.printStackTrace();
	}
		}else {
		response.sendRedirect("./login.jsp?source=cartServlet");
	} 
		
		
		
		
	}

}
