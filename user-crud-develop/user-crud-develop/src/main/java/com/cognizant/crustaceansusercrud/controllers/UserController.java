package com.cognizant.crustaceansusercrud.controllers;

import java.util.List;

import com.cognizant.crustaceansusercrud.exceptions.UserAlreadyExistsException;
import com.cognizant.crustaceansusercrud.models.User;
import com.cognizant.crustaceansusercrud.services.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin( origins = {"${settings.cors_origin}"})
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        if(user==null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @PostMapping("/{username}")
    public ResponseEntity  addUser(@PathVariable String username) throws UserAlreadyExistsException {
       try {
           User newUser = userService.addUser(username);
           return new ResponseEntity<>(newUser, HttpStatus.CREATED);
       }
       catch(UserAlreadyExistsException userAlreadyExistsException ){
           return new ResponseEntity<>("User Already Exists", HttpStatus.CONFLICT);
       }    }



}
