package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminValidation
 */
@WebServlet("/admin")
public class AdminValidation extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && isAdmin(user)) {
            response.sendRedirect("./admin.jsp");
        } else {
            response.sendRedirect("./login.jsp?source=adminValidation");
        }
    }

    private static boolean isAdmin(User user) {
        boolean isAdmin = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = UserDao.getConnection();
            String sql = "SELECT COUNT(*) AS user_count FROM users WHERE email = ? AND psw = ? AND role = 'admin'";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();

            if (rs.next()) {
                int userCount = rs.getInt("user_count");
                isAdmin = (userCount > 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return isAdmin;
    }
}
