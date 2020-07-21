/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.entity.Comments;
import com.esprit.service.CommentServiceimp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author gaston
 */
public class EditcommentController implements Initializable {
    @FXML 
   private Label notiflabel;
    @FXML
    private TextField comfiled;
    @FXML
    private Button editc;
    private int id_user; 
    private int id_post;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }
    private int id_comment; 

    public void setComfiled(String cc) {
        System.out.println("d5al lel comfiled"+cc);
        this.comfiled.setText(cc);
    }

    public int getId_comment() {
        return id_comment;
    }

    public void setId_comment(int id_comment) {
        this.id_comment = id_comment;
    }



   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void edit_com(MouseEvent event) {
         CommentServiceimp csi = new CommentServiceimp(); 
         Comments c = new Comments();
         c.setId(this.id_comment);
         c.setContent(this.comfiled.getText());
         c.setId_post(this.id_post);
         c.setId_user(this.id_user);
         csi.Modifier(c);
         this.notiflabel.setText("comment was updated successfully!");
         System.out.println(this.id_post+"this comment post id ");
         
         
    }
    
}
