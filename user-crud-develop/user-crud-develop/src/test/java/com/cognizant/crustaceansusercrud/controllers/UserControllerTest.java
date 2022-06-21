package com.cognizant.crustaceansusercrud.controllers;

import com.cognizant.crustaceansusercrud.exceptions.UserAlreadyExistsException;
import com.cognizant.crustaceansusercrud.models.User;
import com.cognizant.crustaceansusercrud.services.Interface.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    private User user = new User(1, "TempUser", "SomeImage");

    @Test
    public void testGetUserById(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(userService.getUserById(any(Integer.class))).thenReturn(new User());

        ResponseEntity<User> responseEntity = userController.getUserById(1);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
    }

    @Test
    public void testGetNull(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(userService.getUserById(any(Integer.class))).thenReturn(null);

        ResponseEntity<User> responseEntity = userController.getUserById(1);

        assertEquals(HttpStatus.NO_CONTENT.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void testCreateUser(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(userService.addUser(any(String.class))).thenReturn(user);
        ResponseEntity<User> responseEntity = userController.addUser("TempUser");
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.CREATED.value());
    }

    @Test
    public void testCreateExistingUser(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(userService.addUser(any(String.class))).thenThrow(new UserAlreadyExistsException());
        ResponseEntity<User> responseEntity = userController.addUser("TempUser");
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.CONFLICT.value());
    }

    
}