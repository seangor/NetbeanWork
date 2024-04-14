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
public class OrderitemBean implements Serializable {

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getOrderid() {
        return orderid; 
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public OrderitemBean() {
    }

    private int eid;
    private int orderid;

}
