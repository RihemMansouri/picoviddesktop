/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.service;

import com.esprit.entity.Post;
import com.esprit.entity.User;
import com.esprit.iservice.Userservice;
import static com.esprit.service.Postserviceimpl.con;
import com.esprit.util.UserSession;
import com.esprit.util.dbconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaston
 */
public class UserserviceImp implements Userservice{
    
     static Connection con 
        = dbconnect.getConnection(); 
         ResultSet resultSet = null;

  

    @Override
    public int add(User emp) throws SQLException {
       String query 
            = "insert into users(nom, "
              + "password) VALUES (?, ?)"; 
        PreparedStatement ps 
            = con.prepareStatement(query); 
        ps.setString(1, emp.getUserName()); 
        ps.setString(2, emp.getPassword()); 
        int n = ps.executeUpdate(); 
        return n;  
    }

    @Override
    public String logIn(String user, String password) {
        String sql = "SELECT * FROM users Where nom = ? and password = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, password);
            resultSet = ps.executeQuery();
            if (!resultSet.next()) {

                return "Error";

            } else {
             
                
               

               do{
    String username = resultSet.getString("nom");
    String passwords = resultSet.getString("password");
    String firstname = resultSet.getString("firstname");
    String lastname = resultSet.getString("lastname");
    int ids = Integer.parseInt( resultSet.getString("id"));
                       System.out.println(username + "   "+ ids );
       UserSession.getInstace(username, ids);

    
 
  
    

    
}while (resultSet.next());
                return "Success";
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return "Exception";
        }
    }

    @Override
    public User getuserbyid(int id) {


        try {
                    String sql = "SELECT * FROM users Where id = "+id;

           Statement statement = con.createStatement();
                        
            System.out.println(sql);
            ResultSet result = statement.executeQuery(sql);
             int count = 0;
List<Post> lp = new ArrayList<>();
     User us = new User(); 

while (result.next()){
    String username = result.getString("nom");
    String password = result.getString("password");
    String firstname = result.getString("firstname");
    String lastname = result.getString("lastname");
    int ids = Integer.parseInt( result.getString("id"));
    
 
    String output = "User #%d: %s - %s - %s - %s";
    System.out.println(String.format(output, ++count, username, password, firstname, lastname));
    us.setFirstName(firstname);
    us.setLastName(lastname);
    us.setId(ids);
    us.setPassword(password);

            return us;

    
}

             
         } catch (SQLException ex) {
             Logger.getLogger(UserserviceImp.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;

    }
    
}
