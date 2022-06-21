package com.cognizant.crustaceanscommentcrud.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import com.cognizant.crustaceanscommentcrud.models.Comment;
import com.cognizant.crustaceanscommentcrud.models.PageOfItems;
import com.cognizant.crustaceanscommentcrud.services.CommentServiceImp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@SpringBootTest
class CommentControllerTest {
    @InjectMocks
    CommentController commentController;

    @Mock
    CommentServiceImp commentServiceImp;

    @Test
    void testGetCommentPage() {
        MockHttpServletRequest request = new MockHttpServletRequest();

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(commentServiceImp.getCommentPage(any(Integer.class), any(Integer.class), any(Integer.class)))
                .thenReturn(new PageImpl<>(new LinkedList<>()));

        ResponseEntity<PageOfItems<Comment>> responseEntity = commentController.getCommentPage(0, 0, 1);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
    }

}
