/*
    Authors: Dominic Sieli, Ben Paser
    Last Edited: 04/14/2022
*/

package com.cognizant.crustaceansfems.models;

import java.time.LocalDate;

public class Post {
    private int id;

    private int userId;

    private User user;

    private String img;

    private String description;

    private LocalDate createdOn;

    public Post() {
    }

    public Post(int id, int userId, String img, String description, LocalDate createdOn) {
        this.id = id;
        this.userId = userId;
        this.img = img;
        this.description = description;
        this.createdOn = createdOn;
    }

    //Change getUserId to UserId
    //The request look for get functions to add to the return statement if we don't include get 
    //it will not add it to the response 
    public int UserId() {
        return userId;
    }

    public int getUserId() { return userId; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}