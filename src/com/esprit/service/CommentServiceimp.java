/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.service;

import com.esprit.entity.Comments;
import com.esprit.entity.Post;
import com.esprit.iservice.IcommentService;
import static com.esprit.service.Postserviceimpl.con;
import com.esprit.util.UserSession;
import com.esprit.util.dbconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaston
 */

public class CommentServiceimp implements IcommentService{
    
static Connection con 
        = dbconnect.getConnection(); 

    @Override
    public void ajouter(Comments c) {
      
        System.out.println(UserSession.getsession().getUserName() );
 // System.out.println("addcommentkaklll"+us.getuserbyid(c.getId_user()));
          try {
             String sql = "INSERT INTO commentaire (id_user, idPost, contenu,nomUser,dateCom) VALUES (?, ?, ? , ?, ?)";
             
             PreparedStatement statement = con.prepareStatement(sql);
             statement.setString(1, String.valueOf(c.getId_user()));
             statement.setString(2, String.valueOf(c.getId_post()));
             statement.setString(3, c.getContent());
             
             statement.setString(4,UserSession.getsession().getUserName());
             
             Date dateobj = new Date();
             DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
             statement.setString(5, df.format(dateobj));

             

             
             int rowsInserted = statement.executeUpdate();
             if (rowsInserted > 0) {
                 System.out.println("A new comment was inserted successfully!");
                 
             }      
         } catch (SQLException ex) {
             Logger.getLogger(Postserviceimpl.class.getName()).log(Level.SEVERE, null, ex);
            
         }
         
    }

    @Override
    public void Modifier(Comments c) {
 try {
             String sql = "UPDATE commentaire SET  contenu =?  WHERE id="+c.getId()+";";
             
             PreparedStatement statement = con.prepareStatement(sql);
             statement.setString(1, c.getContent());
             //statement.setString(2, post.getContent());
             
             
             
             int rowsUpdated = statement.executeUpdate();
             if (rowsUpdated > 0) {
                 System.out.println("An existing comments was updated successfully!");
             }        } catch (SQLException ex) {
             Logger.getLogger(Postserviceimpl.class.getName()).log(Level.SEVERE, null, ex);
             }
             }

    @Override
    public List<Comments> Read(Post p) {
                List<Comments> lp = new ArrayList<>();
                System.out.println("in read comment ");

    try {
        System.out.println(p.getId()+"id_mta3_lpost");
        String sql = "SELECT * FROM commentaire WHERE idPost='"+p.getId()+"' ORDER BY id DESC";
        String sqla=  "SELECT * FROM `commentaire` WHERE `idPost` = '"+p.getId()+"' ORDER by id DESC ";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        
        int count = 0;
                                        System.out.println("before while loop read comment ");

        while (result.next()){
                                System.out.println("in while loop read comment ");

                String id_user = result.getString("id_user");
                String content = result.getString("contenu");
                String id_post = result.getString("idPost");
                //String date = result.getString("date");
                //LocalDate ld = LocalDate.parse( result.getString("date"));
                int id = Integer.parseInt( result.getString("id").toString());
                
                
                //String output = "User #%d: %s - %s - %s - %s -%s";
                System.out.println( id_user+""+ content+""+ id_post);
                Comments ca = new Comments();
                ca.setContent(content);
                ca.setId(id);
                ca.setId_post(Integer.parseInt(id_post));
                ca.setId_user(Integer.parseInt(id_user));
                // p.setDatep(ld);
                lp.add(ca);
                
            } 
            
            return lp;
        }
     catch (SQLException ex) {
        Logger.getLogger(CommentServiceimp.class.getName()).log(Level.SEVERE, null, ex);
    }
     return lp;
    }

    @Override
    public void Delete(Comments c) {
          System.out.println("i am in");
        try {
                    System.out.println("i am here");

             String sql = "DELETE FROM commentaire WHERE id= ?";
             
             PreparedStatement statement = con.prepareStatement(sql);
             statement.setString(1, String.valueOf(c.getId()) );
             
             int rowsDeleted = statement.executeUpdate();
             if (rowsDeleted > 0) {
                 System.out.println("A comment was deleted successfully!");
                
             }        } catch (SQLException ex) {
             Logger.getLogger(Postserviceimpl.class.getName()).log(Level.SEVERE, null, ex);
             
         }
         

    }
    
}
