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


@WebServlet("/viewOrder")
public class viewOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");

        if (usr != null) {
		int id = (Integer)session.getAttribute("userId");
		
		
		try {
			Connection conn = UserDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("select o.order_id, o.order_date, p.product_image, p.product_keyword, p.product_name, os.quantity,\r\n"
					+ "o.state from order_details as os \r\n"
					+ "join orders o on o.order_id = os.order_id\r\n"
					+ "join products p on p.product_id = os.product_id\r\n"
					+ "where user_id = ? order by o.order_date desc;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			List <Orders> list = new ArrayList<>();
			while (rs.next()) {
				Orders order = new Orders();
				order.setOrderId(rs.getInt("order_id"));
				order.setDateOrder(rs.getDate("order_date"));
				order.setState(rs.getString("state"));
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
				System.out.println(rs.getString("product_name"));
				order.setQuantity(rs.getInt("quantity"));
				order.setKeyword(rs.getString("product_keyword"));
				list.add(order);
			}
			request.setAttribute("orderList", list);
			request.getRequestDispatcher("./userOrderPanel.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Message: " +e.getMessage());
			e.printStackTrace();
		}
	} else {
        response.sendRedirect("./login.jsp?source=cartServlet");
    }
	}
	
}
