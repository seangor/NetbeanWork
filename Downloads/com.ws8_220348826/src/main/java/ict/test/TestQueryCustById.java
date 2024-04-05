
package ict.test;

import ict.bean.CustomerBean;
import ict.db.CustomerDB;
public class TestQueryCustById {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ITP4505_DB";
        String username = "root";
        String password = "root";
        CustomerDB custDb = new CustomerDB(url, username, password);
        CustomerBean cb = custDb.queryCustByID("7");
        
        String id = cb.getCustId();
        String name = cb.getName();
        String tel = cb.getTel();
        int age = cb.getAge();
        System.out.println("ID: " + id + "\n");
        System.out.println("Name: " + name + "\n");
        System.out.println("Tel: " + tel + "\n");
        System.out.println("Age: " + age + "\n");

    }
}
