package com.cognizant.crustaceanscommentcrud.services.Interface;

import com.cognizant.crustaceanscommentcrud.models.Comment;
import org.springframework.data.domain.Page;

public interface CommentService {
    Page<Comment> getCommentPage(int postId, int page, int size);
    
}
