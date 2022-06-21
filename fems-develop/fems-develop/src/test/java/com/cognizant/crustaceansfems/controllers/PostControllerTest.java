/*
    Authors: Dominic Sieli, Ben Paser
    Last Edited: 04/18/2022
*/

package com.cognizant.crustaceansfems.controllers;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import com.cognizant.crustaceansfems.models.*;
import com.cognizant.crustaceansfems.services.CommentService;
import com.cognizant.crustaceansfems.services.ImgurService;
import com.cognizant.crustaceansfems.services.PostService;
import com.cognizant.crustaceansfems.services.UserService;

import com.cognizant.crustaceansfems.utils.AuthUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {
    @InjectMocks
    PostController postController;

    @Mock
    PostService postService;

    @Mock
    CommentService commentService;

    @Mock
    UserService userService;

    @Mock
    ImgurService imgurService;

    @Mock
    AuthUtils authUtils;

    @Test
    void getAllPostsByPageNull() {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
        when(postService.getAllPostsByPage(any(Integer.class),any(Integer.class))).thenReturn(new LinkedList<>());
        ResponseEntity<List<Post>> responseEntity = postController.getAllPostsByPage(0,10);
        Assert.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    void getAllPostsByPage() {

        List<Post> post = new LinkedList<>();
        post.add(new Post(1,2,"url","Description",LocalDate.now()));

        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
        when(postService.getAllPostsByPage(any(Integer.class),any(Integer.class))).thenReturn(post);
        when(userService.getUserById(any(Integer.class))).thenReturn(new User());

        ResponseEntity<List<Post>> responseEntity = postController.getAllPostsByPage(0,10);
        Assert.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }


    @Test
    void getAllCommentsByPostId() {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
        when(commentService.getAllCommentsByPostId(any(Integer.class),any(Integer.class),any(Integer.class))).thenReturn(new PageOfItems<>());
        ResponseEntity<PageOfItems<Comment>> responseEntity = postController.getAllCommentsByPostId(404,1,5);
        Assert.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }


    @Test
    void addPost() {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.addHeader("access_token", "AOISMDAOSIDM");
        Post post = new Post(1,2,"url","Description",LocalDate.now());
        User user = new User();
        user.setId(1);
        user.setUsername("Bob");
        user.setProfileImg("imageLink");
        Imgur imgur = new Imgur();
        Imgur.Data data = new Imgur.Data("link");
        imgur.setData(data);

        MultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Testing".getBytes());

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
        when(authUtils.validate(any(String.class), any())).thenReturn(true);
        when(postService.addPost(any(Post.class), any(String.class))).thenReturn(post);
        when(imgurService.getImgurUrl(any(String.class), any(MultipartFile.class))).thenReturn(imgur);

        ResponseEntity<Post> responseEntity = postController.addPost(mockHttpServletRequest,file,1, "Description");
        Assert.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    void addPostFailAuth() {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.addHeader("access_token", "AOISMDAOSIDM");
        MultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Testing".getBytes());
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
        when(authUtils.validate(any(String.class), any())).thenReturn(false);
        ResponseEntity<Post> responseEntity = postController.addPost(mockHttpServletRequest,file,1, "Description");
        Assert.assertEquals(HttpStatus.FORBIDDEN.value(), responseEntity.getStatusCodeValue());
    }


}
