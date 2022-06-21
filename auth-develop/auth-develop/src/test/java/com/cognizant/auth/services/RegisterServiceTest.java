package com.cognizant.auth.services;

import com.cognizant.auth.models.Register;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {

    @Mock
    RegisterService registerService;

    @Mock
    Register register;

    @Test
    void testRegister(){
        when(registerService.getRegister(any(),any(Register.class))).thenReturn("201");
        assertEquals("201", registerService.getRegister("username",register));
    }
}