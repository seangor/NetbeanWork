
package ict.test;

import ict.db.CustomerDB;


public class TestDeleteRecord {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ITP4511_DB";
        String username = "root";
        String password = "root";
        CustomerDB custDb = new CustomerDB(url, username, password);
        custDb.delRecord("2");
    }
}
