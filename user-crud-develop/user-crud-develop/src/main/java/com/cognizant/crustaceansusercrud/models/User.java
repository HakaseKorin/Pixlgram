package com.cognizant.crustaceansusercrud.models;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id", unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "profileImg")
    private String profileImg;

    public User() {
    }

    public User(int id, String username, String profileImg) {
        this.id = id;
        this.username = username;
        this.profileImg = profileImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
}
