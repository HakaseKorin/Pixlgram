package com.cognizant.crustaceanspostcrud.models;

import javax.persistence.*;

import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "userId", nullable = false)
    @Column(name = "userId",nullable = false)
    private int userId;

    @Column(name = "img", columnDefinition = "TEXT")
    private String img;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "createdOn", nullable = false)
    private LocalDate createdOn;

    public Post() {}

    public Post(int id, int userId, String img, String description, LocalDate createdOn) {
        this.id = id;
        this.userId = userId;
        this.img = img;
        this.description = description;
        this.createdOn = createdOn;
    }

    public PostDTO toPostDTO(){
        return new PostDTO(id, userId, img, description, createdOn);
    }

}
