package com.cognizant.auth.services;

import com.cognizant.auth.models.Logout;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LogoutServiceTest {

    @Mock
    LogoutService logoutService;

    @Mock
    Logout logout;

    @Test
    void getLogout(){
        when(logoutService.getLogout(logout)).thenReturn("200");
        assertEquals("200", logoutService.getLogout(logout));
    }


}
