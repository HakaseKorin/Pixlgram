package com.cognizant.auth.controllers;

import com.cognizant.auth.models.Admin;
import com.cognizant.auth.models.AuthModel;
import com.cognizant.auth.models.Logout;
import com.cognizant.auth.models.Register;
import com.cognizant.auth.services.LoginService;
import com.cognizant.auth.services.LogoutService;
import com.cognizant.auth.services.RegisterService;
import com.cognizant.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = {"${settings.cors_origin}", "${settings.test_origin}"})
@RequestMapping("/auth")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private LogoutService logoutService;

    @Autowired
    private AuthModel authModel;

    @Autowired
    private Register register;

    @Autowired
    private Logout logout;

    @Autowired
    private Admin admin;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> register(String username, String password){
        try{
            //Login Admin
            authModel.setUsername("admin");
            authModel.setPassword("admin");
            authModel.setGrant_type("password");
            authModel.setClient_id("crustaceans-auth");
            admin = loginService.getAdminToken(authModel);

            //Submit User to UserCrud
            String createUser = userService.createUser(username);

            //Register User to KeyCloak
            String accessToken = "Bearer " + admin.getAccess_token();
            register.setUsername(username);
            register.setValue(password);
            String registerToken = registerService.getRegister(accessToken, register);

            //Login with new user
            authModel.setUsername(username);
            authModel.setPassword(password);
            String loginToken = loginService.getToken(authModel);


            return new ResponseEntity<>(loginToken, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("error", HttpStatus.CONFLICT);
        }finally {
            //Logout admin
            logout.setClient_id("crustaceans-auth");
            logout.setRefresh_token(admin.getRefresh_token());
            logout.setAccess_token(admin.getAccess_token());
            String logoutToken = logoutService.getLogout(logout);
        }
    }
}
