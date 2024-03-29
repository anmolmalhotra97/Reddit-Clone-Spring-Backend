package com.example.springredditclone.controller;

import com.example.springredditclone.dto.PostResponse;
import com.example.springredditclone.security.JwtProvider;
import com.example.springredditclone.service.PostService;
import com.example.springredditclone.service.UserDetailsServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = PostController.class)
@ExtendWith(SpringExtension.class)
public class PostControllerTest {


    @MockBean
    //To mock interaction between post controller and post service we don't need to mock all the different dependencies
    //used by postService, instead use @MockBean which automatically injects all the necessary dependencies.
    private PostService postService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;
    @MockBean
    private JwtProvider jwtProvider;

    @Autowired
    //Allows us to call our rest Endpoint from our tests.
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should List all posts when making GET request to endpoint - /api/posts/")
    public void shouldListAllPost() throws Exception {

        PostResponse postRequest1 = new PostResponse(1L, "Post Name", "http://url.site", "Description", "User 1",
                "Subreddit Name", 0, 0, "1 day ago", false, false);
        PostResponse postRequest2 = new PostResponse(2L, "Post Name 2", "http://url2.site2", "Description2", "User 2",
                "Subreddit Name 2", 0, 0, "2 days ago", false, false);

        Mockito.when(postService.getAllPosts()).thenReturn(Arrays.asList(postRequest1, postRequest2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].postName", Matchers.is("Post Name")))
                .andExpect(jsonPath("$[0].url", Matchers.is("http://url.site")))
                .andExpect(jsonPath("$[1].url", Matchers.is("http://url2.site2")))
                .andExpect(jsonPath("$[1].postName", Matchers.is("Post Name 2")))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)));

    }
}
