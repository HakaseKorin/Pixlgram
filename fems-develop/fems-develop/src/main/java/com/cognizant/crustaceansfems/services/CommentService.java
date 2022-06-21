package com.cognizant.crustaceansfems.services;

import com.cognizant.crustaceansfems.models.Comment;
import com.cognizant.crustaceansfems.models.PageOfItems;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "CommentCrud", url = "${settings.comment_crud_origin}")
public interface CommentService {
    @GetMapping("/comments")
    PageOfItems<Comment> getAllCommentsByPostId(@RequestParam("postId") int postId, @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize);
}
