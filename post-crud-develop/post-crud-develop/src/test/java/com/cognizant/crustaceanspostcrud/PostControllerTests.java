package com.cognizant.crustaceanspostcrud;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import com.cognizant.crustaceanspostcrud.controllers.PostController;
import com.cognizant.crustaceanspostcrud.models.Post;
import com.cognizant.crustaceanspostcrud.models.PostDTO;
import com.cognizant.crustaceanspostcrud.services.PostServiceImpl;

import com.cognizant.crustaceanspostcrud.utils.AuthUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@SpringBootTest
public class PostControllerTests {
    
	@InjectMocks
	PostController postController;

	@Mock
	PostServiceImpl postServiceImpl;

	@Mock
    AuthUtils authUtils;

	PostDTO postdDto = new PostDTO(1,101,"img1","desp", LocalDate.now());

	@Test
    void testGetPostsPage() {

        MockHttpServletRequest request = new MockHttpServletRequest();

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(postServiceImpl.getPostPage(any(Integer.class),any(Integer.class))).thenReturn(new LinkedList<>());

        ResponseEntity<List<PostDTO>> responseEntity = postController.getPostPage(0,5);

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());    

    }

	@Test
    void testGetPostsPageNull() {

        MockHttpServletRequest request = new MockHttpServletRequest();

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(postServiceImpl.getPostPage(any(Integer.class),any(Integer.class))).thenReturn(null);

        ResponseEntity<List<PostDTO>> responseEntity = postController.getPostPage(0,5);

        assertEquals(HttpStatus.NO_CONTENT.value(), responseEntity.getStatusCodeValue());    

    }
    @Test
    public void testAddPost(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("access_token", "");

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(postServiceImpl.addPost(any(Post.class))).thenReturn(postdDto.toPost());
        when(authUtils.validate(any(String.class),isNull())).thenReturn(true);

        ResponseEntity<PostDTO> responseEntity = postController.addPost(postdDto,request);
        assertEquals(HttpStatus.CREATED.value(),responseEntity.getStatusCode().value());
    }

    @Test
    public void testForbiddenAddPost(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("access_token", "");

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(postServiceImpl.addPost(any(Post.class))).thenReturn(postdDto.toPost());
        when(authUtils.validate(any(String.class),isNull())).thenReturn(false);

        ResponseEntity<PostDTO> responseEntity = postController.addPost(postdDto,request);
        assertEquals(HttpStatus.FORBIDDEN.value(),responseEntity.getStatusCode().value());
    }
}
