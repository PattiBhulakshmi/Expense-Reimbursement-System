package com.ers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

    public Connection getConnection() throws Exception{
        String url="jdbc:mysql://localhost:3306/ers_rev_project0";
        String username="root";
        String password="root";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return  DriverManager.getConnection(url,username,password);
    }
}
