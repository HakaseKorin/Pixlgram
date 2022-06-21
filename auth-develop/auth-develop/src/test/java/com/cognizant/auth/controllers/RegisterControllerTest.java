package com.cognizant.auth.controllers;

import com.cognizant.auth.models.Admin;
import com.cognizant.auth.models.AuthModel;
import com.cognizant.auth.models.Logout;
import com.cognizant.auth.models.Register;
import com.cognizant.auth.services.LoginService;
import com.cognizant.auth.services.LogoutService;
import com.cognizant.auth.services.RegisterService;
import com.cognizant.auth.services.UserService;
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
class RegisterControllerTest {

    @InjectMocks
    RegisterController registerController;

    @Mock
    RegisterService registerService;

    @Mock
    UserService userService;

    @Mock
    LoginService loginService;

    @Mock
    LogoutService logoutService;

    @Mock
    Register register;

    @Mock
    AuthModel authModel;

    @Mock
    Logout logout;

    Admin admin = new Admin();

    @Test
    void testRegister(){
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
        when(loginService.getAdminToken(any(AuthModel.class))).thenReturn(admin);
        when(registerService.getRegister(any(String.class),any(Register.class))).thenReturn("User Registered");
        when(userService.createUser(any(String.class))).thenReturn("User Created");
        when(logoutService.getLogout(any(Logout.class))).thenReturn("Admin Logged out");
        ResponseEntity<String> responseEntity = registerController.register("username","password");
        Assert.assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }

    @Test
    void testFailedRegistration(){
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
        when(loginService.getAdminToken(any(AuthModel.class))).thenReturn(admin);
        when(registerService.getRegister(any(String.class),any(Register.class))).thenThrow(new RuntimeException());
        when(userService.createUser(any(String.class))).thenReturn("User Created");
        when(logoutService.getLogout(any(Logout.class))).thenReturn("Admin Logged out");
        ResponseEntity<String> responseEntity = registerController.register("username","password");
        Assert.assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }
}