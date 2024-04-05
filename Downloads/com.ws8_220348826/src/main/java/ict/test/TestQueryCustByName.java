/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.test;

import ict.bean.CustomerBean;
import ict.db.CustomerDB;
import java.util.ArrayList;

/**
 *
 * @author sean3
 */
public class TestQueryCustByName {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ITP4505_DB";
        String username = "root";
        String password = "root";
        CustomerDB custDb = new CustomerDB(url, username, password);
        ArrayList<CustomerBean> cbs = custDb.queryCustByName("Sean");
        for (int i = 0; i < cbs.size(); i++) {
            CustomerBean cb = cbs.get(i);
                        String id = cb.getCustId();
            String name = cb.getName();
            String tel = cb.getTel();
            int age = cb.getAge();
            System.out.println("ID: " + id + "\n");
            System.out.println("Name: " + name + "\n");
            System.out.println("Tel: " + tel + "\n");
            System.out.println("Age: " + age + "\n");
            System.out.println("========================================\n\n");
        }

        for (CustomerBean cb : cbs) {
            String id = cb.getCustId();
            String name = cb.getName();
            String tel = cb.getTel();
            int age = cb.getAge();
            System.out.println("ID: " + id + "\n");
            System.out.println("Name: " + name + "\n");
            System.out.println("Tel: " + tel + "\n");
            System.out.println("Age: " + age + "\n");
            System.out.println("========================================\n\n");

        }
    }
}
