/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.RequestBean;
import ict.bean.RequestitemBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sean3
 */
public class RequestDB {

    private String url = "";
    private String username = "";
    private String password = "";

    public RequestDB(String url, String username, String password) {
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

    public ArrayList<RequestBean> queryRequest() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection(); // Implement getConnection() method

            String query = "SELECT requestid, CreatedDate, uid, DeliveryDate, DeliveryTime, Status "
                    + "FROM request ORDER BY requestId DESC";
            pStmnt = cnnct.prepareStatement(query);

            ResultSet rs = pStmnt.executeQuery();

            ArrayList<RequestBean> list = new ArrayList<>();

            while (rs.next()) {
                RequestBean eb = new RequestBean();
                eb.setRequestId(rs.getInt(1));
                eb.setCreatedTime(rs.getString(2));
                eb.setUid(rs.getInt(3));
                eb.setDeliverdate(rs.getString(4));
                eb.setDelivertime(rs.getString(5));
                eb.setStatus(rs.getString(6));

                list.add(eb);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
        }
        return null;
    }

       public RequestBean queryRequestById(int requestid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection(); // Implement getConnection() method

            String query = "SELECT requestid, CreatedDate, uid, DeliveryDate, DeliveryTime, Status "
                    + "FROM request WHERE requestid = ? ORDER BY requestId DESC";
            pStmnt = cnnct.prepareStatement(query);
            pStmnt.setInt(1, requestid);

            ResultSet rs = pStmnt.executeQuery();
                RequestBean eb = new RequestBean();


            while (rs.next()) {
                eb.setRequestId(rs.getInt(1));
                eb.setCreatedTime(rs.getString(2));
                eb.setUid(rs.getInt(3));
                eb.setDeliverdate(rs.getString(4));
                eb.setDelivertime(rs.getString(5));
                eb.setStatus(rs.getString(6));
            }
            return eb;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
        }
        return null;
    }
    
    
    public ArrayList<RequestBean> queryRequestByUid(int uid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection(); // Implement getConnection() method

            String query = "SELECT requestid, CreatedDate, uid, DeliveryDate, DeliveryTime, Status "
                    + "FROM request WHERE uid = ? ORDER BY requestId DESC";
            pStmnt = cnnct.prepareStatement(query);
            pStmnt.setInt(1, uid);

            ResultSet rs = pStmnt.executeQuery();

            ArrayList<RequestBean> list = new ArrayList<>();

            while (rs.next()) {
                RequestBean eb = new RequestBean();
                eb.setRequestId(rs.getInt(1));
                eb.setCreatedTime(rs.getString(2));
                eb.setUid(rs.getInt(3));
                eb.setDeliverdate(rs.getString(4));
                eb.setDelivertime(rs.getString(5));
                eb.setStatus(rs.getString(6));

                list.add(eb);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
        }
        return null;
    }

    public boolean addRequest(int uid, String formattedDate, String formattedTime) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String status = "1";
            String preQueryStatement = "INSERT INTO Request (uid, DeliveryDate, deliveryTime, status, CreatedDate, ID_Flag) VALUES (?, ?, ?, ?, NOW(), 'X')";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, uid);
            pStmnt.setString(2, formattedDate);
            pStmnt.setString(3, formattedTime);
            pStmnt.setString(4, status);

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
    
        public ArrayList queryItemById(int requestid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String query = "SELECT requestId, requestitem.eid, Imgsrc, EName from requestitem Inner JOIN equipment ON equipment.Eid = requestitem.eid WHERE requestId = ?";
            pStmnt = cnnct.prepareStatement(query);
            pStmnt.setInt(1, requestid);

            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                RequestitemBean ob = new RequestitemBean();
                ob.setRequestid(rs.getInt(1));
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

    public int getIdByFlag() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        int requestId = 0;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT requestId FROM request WHERE ID_Flag = 'X'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet resultSet = pStmnt.executeQuery();

            if (resultSet.next()) {
                requestId = resultSet.getInt(1);
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
        return requestId;
    }

    public boolean addRequestItem(int requestId, int eid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO requestitem (requestId, eid) VALUES (?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setInt(1, requestId);
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
            String preQueryStatement = "UPDATE request SET ID_Flag = ''";
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
    
        public boolean apporveOrder(int requestid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE request SET status = '2' WHERE requestid = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, requestid);

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
        
        
        
}
