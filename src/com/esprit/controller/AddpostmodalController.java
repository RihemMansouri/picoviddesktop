/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.entity.Post;
import com.esprit.service.Postserviceimpl;
import com.esprit.util.UserSession;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author gaston
 */
public class AddpostmodalController implements Initializable {

    @FXML
    private Button addposti;
    @FXML
    private Button cancel;
    @FXML
    private TextField posttitle;
    @FXML
    private TextArea contentpost;
    
    private int targetid;

    public Button getAddposti() {
        return addposti;
    }

    public void setAddposti(String addposti) {
        this.addposti.setText(addposti);
    }

    public int getTargetid() {
        return targetid;
    }

    public void setTargetid(int targetid) {
        this.targetid = targetid;
    }

    public TextField getPosttitle() {
        return posttitle;
    }

    public void setPosttitle(String posttitle) {
        this.posttitle.setText(posttitle);
    }

    public TextArea getContentpost() {
        return contentpost;
    }

    public void setContentpost(String contentpost) {
        this.contentpost.setText(contentpost); 
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterposte(MouseEvent event) {
        if(this.addposti.getText().equals("Modifier")){
            Postserviceimpl ps = new Postserviceimpl(); 
            Post p = new Post();
            p.setId(this.targetid);
            p.setContent(this.contentpost.getText());
            p.setTitle(this.posttitle.getText());
            System.out.println(this.targetid+"ohya3alem");
            ps.update(p);
        
        }else{
        Postserviceimpl ps = new Postserviceimpl(); 
        Post p = new Post();
        p.setTitle(posttitle.getText());
        p.setContent(contentpost.getText());
        System.out.println(contentpost.getText());
        p.setUserid(String.valueOf(UserSession.getsession().getid()));
        Date dateobj = new Date();
        p.setDatep(dateobj);
        ps.add(p);
        posttitle.clear();
        contentpost.clear();
    }
    }
    @FXML
    private void cancel(MouseEvent event) {
    }
    

}
