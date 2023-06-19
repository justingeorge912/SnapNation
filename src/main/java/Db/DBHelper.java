/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Justin
 */
public class DBHelper {
    private static Connection conn=null;
    static
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        try
        {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","Thunderbird@912");
        }
        catch (SQLException e)
{
            e.printStackTrace();
            System.exit(0);
        }
    }
    public static Connection getConnection()
    {
        return conn;
    }
}
