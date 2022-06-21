package com.cognizant.auth.controllers;

import com.cognizant.auth.models.AuthModel;
import com.cognizant.auth.services.LoginService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @InjectMocks
    LoginController loginController;

    @Mock
    LoginService loginService;

    @Mock
    AuthModel authModel;



    @Test
    void loginTest(){
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
        when(loginService.getToken(any(AuthModel.class))).thenReturn("hello");
        ResponseEntity<String> responseEntity = loginController.login("postman", "postman");
        Assert.assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }

    @Test
    void failedLoginTest(){
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
        when(loginService.getToken(any(AuthModel.class))).thenThrow();
        ResponseEntity<String> responseEntity = loginController.login("postman", "1");
        Assert.assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }
}
