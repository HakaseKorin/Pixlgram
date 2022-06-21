package com.cognizant.auth.services;

import com.cognizant.auth.models.Admin;
import com.cognizant.auth.models.Register;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletResponse;

@FeignClient(value="register", url="${settings.auth_admin_token}")
public interface RegisterService {

    @PostMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getRegister(@RequestHeader(value="Authorization") String accessToken, @RequestBody Register register);

}
