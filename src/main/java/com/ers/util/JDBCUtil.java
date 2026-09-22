package com.ers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static final String URL="jdbc:mysql://localhost:3306/ers_rev_project0";
    public static final String USERNAME="root";
    public static final String PASSWORD="root";
    public Connection getConnection() throws Exception{
       try{
           return DriverManager.getConnection(URL,USERNAME,PASSWORD);
       } catch (Exception e) {
          e.printStackTrace();
          return null;
       }
    }
}
