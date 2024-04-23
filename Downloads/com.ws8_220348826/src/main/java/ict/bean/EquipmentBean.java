  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;

import java.io.Serializable;

/**
 *
 * @author sean3
 */
public class EquipmentBean implements Serializable{
    private int Eid;
    private String EName;
    private String Estatus;
    private int quantity;
    private int wid;
    private String imgsrc;
    private int damagedQty;
    private int totalQty;
    private int borrowQty;
    private String campus;

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public int getBorrowQty() {
        return borrowQty;
    }

    public void setBorrowQty(int borrowQty) {
        this.borrowQty = borrowQty;
    }

    public int getDamagedQty() {
        return damagedQty;
    }

    public void setDamagedQty(int damagedQty) {
        this.damagedQty = damagedQty;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public int getWid() {
        return wid;
    }

    public EquipmentBean() {
    }
    
    public int getEid() {
        return Eid;
    }

    public void setEid(int Eid) {
        this.Eid = Eid;
    }

    public String getEName() {
        return EName;
    }

    public void setEName(String EName) {
        this.EName = EName;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
