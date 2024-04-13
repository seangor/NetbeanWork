/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.RecordBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author sean3
 */
public class OrderDB {

    private String url = "";
    private String username = "";
    private String password = "";
    EquipmentDB Eqdb;

    public OrderDB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        Eqdb = new EquipmentDB(url, username, password);
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return DriverManager.getConnection(url, username, password);
    }

    public boolean addOrder(int uid, LocalDateTime selectedDateTime) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            Timestamp timestamp = Timestamp.valueOf(selectedDateTime);
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO `Order` (DeliveryDate, uid, CreatedDate, ID_Flag) VALUES (?, ?, NOW(), 'X')";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setTimestamp(1, timestamp);
            pStmnt.setInt(2, uid);

            int rowCount = pStmnt.executeUpdate();

            if (rowCount > 0) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return isSuccess;
    }

    public boolean addOrderItem(int orderid, int eid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO orderitem (OrderId, eid) VALUES (?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setInt(1, orderid);
            pStmnt.setInt(2, eid);

            int rowCount = pStmnt.executeUpdate();

            if (rowCount > 0) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return isSuccess;
    }

    public void UpdateFlag() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE `order` SET ID_Flag = ''";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            int rowCount = pStmnt.executeUpdate();

            if (rowCount > 0) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }

    public int getIdByFlag() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        int orderid = 0;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT OrderId FROM `order` WHERE ID_Flag = 'X'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet resultSet = pStmnt.executeQuery();

            if (resultSet.next()) {
                orderid = resultSet.getInt("OrderId");
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return orderid;
    }

}
