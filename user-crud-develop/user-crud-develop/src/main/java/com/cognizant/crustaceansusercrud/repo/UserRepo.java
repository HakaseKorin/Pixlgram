package com.cognizant.crustaceansusercrud.repo;

import java.util.List;

import com.cognizant.crustaceansusercrud.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
    User getById(int id);
    User findByUsername(String username);

}
