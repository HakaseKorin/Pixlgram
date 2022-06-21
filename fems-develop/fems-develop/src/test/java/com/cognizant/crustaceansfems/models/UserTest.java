package com.cognizant.crustaceansfems.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final static int id = 0;
    private final static String username = "JohnDoe", profileImg = "profileImg.path";

    private User user;

    @BeforeEach
    public void init(){ user = new User(id,username,profileImg); }

    @Test
    public void testConstructor(){
        User test = new User(id,username,profileImg);

        assertEquals(id, test.getId());
        assertEquals(username, test.getUsername());
        assertEquals(profileImg, test.getProfileImg());
    }

    @Test
    public void testGetters(){
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(profileImg, user.getProfileImg());
    }

    @Test
    public void testSetters(){
        User test = new User();
        test.setId(id);
        test.setUsername(username);
        test.setProfileImg(profileImg);

        assertEquals(user.getId(), test.getId());
        assertEquals(user.getUsername(), test.getUsername());
        assertEquals(user.getProfileImg(), test.getProfileImg());
    }
}