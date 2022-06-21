package com.cognizant.crustaceansfems.services;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.cognizant.crustaceansfems.models.Comment;
import com.cognizant.crustaceansfems.models.PageOfItems;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    CommentService commentService;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    void getAllCommentsByPostId() {
        PageOfItems<Comment> comments = new PageOfItems<>();
        when(commentService.getAllCommentsByPostId(404, 1, 5)).thenReturn(comments);
        assertEquals(comments, commentService.getAllCommentsByPostId(404, 1, 5));
    }
}
