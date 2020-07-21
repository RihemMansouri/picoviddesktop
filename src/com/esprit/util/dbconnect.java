/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.util;

import com.mysql.jdbc.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gaston
 */
public class dbconnect {
     private static Connection con = null; 
  
    static
    { 
        String url = "jdbc:mysql://localhost:3306/pidevback"; 
        String user = "root"; 
        String pass = ""; 
        try { 
            Class.forName("com.mysql.jdbc.Driver"); 
            con = (Connection) DriverManager.getConnection(url, user, pass); 
        } 
        catch (ClassNotFoundException | SQLException e) { 
            e.printStackTrace(); 
        } 
    } 
    public static Connection getConnection() 
    { 
        return con; 
    } 
}
