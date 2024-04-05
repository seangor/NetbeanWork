/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.test;

import ict.bean.CustomerBean;
import ict.db.CustomerDB;

public class TestEditRecord {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ITP4505_DB";
        String username = "root";
        String password = "root";
        CustomerDB custDb = new CustomerDB(url, username, password);
        CustomerBean cb = custDb.queryCustByID("3");
        System.out.println("Before");
        System.out.println("---------------------------------");
        String id = cb.getCustId();
        String name = cb.getName();
        String tel = cb.getTel();
        int age = cb.getAge();
        System.out.println("ID: " + id );
        System.out.println("Name: " + name);
        System.out.println("Tel: " + tel);
        System.out.println("Age: " + age);
        System.out.println("--------------------------------- \n");

        cb.setCustId("3");
        cb.setName("Nancy");
        cb.setTel("12345628");
        cb.setAge(28);
        custDb.editRecord(cb);
        cb = custDb.queryCustByID("1");
        System.out.println("After ");
        System.out.println("--------------------------------- ");
        id = cb.getCustId();  // Update the variables with the updated values
        name = cb.getName();
        tel = cb.getTel();
        age = cb.getAge();
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Tel: " + tel);
        System.out.println("Age: " + age );
        System.out.println("--------------------------------- \n");
    }
}
