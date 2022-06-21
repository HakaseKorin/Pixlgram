package com.cognizant.auth.controllers;

import com.cognizant.auth.models.Logout;
import com.cognizant.auth.services.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "${settings.cors_origin}")
@RequestMapping("/auth")
public class LogoutController {

    @Autowired
    private LogoutService logoutService;

    @Autowired
    private Logout logout;


    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<String> logout(String accessToken, @RequestParam("redirect_uri") String refreshToken) {
        try{
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("Authorization", accessToken);
            logout.setClient_id("crustaceans-auth");
            logout.setRefresh_token(refreshToken);
            String token = logoutService.getLogout(logout);
            return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("error", HttpStatus.CONFLICT);
        }



    }
}
