package com.cognizant.crustaceansusercrud.services.Interface;

import com.cognizant.crustaceansusercrud.exceptions.UserAlreadyExistsException;
import com.cognizant.crustaceansusercrud.models.User;
import com.cognizant.crustaceansusercrud.repo.UserRepo;
import com.cognizant.crustaceansusercrud.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetUserById(){
        User test = new User(1, "JohnDoe", "profileImg.path");

        when(userService.getUserById(any(Integer.class))).thenReturn(test);

        User user = userService.getUserById(1);

        assertEquals(test, user);
        assertNotNull(user);
    }

    @Test
    public void TestGettingNullUser(){
        when(userService.getUserById(any(Integer.class))).thenReturn(null);

        User user = userService.getUserById(1);

        assertNull(user);
    }

    @Test
    public void testCreateUser(){
        User test = new User(1, "JohnDoe", "profileImg.path");

        when(userRepo.findByUsername(any(String.class))).thenReturn(null);
        when(userRepo.save(any(User.class))).thenReturn(test);

        User user = userService.addUser("JohnDoe");

        assertEquals(test, user);
        assertNotNull(user);
    }

    @Test
    public void testCreateExistingUser(){
        User test = new User(1, "JohnDoe", "profileImg.path");

        when(userRepo.findByUsername(any(String.class))).thenReturn(test);
        assertThrows(UserAlreadyExistsException.class,()->userService.addUser("JohnDoe"));
    
    }


}