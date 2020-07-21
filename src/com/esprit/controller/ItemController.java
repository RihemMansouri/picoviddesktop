/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.entity.Post;
import com.esprit.service.Postserviceimpl;
import com.esprit.util.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gaston
 */
public class ItemController implements Initializable {
    @FXML 
    private Label showcmnt; 
    
    
    
    @FXML
    private Label edit;
    @FXML
    private Label delete;
    @FXML
    private Label postitle;
    @FXML
    private Label username;
    @FXML
    private Label day;
    @FXML
    private Label month;
    @FXML
    private Label year;
    
    @FXML 
    private Label content;
    
    @FXML 
    private Label addcomment;
    
    private int postid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("av");
    }    
    
   

    public ItemController() {
    }

    
    public ItemController(String postitle, String username) {
        this.postitle.setText(postitle);
        this.username.setText(username);
    }
    
    public void setPostitle(String title){
        this.postitle.setText(title);
    
    }

    public Label getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username.setText(username); ;
    }

    public Label getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day.setText(day);
    }

    public Label getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month.setText(month);
    }

    public Label getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year.setText(year);
    }
    
    public void setcontent(String content){
        this.content.setWrapText(true);
    this.content.setText(content);
        
    }
    
    @FXML
    public void editpost(MouseEvent event) throws IOException{
        //System.out.println(this.postid);
    
         Stage stage = new Stage();
         FXMLLoader loader = new FXMLLoader(AddpostmodalController.class.getClassLoader().getResource("com/esprit/view/Addpostmodal.fxml"));
          Parent root = loader.load();
          AddpostmodalController controller = loader.getController();
          controller.setContentpost(this.content.getText());
          controller.setPosttitle(this.postitle.getText());
          System.out.println(this.postid+"post id edit");
          controller.setTargetid(this.postid);
          controller.setAddposti("Modifier");
    stage.setScene(new Scene(root));
    stage.setTitle("My modal window");
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(
        ((Node)event.getSource()).getScene().getWindow() );
    stage.show();
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
       this.postid = postid;
       
    }
    
    @FXML
    public void deletepost(MouseEvent event){
                  System.out.println("clicked!!!!");
                  
                  Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Confirmation");
                    alert.setContentText("Are you sure want to delete  this?");
                    
Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
     
                    Postserviceimpl ps = new Postserviceimpl(); 
                    Post p = new Post(); 
                   p.setId(this.postid);
                   ps.Remove(p);
} else {
    // ... user chose CANCEL or closed the dialog
}
                        
 
        
    }
    
    
    @FXML 
    public void showcommentmodal(MouseEvent event) throws IOException{
     Stage stage = new Stage();
          //Parent root = FXMLLoader.load(
        //AddcommentmodalController.class.getClassLoader().getResource("com/esprit/view/addcommentmodal.fxml"));
           FXMLLoader loader = new FXMLLoader(AddcommentmodalController.class.getClassLoader().getResource("com/esprit/view/addcommentmodal.fxml"));
                     Parent root = loader.load();

           AddcommentmodalController controller = loader.getController(); 
           System.out.println(this.postid+"testamouni"+UserSession.instance.getid());
           controller.setId_post(this.postid);
           System.out.println("//////////");
           controller.setId_user(UserSession.instance.getid());
          
     stage.setScene(new Scene(root));
     stage.setTitle("Ajouter un commentaire");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
        ((Node)event.getSource()).getScene().getWindow() );
    stage.show();
    
    }
    
    @FXML 
    public void showlistcmmodal(MouseEvent event) throws IOException{
    
        Stage stage = new Stage();
          //Parent root = FXMLLoader.load(
        //AddcommentmodalController.class.getClassLoader().getResource("com/esprit/view/addcommentmodal.fxml"));
           FXMLLoader loader = new FXMLLoader(Comment_listController.class.getClassLoader().getResource("com/esprit/view/comment_list.fxml"));
                     Parent root = loader.load();

           Comment_listController controller = loader.getController(); 
           System.out.println("this post id is ::::::::"+this.postid);
           controller.setPost_id(this.postid);
           
           
          
     stage.setScene(new Scene(root));
     stage.setTitle(" commentaires ");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
        ((Node)event.getSource()).getScene().getWindow() );
    stage.show();
    
    
    }
    
    
    
    
    
}
