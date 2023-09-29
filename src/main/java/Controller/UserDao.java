package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao {
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
	
	public static int save(User usr) {
		int status = 0;
		try {
			Connection conn = UserDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into users (fname, lname, email, psw) values (?, ?, ?, ?)");
			ps.setString(1, usr.getFname());
			ps.setString(2, usr.getLname());
			ps.setString(3, usr.getEmail());
			ps.setString(4, usr.getPassword());
			
			status = ps.executeUpdate();
			conn.close();
		} catch(Exception e) {
			System.out.print("meassage" +e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	public static boolean check(User usr) {
	    boolean userExists = false;
	    try {
	        Connection conn = UserDao.getConnection();
	        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) AS user_count FROM users WHERE email = ? AND psw = ?");
	        ps.setString(1, usr.getEmail());
	        ps.setString(2, usr.getPassword());
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            int userCount = rs.getInt("user_count");
	            // If userCount is greater than 0, a matching user exists
	            userExists = (userCount > 0);
	        }
	        conn.close();
	    } catch (Exception e) {
	        System.out.print("message" + e.getMessage());
	        e.printStackTrace();
	    }
	    return userExists;
	}
	
	public static boolean isAdmin(User usr) {
	    boolean isAdmin = false;
	    try {
	        Connection conn = UserDao.getConnection();
	        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) AS user_count FROM users WHERE email = ? AND psw = ? AND role = 'admin'");
	        ps.setString(1, usr.getEmail());
	        ps.setString(2, usr.getPassword());
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            int userCount = rs.getInt("user_count");
	            // If userCount is greater than 0, the user has the "admin" role
	            isAdmin = (userCount > 0);
	        }
	        conn.close();
	    } catch (Exception e) {
	        System.out.print("message" + e.getMessage());
	        e.printStackTrace();
	    }
	    return isAdmin;
	}

}