package com.cognizant.auth.services;

import com.cognizant.auth.models.AuthModel;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    LoginService loginService;

    @Mock
    AuthModel authModel;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();


    @Test
    void getToken() {
        String message = "hello";
        when(loginService.getToken(authModel)).thenReturn("hello");
        assertEquals("hello", loginService.getToken(authModel));
    }

}