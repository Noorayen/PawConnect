package com.mario.main.controller;

import com.mario.main.entity.Post;
import com.mario.main.service.PostService;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;


    public PostController(PostService postService) {

        this.postService = postService;

    }


    // Create a new post with image and caption
    @PostMapping
    public ResponseEntity<Post> createPost(

            Authentication authentication,

            @RequestParam("image") MultipartFile image,

            @RequestParam(value = "caption",
                          required = false)
            String caption) throws IOException {

        String email = authentication.getName();

        Post post = postService.createPost(
                email,
                image,
                caption
        );

        return new ResponseEntity<>(
                post,
                HttpStatus.CREATED
        );
    }


    // Get all posts
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {

        return ResponseEntity.ok(
                postService.getAllPosts()
        );
    }
}