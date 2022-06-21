package com.cognizant.crustaceanscommentcrud.models;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "postId",nullable = false)
    private int postId;
    
    @Column(name = "username",nullable = false)
    private String username;
    
    @Column(name = "body",nullable = false)
    private String body;
    
    @Column(name = "createdOn",nullable = false)
    private LocalDate createdOn;

    

    public Comment() {
    }

    public Comment(int id, int postId, String username, String body, LocalDate createdOn) {
        this.id = id;
        this.postId = postId;
        this.username = username;
        this.body = body;
        this.createdOn = createdOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }
    
}
