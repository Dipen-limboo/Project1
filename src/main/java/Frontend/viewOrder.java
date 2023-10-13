package Frontend;

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


@WebServlet("/viewOrder")
public class viewOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = (Integer)session.getAttribute("userId");
		System.out.println("userId: " +id);
		
		try {
			Connection conn = UserDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT os.order_id, o.order_date, p.product_image, p.product_name, c.quantity FROM order_details as os join orders o on os.order_id = o.order_id join products p on os.product_id = p.product_id join carts c on os.cart_id = c.cart_id join users u on os.user_id = u.id where os.user_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			List <Orders> list = new ArrayList<>();
			while (rs.next()) {
				Orders order = new Orders();
				order.setOrderId(rs.getInt("order_id"));
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
				order.setQuantity(rs.getInt("quantity"));
				list.add(order);
			}
			request.setAttribute("orderList", list);
			request.getRequestDispatcher("./OrderPanel.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Message: " +e.getMessage());
			e.printStackTrace();
		}
	}
	
}
