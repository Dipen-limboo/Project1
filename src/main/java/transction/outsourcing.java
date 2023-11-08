package transction;

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

import Controller.UserDao;
import product.Product;

@WebServlet("/outsourcing")
public class outsourcing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setContentType("text/html");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List <Product> list = new ArrayList<>();
		try {
		    conn = UserDao.getConnection();
		    ps = conn.prepareStatement("select p.product_image, p.product_name, s.product_id, s.quantity, s.type, s.date, u.fname, u.lname\r\n"
		    		+ "from stocks as s\r\n"
		    		+ "join products p on p.product_id = s.product_id\r\n"
		    		+ "join users u on u.id = s.user_id where s.type = 'sell'\r\n"
		    		+ "order by s.date desc; ");
		    rs = ps.executeQuery();
		    
		    while (rs.next()) {
		        Product pro = new Product(); 
		        
		        pro.setProductID(rs.getInt("product_id"));
		        
		        Blob blob = rs.getBlob("product_image");
		        InputStream ins = blob.getBinaryStream();
		        ByteArrayOutputStream os = new ByteArrayOutputStream();
		        byte[] byt = new byte[4096];
		        int byteread = -1;
		        
		        while ((byteread = ins.read(byt)) != -1) {
		            os.write(byt, 0, byteread);
		        }
		        
		        byte[] imageByte = os.toByteArray();
		        String image = Base64.getEncoder().encodeToString(imageByte);
		        
		        pro.setProductImage(image);
		        pro.setProductName(rs.getString("product_name"));
		        pro.setProductQuantity(rs.getInt("quantity"));
		        pro.setType(rs.getString("type"));
		        pro.setDateOrder(rs.getTimestamp("date"));
		        pro.setFirstname(rs.getString("fname"));
		        pro.setLastname(rs.getString("lname"));
		        
		        
		        list.add(pro);
		    }
		    
		    request.setAttribute("stocks", list);
		    request.getRequestDispatcher("outsourcing.jsp").forward(request, response);
		} catch (Exception e) {
		    System.out.print("message: " + e.getMessage());
		    e.printStackTrace();
		}
		
	}
	
}
