package com.cognizant.crustaceansfems.services;


import com.cognizant.crustaceansfems.models.Imgur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value="imgur", url="${settings.imgur_upload}")
public interface ImgurService {

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Imgur getImgurUrl(@RequestHeader(value="Authorization") String authorization, @RequestBody MultipartFile image);

}
