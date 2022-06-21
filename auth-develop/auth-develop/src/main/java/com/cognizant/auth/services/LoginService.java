package com.cognizant.auth.services;


import com.cognizant.auth.models.Admin;
import com.cognizant.auth.models.AuthModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.*;

@FeignClient(value="feign", url="${settings.auth_token}")
public interface LoginService {

    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getToken(@RequestBody AuthModel authModel);

    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Admin getAdminToken(@RequestBody AuthModel authModel);

}
