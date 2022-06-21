/*
    Authors: Dominic Sieli, Ben Paser
    Last Edited: 04/15/2022
*/

package com.cognizant.crustaceansfems.services;

import java.util.List;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cognizant.crustaceansfems.models.Post;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock
    PostService postService;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    void getAllPostsByPage() {
        List<Post> posts = new ArrayList<>();
        when(postService.getAllPostsByPage(0,0)).thenReturn(posts);
        assertEquals(posts, postService.getAllPostsByPage(0,0));
    }

    @Test
    void createPost(){
        Post post = new Post();
        String string = "String";
        when(postService.addPost(any(Post.class),any(String.class))).thenReturn(post);
        assertEquals(post, postService.addPost(post, string));
    }
}