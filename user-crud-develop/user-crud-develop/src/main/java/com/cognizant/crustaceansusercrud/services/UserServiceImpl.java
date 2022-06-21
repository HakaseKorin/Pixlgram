package com.cognizant.crustaceansusercrud.services;

import com.cognizant.crustaceansusercrud.exceptions.UserAlreadyExistsException;
import com.cognizant.crustaceansusercrud.models.User;
import com.cognizant.crustaceansusercrud.repo.UserRepo;
import com.cognizant.crustaceansusercrud.services.Interface.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    public UserRepo userRepo;

    @Override
    public User getUserById(int id) {
        User user = userRepo.getById(id);
        if(user!=null) return userRepo.getById(id);
        else return null;
    }

    @Override
    public User addUser(String username) {
        User user = new User();
        user.setUsername(username);
        User userDetails = userRepo.findByUsername(username);

        if (Objects.isNull(userDetails))
            return userRepo.save(user);
         else
             throw new UserAlreadyExistsException();
    }


}
