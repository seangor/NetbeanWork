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
public class RecordBean implements Serializable {

    private int bid;
    private int eid;
    private String Ename;
    private String borrowDate;
    private String Deadline;

    public int getBid() {
        return bid;
    }

    public int getEid() {
        return eid;
    }

    public String getEname() {
        return Ename;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getDeadline() {
        return Deadline;
    }

    public RecordBean() {
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public void setEname(String Ename) {
        this.Ename = Ename;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setDeadline(String Deadline) {
        this.Deadline = Deadline;
    }

}
