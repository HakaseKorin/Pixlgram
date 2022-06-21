package com.cognizant.crustaceansfems.services;

import com.cognizant.crustaceansfems.models.User;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserService userService;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void getUserById(){
        User user = new User(1,"JohnDoe","profileImg.path");
        when(userService.getUserById(any(Integer.class))).thenReturn(user);
        assertEquals(user, userService.getUserById(1));
    }

}