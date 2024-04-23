
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = "默认用户"; // 默认用户名
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String dbURL = getServletContext().getInitParameter("dbUrl");
            String dbUser = getServletContext().getInitParameter("dbUser");
            String dbPassword = getServletContext().getInitParameter("dbPassword");

            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            String sql = "SELECT username FROM users WHERE id = 1"; // 假设我们获取ID为1的用户
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                username = rs.getString("username");
            }
        } catch (SQLException e) {
            throw new ServletException("Database access error", e);
        } finally {
            // 关闭资源
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("username", username);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user.jsp");
        dispatcher.forward(request, response);
    }
}
