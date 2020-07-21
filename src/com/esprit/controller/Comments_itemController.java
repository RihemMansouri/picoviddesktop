/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.entity.Comments;
import com.esprit.entity.Post;
import com.esprit.service.CommentServiceimp;
import com.esprit.service.Postserviceimpl;
import com.esprit.service.UserserviceImp;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
public class Comments_itemController implements Initializable {

    @FXML
    private Label user;
    
    @FXML
    private Label comcom;
    
    @FXML
    private Label editlab;
    
    @FXML
    private Label deletecm;
    
    private String commentext;
    
    private int commentid ; 
    
    private int postid; 
    
    private int userid;

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }
    

    public String getCommentext() {
        return commentext;
    }

    public void setCommentext(String commentext) {
        System.out.println("commentext!!!!!!!!!!!"+commentext);
        this.commentext = commentext;
        System.out.println(this.commentext+"********");
        
        editcommenttext(commentext);
    }
    
    
    public void setCommentuser(int usernameid){
        UserserviceImp us = new UserserviceImp(); 
        this.user.setText(us.getuserbyid(usernameid).getFirstName()+""+us.getuserbyid(usernameid).getLastName());
    
    
    
    
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void edit_com(MouseEvent event) throws IOException {
        Stage stage = new Stage();
          //Parent root = FXMLLoader.load(
        //AddcommentmodalController.class.getClassLoader().getResource("com/esprit/view/addcommentmodal.fxml"));
           FXMLLoader loader = new FXMLLoader(EditcommentController.class.getClassLoader().getResource("com/esprit/view/editcomment.fxml"));
                                                     Parent root = loader.load();

           EditcommentController controller = loader.getController(); 

           System.out.println(this.commentext+"eli bech yetb3ath ll edit");
           controller.setComfiled(this.commentext);
           controller.setId_comment(this.commentid);
           controller.setId_post(this.postid);
           controller.setId_user(this.userid);
           
           
          
     stage.setScene(new Scene(root));
     stage.setTitle(" commentaires ");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
        ((Node)event.getSource()).getScene().getWindow() );
    stage.show();
    }

    @FXML
    private void deletecm(MouseEvent event) {
        
                 CommentServiceimp csi = new CommentServiceimp(); 
               
                 
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Confirmation");
                    alert.setContentText("Are you sure want to delete  this comment?");
                    
Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
     
                      Comments c = new Comments(); 
                 c.setId(this.getCommentid());
                 csi.Delete(c);
} else {
    // ... user chose CANCEL or closed the dialog
}

        
    }

    private void editcommenttext(String commentext) {
       
       this.comcom.setText(commentext);
    }
    
}
