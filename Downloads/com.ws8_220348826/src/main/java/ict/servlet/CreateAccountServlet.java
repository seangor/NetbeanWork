import ict.db.UserDB;
import ict.bean.UserBean;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class CreateAccountServlet extends HttpServlet {

    private UserDB userDB;

    @Override
    public void init() {
        String dbUrl = getServletContext().getInitParameter("dbUrl");
        String dbUser = getServletContext().getInitParameter("dbUser");
        String dbPassword = getServletContext().getInitParameter("dbPassword");
        userDB = new UserDB(dbUrl, dbUser, dbPassword);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单数据
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // 请在userDB.addUserInfo方法中处理密码加密
        String phoneNumber = request.getParameter("phone_number");
        String identity = request.getParameter("identity");
        String id = generateUniqueId(); // 假设这是一个生成唯一用户ID的方法

        // 检查用户名是否已存在
        if (userDB.isValidUser(username, password)) {
            request.setAttribute("error", "Username already exists.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // 尝试将用户信息添加到数据库
        boolean isAdded = userDB.addUserInfo(id, username, password, phoneNumber, identity);
        if (isAdded) {
            request.setAttribute("message", "Account successfully created!");
            request.getRequestDispatcher("./Login/Login.jsp").forward(request, response); // 跳转到登录页面或其他成功页面
        } else {
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    private String generateUniqueId() {
        // 实现您的逻辑来生成唯一的ID
        return "U" + System.currentTimeMillis(); // 简单示例
    }
}