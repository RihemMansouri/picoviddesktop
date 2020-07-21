/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.service;

import com.esprit.entity.Post;
import com.esprit.iservice.Ipostservice;
import com.esprit.util.dbconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaston
 */
public class Postserviceimpl implements Ipostservice{
     static Connection con 
        = dbconnect.getConnection(); 
         ResultSet resultSet = null;


    @Override
    public int add(Post post) {
         try {
             String sql = "INSERT INTO posts (NomPost, Contenu, id_author, DatePost) VALUES (?, ?, ?, ?)";
             
             PreparedStatement statement = con.prepareStatement(sql);
             statement.setString(1, post.getTitle());
             statement.setString(2, post.getContent());
             statement.setString(3, post.getUserid());
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

             statement.setString(4, df.format(post.getDatep()));
             
             int rowsInserted = statement.executeUpdate();
             if (rowsInserted > 0) {
                 System.out.println("A new user was inserted successfully!");
                 return 1;
             }      
         } catch (SQLException ex) {
             Logger.getLogger(Postserviceimpl.class.getName()).log(Level.SEVERE, null, ex);
             return 0;
         }
         return 0;
    }

    @Override
    public Boolean update(Post post) {
         try {
             String sql = "UPDATE posts SET NomPost=?, Contenu=?  WHERE id="+post.getId()+";";
             
             PreparedStatement statement = con.prepareStatement(sql);
             statement.setString(1, post.getTitle());
             statement.setString(2, post.getContent());
             
             
             
             int rowsUpdated = statement.executeUpdate();
             if (rowsUpdated > 0) {
                 System.out.println("An existing user was updated successfully!");
                 return true;
             }        } catch (SQLException ex) {
             Logger.getLogger(Postserviceimpl.class.getName()).log(Level.SEVERE, null, ex);
                return false;
             }
         return false;
    }

    @Override
    public Boolean Remove(Post post) {
        System.out.println("D5al ya 7aj");
        try {
                    System.out.println("D5al D5al ya 7aj");

             String sql = "DELETE FROM posts WHERE id= ?";
             
             PreparedStatement statement = con.prepareStatement(sql);
             statement.setString(1, String.valueOf(post.getId()) );
             
             int rowsDeleted = statement.executeUpdate();
             if (rowsDeleted > 0) {
                 System.out.println("A user was deleted successfully!");
                 return true;
             }        } catch (SQLException ex) {
             Logger.getLogger(Postserviceimpl.class.getName()).log(Level.SEVERE, null, ex);
             return false;
         }
         return false;
    }

    @Override
    public List<Post> read() throws SQLException {
            String sql = "SELECT * FROM posts ORDER BY id DESC";
 
Statement statement = con.createStatement();
ResultSet result = statement.executeQuery(sql);
 
int count = 0;
List<Post> lp = new ArrayList<>();
 
while (result.next()){
    String title = result.getString("NomPost");
    String content = result.getString("Contenu");
    String userid = result.getString("id_author");
    String date = result.getString("DatePost");
    //LocalDate ld = LocalDate.parse( result.getString("date"));
    int id = Integer.parseInt( result.getString("id").toString());
    
 
    String output = "User #%d: %s - %s - %s - %s";
    System.out.println(String.format(output, ++count, title, content, userid,date));
    Post p = new Post();
    p.setContent(content);
    p.setId(id);
    p.setTitle(title);
    p.setUserid(userid);
   // p.setDatep(ld);
    lp.add(p);
    
    
}

return lp;


    }
    
}
