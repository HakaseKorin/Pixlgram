/*
    Authors: Dominic Sieli, Ben Paser
    Last Edited: 04/18/2022
*/

package com.cognizant.crustaceansfems.controllers;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import com.cognizant.crustaceansfems.models.*;
import com.cognizant.crustaceansfems.services.CommentService;
import com.cognizant.crustaceansfems.services.ImgurService;
import com.cognizant.crustaceansfems.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cognizant.crustaceansfems.services.PostService;
import com.cognizant.crustaceansfems.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = { "${settings.cors_origin}", "${settings.test_origin}"})
@RequestMapping("/posts")
public class PostController {
    @Autowired
    public PostService postService;

    @Autowired
    public CommentService commentService;

    @Autowired
    public UserService userService;

    @Autowired
    public ImgurService imgurService;

    @Autowired
    public AuthUtils authUtils;

    @Value("${settings.issuer}")
    public String issuer;

    @Value("${settings.imgur_client_id}")
    String clientId;

    @GetMapping()
    public ResponseEntity<List<Post>> getAllPostsByPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        List<Post> posts = postService.getAllPostsByPage(pageNumber, pageSize);
        if(posts == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else{
            for(Post post:posts){
                User user = userService.getUserById(post.UserId());
                post.setUser(user);
            }
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }

        
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<PageOfItems<Comment>> getAllCommentsByPostId(@PathVariable int postId, @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        PageOfItems<Comment> comments = commentService.getAllCommentsByPostId(postId, pageNumber, pageSize);

        if(comments == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(comments, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Post> addPost(HttpServletRequest httpServletRequest, @RequestBody MultipartFile image, Integer userId, String description){
        try{
            //Token Verification before beginning imgur upload
            String token = httpServletRequest.getHeader("access_token");
            boolean valid = authUtils.validate(token,issuer);
            Post post = new Post();
            if(!valid){
                return new ResponseEntity<>(post, HttpStatus.FORBIDDEN);
            } else {
                //Imgur upload to get link
                String auth = "Client-ID " + clientId;
                Imgur result = imgurService.getImgurUrl(auth, image);

                //Add data to post object to send to PostCrud
                post.setUserId(userId);
                post.setDescription(description);
                post.setImg(result.getData().getLink());
                //Creating a date because we never pass this or set this anywhere else.
                post.setCreatedOn(new Date(System.currentTimeMillis()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

                //Send data to post crud with Access Token
                Post newPost = postService.addPost(post, token);

                //Return result and status.
                return new ResponseEntity<>(newPost, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}