package com.project.DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection
{
    private static final String url="jdbc:mysql://127.0.0.1:3306/project?useSSL=false";
    private static final String user="hbstudent";
    private static final String password="hbstudent";
    public static Connection getConnection()
    {
        Connection conn=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(url,user,password);
            System.out.println("Connection successful");
        }
        catch(Exception e)
        {
            System.out.println("Connection failed!");
        }
        return conn;
    }
}
