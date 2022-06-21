package com.cognizant.auth.services;


import com.cognizant.auth.models.Logout;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value="logout", url="${settings.auth_token}")
public interface LogoutService {

    //logout?redirect_uri=encodedRedirectUri
    @PostMapping(value = "/logout", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getLogout(@RequestBody Logout logout);

}
