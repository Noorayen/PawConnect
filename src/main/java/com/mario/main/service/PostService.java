package com.mario.main.service;

import com.mario.main.entity.Post;
import com.mario.main.entity.User;
import com.mario.main.repository.PostRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserService userService;


    public PostService(
            PostRepository postRepository,
            UserService userService) {

        this.postRepository = postRepository;
        this.userService = userService;
    }


    // Create a new post with image
    public Post createPost(
            String email,
            MultipartFile image,
            String caption) throws IOException {

        User user =
                userService.getUserByEmail(email);


        // Create uploads folder if it does not exist
        String uploadDirectory = "uploads";

        Path uploadPath =
                Paths.get(uploadDirectory);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }


        // Create a unique file name
        String originalFileName =
                image.getOriginalFilename();

        String fileName =
                UUID.randomUUID().toString()
                + "_"
                + originalFileName;


        // Save image
        Path filePath =
                uploadPath.resolve(fileName);

        Files.copy(
                image.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );


        // Create post
        Post post = new Post();

        post.setImageUrl(
                "/uploads/" + fileName
        );

        post.setCaption(caption);

        post.setCreatedAt(
                LocalDateTime.now()
        );

        post.setUser(user);


        return postRepository.save(post);
    }


    // Get all posts
    public List<Post> getAllPosts() {

        return postRepository.findAll();
    }
}