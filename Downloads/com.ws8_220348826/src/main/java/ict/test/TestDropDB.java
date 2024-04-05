/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.test;

import ict.db.CustomerDB;

public class TestDropDB {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ITP4505_DB";
        String username = "root";
        String password = "root";
        CustomerDB custDb = new CustomerDB(url, username, password);
        custDb.dropDatabase();
    }
}
