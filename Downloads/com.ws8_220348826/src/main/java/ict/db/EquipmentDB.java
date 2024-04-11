/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.EquipmentBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquipmentDB {

    private String url = "";
    private String username = "";
    private String password = "";

    public EquipmentDB(String url, String username, String password) {
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

    public ArrayList queryEq() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String query = "SELECT e.Eid, e.EName, e.EStatus, e.Quantity, w.wid, e.imgsrc "
                    + "FROM Equipment e "
                    + "LEFT JOIN Wishlist w ON w.eid = e.Eid";
            pStmnt = cnnct.prepareStatement(query);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                EquipmentBean eb = new EquipmentBean();
                eb.setEid(rs.getInt(1));
                eb.setEName(rs.getString(2));
                eb.setEstatus(rs.getString(3));
                eb.setQuantity(rs.getInt(4));
                eb.setWid(rs.getInt(5));
                eb.setImgsrc(rs.getString(6));
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

    public EquipmentBean queryEqById(int eid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT Eid, EName, EStatus, Quantity, imgsrc FROM Equipment WHERE eid = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, eid);
            ResultSet rs = pStmnt.executeQuery();
            EquipmentBean eb = new EquipmentBean();

            while (rs.next()) {
                eb.setEid(rs.getInt(1));
                eb.setEName(rs.getString(2));
                eb.setEstatus(rs.getString(3));
                eb.setQuantity(rs.getInt(4));
                eb.setImgsrc(rs.getString(5));
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

    public ArrayList<EquipmentBean> queryEqByName(String name) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        ArrayList<EquipmentBean> eList = new ArrayList<EquipmentBean>();

        try {
            cnnct = getConnection();
            String preStatement = "SELECT Eid, EName, EStatus, Quantity FROM Equipment WHERE EName LIKE ?";
            pStmnt = cnnct.prepareStatement(preStatement);
            pStmnt.setString(1, "%" + name + "%");
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                EquipmentBean eb = new EquipmentBean();
                eb.setEid(rs.getInt(1));
                eb.setEName(rs.getString(2));
                eb.setEstatus(rs.getString(3));
                eb.setQuantity(rs.getInt(4));
                eList.add(eb);
            }
            pStmnt.close();
            cnnct.close();
            return eList;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return eList;
    }

    public boolean borrowEquipment(int count, int eid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        count -= 1;
        if (count < 0) {
            return false;
        }
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE Equipment SET Quantity = ? WHERE eid = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, count);
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
        }

        return isSuccess;
    }

    public void checkStatus(int count, int eid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {

            cnnct = getConnection();
            String preQueryStatement = "";
            if (count == 0) {
                preQueryStatement = "UPDATE Equipment SET status = Unavailable WHERE eid = ?";
            } else if (count > 0) {
                preQueryStatement = "UPDATE Equipment SET status = Available WHERE eid = ?";
            }
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            int rowCount = pStmnt.executeUpdate();

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
