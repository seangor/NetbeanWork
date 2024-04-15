/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author sean3
 */
public class OrderBean implements Serializable {

    private String delivertime;
    private String deliverdate;
    private int uid;
    private int orderId;
    private String status;
    private String createdTime;
    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public OrderBean() {
    }

    public String getDelivertime() {
        return delivertime;
    }

    public void setDelivertime(String delivertime) {
        this.delivertime = delivertime;
    }

    public String getDeliverdate() {
        return deliverdate;
    }

    public void setDeliverdate(String deliverdate) {
        this.deliverdate = deliverdate;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

}
