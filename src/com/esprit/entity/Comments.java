/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;

/**
 *
 * @author gaston
 */
public class Comments {
    private int id_user;
    private int id_post; 
    private int id; 
    private String content; 

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comments() {
    }

    public Comments(int id_user, int id_post, String content) {
        this.id_user = id_user;
        this.id_post = id_post;
        this.content = content;
    }
    
    
}
