package com.cognizant.auth.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserService userService;

    @Test
    void testCreateUser(){
        when(userService.createUser(any(String.class))).thenReturn("201");
        assertEquals("201", userService.createUser("username"));
    }

    @Test
    void testUsernameIsTaken(){
        when(userService.createUser(any(String.class))).thenReturn("409");
        assertEquals("409", userService.createUser("taken"));
    }
}
