package ict.bean;

public class EquipmentBean {
    private int eid;
    private String eName;
    private String estatus;
    private int quantity;
    private int wid; // Assuming this is the wishlist ID

    // Constructors, getters, and setters for all fields

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEName() {
        return eName;
    }

    public void setEName(String eName) {
        this.eName = eName;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Add the missing getter and setter for the wishlist ID (wid)
    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }
}