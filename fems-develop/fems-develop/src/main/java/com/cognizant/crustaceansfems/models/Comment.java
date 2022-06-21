package com.cognizant.crustaceansfems.models;

import java.time.LocalDate;

public class Comment {

    private int id;
    private int postId;
    private String username;
    private String body;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postId=" + postId +
                ", username='" + username + '\'' +
                ", body='" + body + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
