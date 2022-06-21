package com.cognizant.auth.controllers;


import com.cognizant.auth.models.Logout;
import com.cognizant.auth.services.LogoutService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LogoutControllerTest {

    @InjectMocks
    LogoutController logoutController;

    @Mock
    LogoutService logoutService;

    @Mock
    Logout logout;

    @Test
    void failLogoutTest(){
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(httpServletRequest));
        when(logoutService.getLogout(any(Logout.class))).thenThrow();
        ResponseEntity<String> responseEntity = logoutController.logout("AOISDMASOICN", "Bearer");
    }

    @Test
    void logoutTest(){
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes((new ServletRequestAttributes((httpServletRequest))));
        when(logoutService.getLogout((any(Logout.class)))).thenReturn("200");
        ResponseEntity<String> responseEntity = logoutController.logout("CORRECTTOKEN", "CORRECTTOKEN");
    }


}
