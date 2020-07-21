/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.iservice;

import com.esprit.entity.Post;
import com.esprit.entity.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gaston
 */
public interface Ipostservice {
        public int add(Post post);
        public Boolean update(Post post); 
        public Boolean Remove (Post post); 
        public List<Post> read() throws SQLException;

}
