package com.cognizant.crustaceansfems.services;

import com.cognizant.crustaceansfems.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="user", url="${settings.user_crud_origin}")
public interface UserService {

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable("id") int id);
}
