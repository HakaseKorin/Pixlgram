package com.cognizant.crustaceanspostcrud.repo;

import com.cognizant.crustaceanspostcrud.models.Post;
import com.cognizant.crustaceanspostcrud.models.PostDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    Page<Post> findAll(Pageable resultPage);
    
}
