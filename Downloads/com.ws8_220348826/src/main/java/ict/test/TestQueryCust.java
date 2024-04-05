
package ict.test;

import ict.bean.CustomerBean;
import ict.bean.EquipmentBean;
import ict.db.CustomerDB;
import ict.db.EquipmentDB;
import java.util.ArrayList;


public class TestQueryCust {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/vtcit";
        String username = "root";
        String password = "root";
        EquipmentDB custDb = new EquipmentDB(url, username, password);
        ArrayList<EquipmentBean> cbs = custDb.queryEq();
        
        for (EquipmentBean cb : cbs) {
            int id = cb.getEid();
            String name = cb.getEName();
            String tel = cb.getEstatus();
            int age = cb.getQuantity();
            System.out.println("ID: " + id + "\n");
            System.out.println("Name: " + name + "\n");
            System.out.println("Tel: " + tel + "\n");
            System.out.println("Age: " + age + "\n");
            System.out.println("========================================\n\n");

        }
    }
}
