
package ict.test;

import ict.bean.CustomerBean;
import ict.db.CustomerDB;


public class TestDB3 {
    public static void main(String[] args) {
                String url = "jdbc:mysql://localhost:3306/ITP4505_DB";
        String username = "root";
        String password = "root";
        CustomerDB custDb = new CustomerDB(url, username, password);
        custDb.dropDatabase();
    }
}
