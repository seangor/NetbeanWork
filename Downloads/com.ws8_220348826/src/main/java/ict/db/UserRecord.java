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

    public ArrayList queryBRec() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT bid, borrowrecord.eid, ename, borrowdate, returndate FROM borrowRecord "
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

}
