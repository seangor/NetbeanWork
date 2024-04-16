/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.OrderBean;
import ict.bean.OrderitemBean;
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

    public OrderDB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return DriverManager.getConnection(url, username, password);
    }

    public ArrayList queryOrder() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String query = "SELECT Orderid, CreatedDate, eqorder.uid, DeliveryDate, DeliveryTime, Status , tel "
                    + "FROM eqorder Inner JOIN user on eqorder.uid = user.uid "
                    + "ORDER BY CreatedDate ASC";
            pStmnt = cnnct.prepareStatement(query);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                OrderBean eb = new OrderBean();
                eb.setOrderId(rs.getInt(1));
                eb.setCreatedTime(rs.getString(2));
                eb.setUid(rs.getInt(3));
                eb.setDeliverdate(rs.getString(4));
                eb.setDelivertime(rs.getString(5));
                eb.setStatus(rs.getString(6));
                eb.setTel(rs.getString(7));
                list.add(eb);
            }
            return list;
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
        return null;
    }

    public ArrayList queryOrderByStatus(String status) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String query = "SELECT Orderid, CreatedDate, eqorder.uid, DeliveryDate, DeliveryTime, Status , tel "
                    + "FROM eqorder Inner JOIN user on eqorder.uid = user.uid WHERE status = ? "
                    + "ORDER BY CreatedDate ASC";
            pStmnt = cnnct.prepareStatement(query);

            pStmnt.setString(1, status);

            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                OrderBean eb = new OrderBean();
                eb.setOrderId(rs.getInt(1));
                eb.setCreatedTime(rs.getString(2));
                eb.setUid(rs.getInt(3));
                eb.setDeliverdate(rs.getString(4));
                eb.setDelivertime(rs.getString(5));
                eb.setStatus(rs.getString(6));
                eb.setTel(rs.getString(7));

                list.add(eb);
            }
            return list;
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
        return null;
    }
    
       public OrderBean queryOrderById(int orderid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String query = "SELECT Orderid, CreatedDate, eqorder.uid, DeliveryDate, DeliveryTime, Status , tel "
                    + "FROM eqorder Inner JOIN user on eqorder.uid = user.uid WHERE orderid = ? "
                    + "ORDER BY CreatedDate ASC";
            pStmnt = cnnct.prepareStatement(query);

            pStmnt.setInt(1, orderid);

            ResultSet rs = pStmnt.executeQuery();
            
                OrderBean eb = new OrderBean();

            while (rs.next()) {
                eb.setOrderId(rs.getInt(1));
                eb.setCreatedTime(rs.getString(2));
                eb.setUid(rs.getInt(3));
                eb.setDeliverdate(rs.getString(4));
                eb.setDelivertime(rs.getString(5));
                eb.setStatus(rs.getString(6));
                eb.setTel(rs.getString(7));
            }
            return eb;
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
        return null;
    }

    public ArrayList queryItemById(int orderid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String query = "SELECT orderId, orderitem.eid, Imgsrc, EName from orderitem Inner JOIN equipment ON equipment.Eid = orderitem.eid WHERE orderId = ?";
            pStmnt = cnnct.prepareStatement(query);
            pStmnt.setInt(1, orderid);

            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                OrderitemBean ob = new OrderitemBean();
                ob.setOrderid(rs.getInt(1));
                ob.setEid(rs.getInt(2));
                ob.setImgsrc(rs.getString(3));
                ob.setEname(rs.getString(4));

                list.add(ob);
            }
            return list;
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
        return null;
    }

    public boolean addOrder(int uid, String formattedDate, String formattedTime, String type) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO eqorder (uid, DeliveryDate, deliveryTime, ID_Flag, status, CreatedDate, type) VALUES (?, ?, ?, 'X', 'In Progress', NOW(), ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, uid);
            pStmnt.setString(2, formattedDate);
            pStmnt.setString(3, formattedTime);
            pStmnt.setString(4, type);

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

    public boolean addBRecord(int uid, int eid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO BorrowRecord (eid, uid) VALUES (?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, eid);
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

    public boolean apporveOrder(int orderid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE eqorder SET status = 'approved' WHERE orderid = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, orderid);

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

    public boolean acceptDelivery(int orderid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE eqorder SET status = 'Delivering' WHERE orderid = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, orderid);

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

        public boolean finishDelivery(int orderid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE eqorder SET status = 'Received' WHERE orderid = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, orderid);

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
            String preQueryStatement = "UPDATE eqorder SET ID_Flag = ''";
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
            String preQueryStatement = "SELECT OrderId FROM eqorder WHERE ID_Flag = 'X'";
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
