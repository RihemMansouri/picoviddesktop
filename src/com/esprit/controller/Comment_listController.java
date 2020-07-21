/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.entity.Comments;
import com.esprit.entity.Post;
import com.esprit.service.CommentServiceimp;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author gaston
 */
public class Comment_listController implements Initializable {

    @FXML
    private VBox scroll_p;
    
    private int post_id;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        System.out.println(post_id+"postid");
        this.post_id = post_id;
         refreshNodes();
       
            }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    public void refreshlist(){
        refreshNodes();
    }
    
    
    private void refreshNodes()
    {
        
         System.out.println("here"+this.post_id);
         CommentServiceimp csi = new CommentServiceimp();
         Post p = new Post(); 
         p.setId(this.post_id);
         List<Comments> lc = new ArrayList<>();
         lc=csi.Read(p);
         System.out.println(lc.size());

        //scroll_p.getChildren().clear();
        
        Node [] nodes = new  Node[lc.size()];
         FXMLLoader loader =  new FXMLLoader(); 

        
        for(int i = 0; i<lc.size(); i++)
        {
            try {
                loader =new  FXMLLoader(getClass().getClassLoader().getResource("com/esprit/view/comments_item.fxml"));
                nodes[i] = (Node) loader.load();
                Comments_itemController cl =(Comments_itemController) loader.getController();
                System.out.println(lc.get(i).getContent()+"///////////////////////////////");
                cl.setCommentext(lc.get(i).getContent());
                cl.setCommentuser(lc.get(i).getId_user());
                cl.setCommentid(lc.get(i).getId());
                cl.setPostid(lc.get(i).getId_post());
                cl.setUserid(lc.get(i).getId_user());
                
               scroll_p.getChildren().add(nodes[i]);
                
            } catch (IOException ex) {
                Logger.getLogger(Comment_listController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }  
    }
    
    
}
