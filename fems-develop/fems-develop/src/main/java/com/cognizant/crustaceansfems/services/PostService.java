/*
    Authors: Dominic Sieli, Ben Paser
    Last Edited: 04/15/2022
*/

package com.cognizant.crustaceansfems.services;

import java.util.List;

import com.cognizant.crustaceansfems.models.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="feign", url="${settings.crud_origin}")
public interface PostService {
    @GetMapping("/posts")
    List<Post> getAllPostsByPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize);

    @PostMapping("/posts/create")
    Post addPost(@RequestBody Post post, @RequestHeader(value="access_token") String token);
}


/*
    Microservice URLs

    comment-crud: 34.72.94.7
    fems: 35.223.84.44
    post-crud: 130.211.118.68
    ui: 34.133.41.15
    user-crud: 34.70.31.134
*/