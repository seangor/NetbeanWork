/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.EquipmentBean;
import ict.bean.RecordBean;
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
public class UserRecord {

    private String url = "";
    private String username = "";
    private String password = "";
    EquipmentDB Eqdb;

    public UserRecord(String url, String username, String password) {
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

    public boolean addBRecord(int uid, int eid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO BorrowRecord (eid, uid, borrowDate,deadline, status, ID_Flag) VALUES (?, ?, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), '1', 'X')";
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

    public ArrayList queryBRec() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT bid, borrowrecord.eid, ename, borrowdate, deadline, borrowRecord.status, equipment.imgsrc FROM borrowRecord "
                    + "INNER JOIN equipment ON borrowRecord.eid = equipment.eid ORDER BY bid ASC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList<RecordBean> list = new ArrayList<RecordBean>();

            while (rs.next()) {
                RecordBean rb = new RecordBean();
                rb.setBid(rs.getInt(1));
                rb.setEid(rs.getInt(2));
                rb.setEname(rs.getString(3));
                rb.setBorrowDate(rs.getString(4));
                rb.setDeadline(rs.getString(5));
                rb.setStatus(rs.getString(6));
                rb.setImgsrc(rs.getString(7));

                list.add(rb);
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

    public RecordBean queryBRecById(int bid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT bid, borrowrecord.eid, ename, borrowdate, deadline, borrowRecord.status, equipment.imgsrc FROM borrowRecord "
                    + "INNER JOIN equipment ON borrowRecord.eid = equipment.eid WHERE bid = ? ORDER BY bid ASC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, bid);
            ResultSet rs = pStmnt.executeQuery();
            RecordBean rb = new RecordBean();

            while (rs.next()) {
                rb.setBid(rs.getInt(1));
                rb.setEid(rs.getInt(2));
                rb.setEname(rs.getString(3));
                rb.setBorrowDate(rs.getString(4));
                rb.setDeadline(rs.getString(5));
                rb.setStatus(rs.getString(6));
                rb.setImgsrc(rs.getString(7));
            }
            return rb;
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

    public ArrayList queryBRecByStatus(String status) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT bid, borrowrecord.eid, ename, borrowdate, deadline, borrowRecord.status, equipment.imgsrc FROM borrowRecord "
                    + "INNER JOIN equipment ON borrowRecord.eid = equipment.eid WHERE borrowRecord.status = ? ORDER BY bid ASC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, status);
            ResultSet rs = pStmnt.executeQuery();
            ArrayList list = new ArrayList<RecordBean>();
            while (rs.next()) {
                RecordBean rb = new RecordBean();

                rb.setBid(rs.getInt(1));
                rb.setEid(rs.getInt(2));
                rb.setEname(rs.getString(3));
                rb.setBorrowDate(rs.getString(4));
                rb.setDeadline(rs.getString(5));
                rb.setStatus(rs.getString(6));
                rb.setImgsrc(rs.getString(7));
                list.add(rb);
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

    public void ReturnEquipment(int ebid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE borrowrecord SET status = '2' WHERE bid = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, ebid);
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

    public void UpdateStatus(String status, int bid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE borrowrecord SET status = ? WHERE bid = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, status);
            pStmnt.setInt(2, bid);

            pStmnt.executeUpdate();

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

    public ArrayList queryBRec_CourierOrder() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT bid, borrowrecord.eid, ename, borrowdate, deadline, borrowRecord.status \n"
                    + "FROM borrowRecord \n"
                    + "LEFT JOIN equipment ON borrowRecord.eid = equipment.eid\n"
                    + "WHERE borrowRecord.status = 'delivering' OR borrowRecord.status = 'finished'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList<RecordBean> list = new ArrayList<RecordBean>();

            while (rs.next()) {
                RecordBean rb = new RecordBean();
                rb.setBid(rs.getInt(1));
                rb.setEid(rs.getInt(2));
                rb.setEname(rs.getString(3));
                rb.setBorrowDate(rs.getString(4));
                rb.setDeadline(rs.getString(5));
                rb.setStatus(rs.getString(6));

                list.add(rb);
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
        int bid = 0;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT bid FROM borrowrecord WHERE ID_Flag = 'X'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet resultSet = pStmnt.executeQuery();

            if (resultSet.next()) {
                bid = resultSet.getInt("bid");
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
        return bid;
    }

    public void UpdateFlag() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE borrowRecord SET ID_Flag = ''";
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
}
//UPDATE eqorder SET Status = "delivering" WHERE OrderId = 43
