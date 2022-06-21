package com.cognizant.crustaceansusercrud.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {

    private static final int id=1;
    private static final String username = "JohnDoe", profileImg = "profileImg.path";

    private User user;

    @BeforeEach
    void init() { user = new User(id,username,profileImg); }

    @Test
    public void testUserConstructor(){
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

        assertEquals(test.getId(), user.getId());
        assertEquals(test.getUsername(), user.getUsername());
        assertEquals(test.getProfileImg(), user.getProfileImg());
    }

}