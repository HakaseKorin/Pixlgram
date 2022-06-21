package com.cognizant.crustaceansusercrud.services.Interface;

import com.cognizant.crustaceansusercrud.exceptions.UserAlreadyExistsException;
import com.cognizant.crustaceansusercrud.models.User;

public interface UserService {

    public User getUserById(int id);
    public User addUser(String username) throws UserAlreadyExistsException;
}
