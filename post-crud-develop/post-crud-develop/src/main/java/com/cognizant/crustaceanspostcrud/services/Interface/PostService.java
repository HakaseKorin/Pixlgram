package com.cognizant.crustaceanspostcrud.services.Interface;

import java.util.List;
import com.cognizant.crustaceanspostcrud.models.Post;
import com.cognizant.crustaceanspostcrud.models.PostDTO;

public interface PostService {
    public List<Post> getPostPage(int page,int size);
    public Post addPost(Post post);

}
