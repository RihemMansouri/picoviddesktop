/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.entity.Comments;
import com.esprit.service.CommentServiceimp;
import com.esprit.service.SendEmail;
import com.esprit.util.UserSession;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author gaston
 */
public class AddcommentmodalController implements Initializable {

    @FXML
    private Button addcbtn;
    @FXML
    private TextField cmnttext;
    @FXML
    private Label stat;

    private int id_post; 
    private int id_user;

    public TextField getCmnttext() {
        return cmnttext;
    }

    public void setCmnttext(TextField cmnttext) {
        this.cmnttext = cmnttext;
    }

    public Label getStat() {
        return stat;
    }

    public void setStat(Label stat) {
        this.stat = stat;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int post) {
        System.out.println("d5al!!!!!winoulid");
        this.id_post = post;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addcmnt(MouseEvent event) {
        CommentServiceimp csi = new CommentServiceimp(); 
        Comments cm = new Comments(); 
        cm.setId_user(this.id_user);
        cm.setId_post(this.id_post);
        cm.setContent(this.cmnttext.getText());
        csi.ajouter(cm);
        this.cmnttext.clear();
        this.stat.setTextFill(Color.CORAL);
        this.stat.setText("votre commentaire est ajouter");
        SendEmail.send("mansouri.ghassen@esprit.tn","230607$Ql","rihem.mansouri@esprit.tn",UserSession.getsession().getUserName(),"Comment added to your post"+cm.getContent());  

    }
    
}
