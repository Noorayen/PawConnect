package com.mario.main.controller;

import com.mario.main.dto.UpdateProfileRequest;
import com.mario.main.dto.UserProfileResponse;
import com.mario.main.entity.User;
import com.mario.main.service.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {

        this.userService = userService;

    }


    // Join PawConnect
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(
            @RequestBody User user) {

        User registeredUser =
                userService.registerUser(user);

        return new ResponseEntity<>(
                registeredUser,
                HttpStatus.CREATED
        );
    }


    // Get currently logged-in user
    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getCurrentUser(
            Authentication authentication) {

        String email = authentication.getName();

        User user =
                userService.getUserByEmail(email);

        UserProfileResponse response =
                new UserProfileResponse(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getBio(),
                        user.getRole().toString()
                );

        return ResponseEntity.ok(response);
    }


    // Update currently logged-in user's profile
    @PutMapping("/profile")
    public ResponseEntity<UserProfileResponse> updateProfile(
            Authentication authentication,
            @RequestBody UpdateProfileRequest request) {

        String email = authentication.getName();

        User user =
                userService.updateProfile(email, request);

        UserProfileResponse response =
                new UserProfileResponse(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getBio(),
                        user.getRole().toString()
                );

        return ResponseEntity.ok(response);
    }


    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }
}