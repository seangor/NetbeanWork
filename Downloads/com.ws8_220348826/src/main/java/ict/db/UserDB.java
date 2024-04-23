package ict.db;

import java.io.IOException;
import java.sql.*;

public class UserDB {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public UserDB(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            // 更好的异常处理
            throw new RuntimeException("MySQL driver not found", ex);
        }
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public boolean isValidUser(String user, String pwd) {
        Connection cnnct = null;
        boolean isValid = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM USER WHERE username=? and password=?";
            PreparedStatement stmnt = cnnct.prepareStatement(preQueryStatement);
            stmnt.setString(1, user);
            stmnt.setString(2, pwd);
            ResultSet rs = null;
            rs = stmnt.executeQuery();
            if (rs.next()) {
                isValid = true;
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        return isValid;
    }

    public void CreateUserInfoTable() throws IOException {
        Statement stmnt = null;
        Connection cnnct = null;
        ResultSet rs = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();

            DatabaseMetaData metaData = cnnct.getMetaData();
            rs = metaData.getTables(null, null, "userinfo", null);
            if (rs.next()) {
                System.out.println("Table already exists.");
                return;
            }
            String sql = "CREATE TABLE IF NOT EXISTS userinfo ("
                    + "Id varchar(5) NOT NULL,"
                    + "username varchar(25) NOT NULL,"
                    + "password varchar(25) NOT NULL"
                    + ")";
            stmnt.execute(sql);
            System.out.println("Table Customer created");
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
    }

    public void createUserInfoTable() {
        String sql = "CREATE TABLE IF NOT EXISTS userinfo ("
                + "Id varchar(5) NOT NULL,"
                + "username varchar(25) NOT NULL,"
                + "password varchar(255) NOT NULL,"
                + "identity varchar(50) NOT NULL," // 添加身份列
                + "PRIMARY KEY (Id))";
        try (Connection cnnct = getConnection(); Statement stmnt = cnnct.createStatement()) {
            DatabaseMetaData dbMetaData = cnnct.getMetaData();
            try (ResultSet rs = dbMetaData.getTables(null, null, "userinfo", null)) {
                if (!rs.next()) {
                    stmnt.execute(sql);
                    System.out.println("Table userinfo created");
                } else {
                    System.out.println("Table already exists.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean addUserInfo(String id, String user, String pwd) {
        String preQueryStatement = "INSERT INTO userinfo VALUES (?, ?, ?)";
        try (Connection cnnct = getConnection(); PreparedStatement stmnt = cnnct.prepareStatement(preQueryStatement)) {
            stmnt.setString(1, id);
            stmnt.setString(2, user);
            stmnt.setString(3, pwd);
            int rowCount = stmnt.executeUpdate();
            if (rowCount > 0) {
                System.out.println(user + " has been added");
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public String getUserIdentity(String user, String pwd) {
        Connection cnnct = null;
        String identity = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT identity FROM user WHERE username=? AND password=?";
            PreparedStatement stmnt = cnnct.prepareStatement(preQueryStatement);
            stmnt.setString(1, user);
            stmnt.setString(2, pwd);
            ResultSet rs = null;
            rs = stmnt.executeQuery();
            if (rs.next()) {
                identity = rs.getString("identity");
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } finally {
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return identity;
    }
}
