/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.entity.Post;
import com.esprit.entity.User;
import com.esprit.service.Postserviceimpl;
import com.esprit.service.UserserviceImp;
import com.esprit.util.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author oXCToo
 */
public class HomeController implements Initializable {
    Postserviceimpl ps= new Postserviceimpl();
    UserserviceImp us = new UserserviceImp();
    
  @FXML 
  private Button refrech;
   
    
  @FXML
  private Button addpost;
  
    
      @FXML
    private VBox pnl_scroll;
    
    @FXML
    private void handleButtonAction(MouseEvent event) throws SQLException {        
       refreshNodes();
    }
    
    @FXML 
    private void showmodal(MouseEvent event ) throws IOException{
         Stage stage = new Stage();
          Parent root = FXMLLoader.load(
        AddpostmodalController.class.getClassLoader().getResource("com/esprit/view/Addpostmodal.fxml"));
          
    stage.setScene(new Scene(root));
    stage.setTitle("My modal window");
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(
        ((Node)event.getSource()).getScene().getWindow() );
    stage.show();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            refreshNodes();
                           // System.out.println("SessionTest :"+UserSession.getsession().getid());

        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void refreshNodes() throws SQLException
    { 
        
        
        
        System.out.println("1");
        List<Post> plist = ps.read();
        System.out.println(plist.size());
        pnl_scroll.getChildren().clear();
       
        Node [] nodes = new  Node[plist.size()];
        FXMLLoader loader =  new FXMLLoader(); 
        

                        System.out.println("1");

        for(int i = 0; i<plist.size(); i++)
        {
            try {
                System.out.println("2");
                 User user = us.getuserbyid(Integer.parseInt(plist.get(i).getUserid()));
                 System.out.println(user.getFirstName());
                FXMLLoader fxmlLoader =new  FXMLLoader(getClass().getClassLoader().getResource("com/esprit/view/Item.fxml"));
                nodes[i] = (Node) fxmlLoader.load();
                ItemController ip = fxmlLoader.getController();
           ip.setPostitle(plist.get(i).getTitle());
           ip.setUsername(user.getFirstName()+" "+user.getLastName());
           ip.setcontent(plist.get(i).getContent());
           ip.setPostid(plist.get(i).getId());

                
               
               
               // nodes[i] = (Node)FXMLLoader.load(getClass().getClassLoader().getResource("com/esprit/view/Item.fxml"));
                // ic = (FXMLLoader)node[i].
                pnl_scroll.getChildren().add(nodes[i]);
                
            } catch (IOException ex) {
                System.out.println(ex);
                //Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }  
    }
    
    @FXML 
    private void refreshnodes(MouseEvent event) throws SQLException{
        pnl_scroll.getChildren().clear();
        refreshNodes();
        
    
    }
    
    
    
}
