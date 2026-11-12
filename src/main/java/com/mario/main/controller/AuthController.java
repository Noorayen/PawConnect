package com.mario.main.controller;

import com.mario.main.dto.LoginRequest;
import com.mario.main.dto.LoginResponse;
import com.mario.main.entity.User;
import com.mario.main.security.JwtService;
import com.mario.main.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService,
                          JwtService jwtService) {

        this.userService = userService;
        this.jwtService = jwtService;
    }


    // Sign In
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest) {

        // Verify email and password
        User user = userService.loginUser(loginRequest);

        // Generate JWT token
        String token = jwtService.generateToken(
                user.getEmail()
        );

        // Return token
        return ResponseEntity.ok(
                new LoginResponse(token)
        );
    }
}