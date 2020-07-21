/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.iservice;

import com.esprit.entity.Comments;
import com.esprit.entity.Post;
import java.util.List;

/**
 *
 * @author gaston
 */
public interface IcommentService {
    
    public void ajouter(Comments c); 
    public void Modifier(Comments c);
    public List<Comments> Read(Post p);
    public void Delete(Comments c);
    
}
