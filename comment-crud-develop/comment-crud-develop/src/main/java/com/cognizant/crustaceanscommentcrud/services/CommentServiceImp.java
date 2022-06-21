package com.cognizant.crustaceanscommentcrud.services;

import com.cognizant.crustaceanscommentcrud.models.Comment;
import com.cognizant.crustaceanscommentcrud.repo.CommentRepo;
import com.cognizant.crustaceanscommentcrud.services.Interface.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentRepo commentRepo;

    @Override
    public Page<Comment> getCommentPage(int postId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createdOn").descending());
        Page<Comment> resultPage = commentRepo.findByPostId(postId,pageable);
        if(resultPage!=null&&resultPage.hasContent()) return resultPage;
        else return null;
    }
}
