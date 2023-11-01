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

import Controller.UserDao;


@WebServlet("/orderServlet")
public class orderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		try {
			Connection conn = UserDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from orders");
			ResultSet rs = ps.executeQuery();
			List <Orders> list = new ArrayList<>();
			while(rs.next()) {
				Orders order = new Orders();
				order.setOrderId(rs.getInt("order_id"));
				order.setDateOrder(rs.getDate("order_date"));
				order.setPhone(rs.getString("phone"));
				order.setLocation(rs.getString("Location"));
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