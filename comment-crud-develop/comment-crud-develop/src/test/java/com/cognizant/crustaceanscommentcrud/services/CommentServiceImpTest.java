package com.cognizant.crustaceanscommentcrud.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import com.cognizant.crustaceanscommentcrud.models.Comment;
import com.cognizant.crustaceanscommentcrud.repo.CommentRepo;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

@SpringBootTest
class CommentServiceImpTest {

    @InjectMocks
    private CommentServiceImp commentServiceImp;

    @Mock
    private CommentRepo commentRepo;

    @Test
    void testGetCommentPage() {
        List<Comment> Comment = new LinkedList<>();
        Comment.add(new Comment());
        Comment.add(new Comment());
        Comment.add(new Comment());
        Comment.add(new Comment());
        Comment.add(new Comment());

        Pageable pageable = PageRequest.of(0, 5, Sort.by("createdOn").descending());

        Page<Comment> temp = new PageImpl<>(Comment.subList(0, 5), pageable, Comment.size());
        when(commentRepo.findByPostId(any(Integer.class), any(Pageable.class))).thenReturn((Page<Comment>) temp);
        Page<Comment> Comments2 = commentServiceImp.getCommentPage(1, 1, 5);
        assertNotNull(Comments2);
    }

    @Test
    void testNullGetCommentPage() {
        when(commentRepo.findByPostId(any(Integer.class), any(Pageable.class))).thenReturn(null);
        Page<Comment> Comments2 = commentServiceImp.getCommentPage(1, 1, 5);
        assertNull(Comments2);
    }

}
