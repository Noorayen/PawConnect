package com.mario.main.service;

import com.mario.main.dto.LoginRequest;
import com.mario.main.dto.UpdateProfileRequest;
import com.mario.main.entity.User;
import com.mario.main.repository.UserRepository;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // Join PawConnect
    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        // Encrypt password
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepository.save(user);
    }


    // Sign In
    public User loginUser(LoginRequest loginRequest) {

        User user = userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // Check password
        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid password");
        }

        return user;
    }


    // Get user by email
    public User getUserByEmail(String email) {

        return userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }


    // Update user profile
    public User updateProfile(
            String email,
            UpdateProfileRequest request) {

        User user = getUserByEmail(email);

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBio(request.getBio());

        return userRepository.save(user);
    }


    // Get all users
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}