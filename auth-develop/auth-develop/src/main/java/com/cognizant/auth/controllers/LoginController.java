package com.cognizant.auth.controllers;


import com.cognizant.auth.models.AuthModel;
import com.cognizant.auth.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"${settings.cors_origin}", "${settings.test_origin}"})
@RequestMapping("/auth")
public class LoginController {


    @Autowired
    public LoginService loginService;

    @Autowired
    public AuthModel authModel;


    @PostMapping("/login")
    public ResponseEntity<String> login(String username, String password) {
        try{
            authModel.setUsername(username);
            authModel.setPassword(password);
            authModel.setGrant_type("password");
            authModel.setClient_id("crustaceans-auth");
            String token = loginService.getToken(authModel);
            return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("error", HttpStatus.CONFLICT);
        }



    }
}
