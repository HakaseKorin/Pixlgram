package com.cognizant.crustaceanscommentcrud.controllers;

import com.cognizant.crustaceanscommentcrud.models.Comment;
import com.cognizant.crustaceanscommentcrud.models.PageOfItems;
import com.cognizant.crustaceanscommentcrud.services.Interface.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin (origins = {"${settings.cors_origin}"})
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<PageOfItems<Comment>> getCommentPage(@RequestParam("postId") int postId, @RequestParam("pageNumber") int page, @RequestParam("pageSize") int size){
        PageOfItems<Comment> comments = new PageOfItems<>(commentService.getCommentPage(postId, page, size));
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
