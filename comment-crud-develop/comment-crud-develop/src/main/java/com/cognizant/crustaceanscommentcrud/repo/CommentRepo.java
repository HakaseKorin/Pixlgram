package com.cognizant.crustaceanscommentcrud.repo;

import com.cognizant.crustaceanscommentcrud.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    Page<Comment> findByPostId(int postId, Pageable resultPage);
}
