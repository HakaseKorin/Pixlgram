package com.cognizant.crustaceanspostcrud.controllers;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cognizant.crustaceanspostcrud.models.Post;
import com.cognizant.crustaceanspostcrud.models.PostDTO;
import com.cognizant.crustaceanspostcrud.services.Interface.PostService;
import com.cognizant.crustaceanspostcrud.utils.AuthUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@CrossOrigin  (origins = {"${settings.cors_origin}"})
@RequestMapping("/posts")
public class PostController {

    @Value("${settings.issuer}")
    private String issuer;

    @Autowired
    public PostService postService;

    @Autowired
    public AuthUtils authUtils;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PostDTO>> getPostPage(@RequestParam("pageNumber") int page, @RequestParam("pageSize") int size){
        List<Post> post = postService.getPostPage(page, size);
        if(post==null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else{
            List<PostDTO> sendPost=new LinkedList<>();
            for(Post getPost:post){
                sendPost.add(getPost.toPostDTO());
            }
            return new ResponseEntity<>(sendPost, HttpStatus.OK);
        } 
    }

    @PostMapping("/create")
    public ResponseEntity<PostDTO> addPost(@RequestBody PostDTO postdto, HttpServletRequest req){
        String token = req.getHeader("access_token");        
        boolean valid=authUtils.validate(token, issuer);

        if(!valid) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        else{
            Post post = postdto.toPost();
            Post newPost = postService.addPost(post);
            PostDTO newPostDTO = newPost.toPostDTO();

            return new ResponseEntity<>(newPostDTO, HttpStatus.CREATED);
        }        
    }
}
