package user_profile_packag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
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
import Controller.UserDao;
import Order_package.Orders;

@WebServlet("/review")
public class review extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");
        
        if (usr != null) {
		int id = (Integer) session.getAttribute("userId");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs= null;
		
		try {
			conn = UserDao.getConnection();
			ps = conn.prepareStatement("select o.order_date, p.product_id, p.product_image, p.product_name, od.user_id, od.orderDetails_id from order_details as od join orders o on od.order_id=o.order_id join products p on od.product_id = p.product_id where od.user_id=? ; ");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			Orders order = null;
			List <Orders> list = new ArrayList<>();
			while (rs.next()) {
				order = new Orders();
				order.setUserId(id);
				
				order.setOrderDetails_id(rs.getInt("orderDetails_id"));
				order.setDateOrder(rs.getDate("order_date"));
				Blob blob = rs.getBlob("product_image");
	 				InputStream ins = blob.getBinaryStream();
	                 ByteArrayOutputStream os = new ByteArrayOutputStream();
	                 byte [] byt = new byte[4096];
	                 int byteread = -1;
	                 while ((byteread = ins.read(byt))!= -1) {
	                 	os.write(byt, 0, byteread);
	                 }
	                 byte[] imageByte = os.toByteArray();
	                 String image = Base64.getEncoder().encodeToString(imageByte);
				order.setProductImage(image);
				order.setProductName(rs.getString("product_name"));
				list.add(order);
			}
			request.setAttribute("orist", list);
			request.getRequestDispatcher("./review.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Message: " +e.getMessage());
			e.printStackTrace();
		} 
        } else {
            response.sendRedirect("./login.jsp?source=cartServlet");
        }
	}
}
