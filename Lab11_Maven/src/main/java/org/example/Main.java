package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/Lab10?autoReconnect=true&useSSL=false";
        String user="root";
        String passwordBD="qazwsx2005";
        Connection cn=null;
        try {

            cn= DriverManager.getConnection(url,user,passwordBD);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}