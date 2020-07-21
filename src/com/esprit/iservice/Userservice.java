/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.iservice;

import com.esprit.entity.User;
import java.sql.SQLException;

/**
 *
 * @author gaston
 */
public interface Userservice {
    public int add(User emp) 
        throws SQLException; 

    public String logIn(String user,String password);
    
    public User getuserbyid(int id);

}

