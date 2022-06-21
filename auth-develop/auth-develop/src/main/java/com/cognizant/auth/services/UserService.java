package com.cognizant.auth.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="user", url="${settings.user_crud}")
public interface UserService {

    @PostMapping(value="/users/{username}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createUser(@PathVariable("username") String username);
}
