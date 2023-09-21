package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



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
}