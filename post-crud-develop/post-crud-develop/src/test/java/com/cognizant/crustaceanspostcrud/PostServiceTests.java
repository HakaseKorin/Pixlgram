package com.cognizant.crustaceanspostcrud;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import com.cognizant.crustaceanspostcrud.models.Post;
import com.cognizant.crustaceanspostcrud.repo.PostRepo;
import com.cognizant.crustaceanspostcrud.services.PostServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
class PostServiceTests {

    @Mock
    private PostRepo postRepo;
    
    @InjectMocks
    private PostServiceImpl postServiceImpl;

    @Test
    void testGetPostPageNull(){
        when(postRepo.findAll(any(Pageable.class))).thenReturn(null);
        List<Post> posts2 = postServiceImpl.getPostPage(1,5);
        assertEquals(null, posts2);
    }
    
    @Test
    void testGetPostPage(){
        List<Post> post = new LinkedList<>();
        post.add(new Post());
        post.add(new Post());
        post.add(new Post());
        post.add(new Post());
        post.add(new Post());

        Pageable pageable = PageRequest.of(0,5,Sort.by("createdOn").descending());
        
        Page<Post> temp = new PageImpl<>(post.subList(0,5),pageable, post.size());
        when(postRepo.findAll(any(Pageable.class))).thenReturn((Page<Post>) temp);
        List<Post> posts2 = postServiceImpl.getPostPage(1,5);
        assertNotNull(posts2);
    }
}
