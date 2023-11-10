package user_profile_packag;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.User;
import Controller.UserDao;

@WebServlet("/updatePassword")
public class updatePassword extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        User uusr = (User) session.getAttribute("user");

        if (uusr != null) {
    	
    	int id = Integer.parseInt(request.getParameter("id"));
        String currentPsw = request.getParameter("cpsw");
        String newPsw = request.getParameter("npsw");
        String psw = request.getParameter("confpsw");

        Connection conn = null;
        PreparedStatement ps = null;
        int status = 0;

        try {
            conn = UserDao.getConnection();
            
            ps = conn.prepareStatement("SELECT psw FROM users WHERE id = ?");
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            String storedPassword = null;
            
            if (rs.next()) {
                storedPassword = rs.getString("psw");
            }
            
            if (storedPassword != null && storedPassword.equals(currentPsw)) {
                if (newPsw.equals(psw)) {
                    ps = conn.prepareStatement("UPDATE users SET psw = ? WHERE id = ?");
                    ps.setString(1, psw);
                    ps.setInt(2, id);
                    
                    status = ps.executeUpdate();
                    
                    if (status > 0) {
                        response.sendRedirect("profie");
                    } else {
                        response.sendRedirect("passwordChange.jsp");
                    }
                } else {
                    String message = "Password does not match";
                    request.setAttribute("match", message);
                    forwardToPasswordChangePage(request, response);
                }
            } else {
                String message = "Invalid password";
                request.setAttribute("invalid", message);
                forwardToPasswordChangePage(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        } else {
            response.sendRedirect("./login.jsp?source=cartServlet");
        }
    }

    private void forwardToPasswordChangePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("passwordChange.jsp?source=change");
        dispatcher.forward(request, response);
    }
}
