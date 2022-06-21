package com.cognizant.crustaceanspostcrud.services;

import java.util.List;
import com.cognizant.crustaceanspostcrud.models.Post;
import com.cognizant.crustaceanspostcrud.models.PostDTO;
import com.cognizant.crustaceanspostcrud.repo.PostRepo;
import com.cognizant.crustaceanspostcrud.services.Interface.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{    
    @Autowired
    public PostRepo postRepo;
    
    @Override
    public List<Post> getPostPage(int page,int size){
        Pageable pageable = PageRequest.of(page - 1,size,Sort.by("createdOn").descending());
        Page<Post> resultPage = postRepo.findAll(pageable);
        if(resultPage!=null&&resultPage.hasContent()) return resultPage.getContent();
        else return null;
    }

    @Override
    public Post addPost(Post post) {
        return postRepo.save(post);
    }
    
}
