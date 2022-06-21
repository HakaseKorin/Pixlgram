package com.cognizant.crustaceansfems.services;

import com.cognizant.crustaceansfems.models.Imgur;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImgurServiceTest {

    @Mock
    ImgurService imgurService;

    @Test
    void getImgurUrlTest(){
        Imgur imgur = new Imgur();
        MultipartFile multipartFile = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Test File".getBytes());
        String string = "testString";
        when(imgurService.getImgurUrl(any(String.class) , any(MultipartFile.class))).thenReturn(imgur);
        assertEquals(imgurService.getImgurUrl(string, multipartFile), imgur);
    }

}
