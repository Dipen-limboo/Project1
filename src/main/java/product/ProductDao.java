package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class ProductDao{
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourstore?useSSL=false", "root", "0564");
		} catch (SQLException e) {
			System.out.print("Message" + e.getMessage());
		} catch(ClassNotFoundException ce) {
			System.out.print("Message" + ce.getMessage());
		}
		return conn;
		}
	
//	public static Product editProduct(int id) {
//		Product pr = new Product();
//		try {
//			Connection conn = ProductDao.getConnection();
//			 String sql = "SELECT p.product_id, p.product_name, p.product_image, p.product_price, " +
//	                    "p.product_keyword, p.product_description, " +
//	                    "GROUP_CONCAT(DISTINCT pv.color ORDER BY pv.v_id SEPARATOR ', ') AS color, " +
//	                    "GROUP_CONCAT(DISTINCT pv.size ORDER BY pv.v_id SEPARATOR ', ') AS size " +
//	                    "FROM products p " +
//	                    "INNER JOIN product_variants pv ON p.product_id = pv.product_id " +
//	                    "WHERE p.product_id = ?";
//			 PreparedStatement ps = conn.prepareStatement(sql);
//			 ps.setInt(1, id);
//			 ResultSet rs = ps.executeQuery();
//			
//			 if(rs.next()) {
//				 pr.setProductID(rs.getInt(1));
//				 pr.setProductName(rs.getString(2));
//				 pr.setProductImage(rs.getString(3));
//				 pr.setProductPrice(rs.getDouble(4));
//				 pr.setProductKeyword(rs.getString(5));
//				 pr.setProductDescription(rs.getString(6));
//				 pr.setVariants(new ArrayList<>());
//				 String concatColor = rs.getString("color");
//				 String concatSize = rs.getString("size");
//				 String[] colorArray = concatColor.split(",");
//				 String[] sizeArray = concatSize.split(",");
//				 for (String cr: colorArray) {
//					 pr.getVariants().add(new ProductVarient("color", null));
//				 }
//				 for (String sz: sizeArray) {
//					 pr.getVariants().add(new ProductVarient(null, "size"));
//				 }
//			 }
//			 conn.close();
//		} catch (Exception e) {
//			System.out.println("message" + e.getMessage());
//			e.printStackTrace();
//		}
//		return pr;
//	}
}