package com.example.backend.controller;

import com.example.backend.services.UserService;
import com.example.backend.dto.AuthenticationRequest;
import com.example.backend.dto.AuthenticationResponse;
import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling authentication and user-related operations.
 * Provides endpoints for user registration, login, and updating user details.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    /**
     * Constructs an {@link AuthController} with the required dependencies.
     *
     * @param userService the service handling user operations
     */
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user.
     *
     * @param user the user information to register
     * @return a {@link ResponseEntity} containing the registered user details as a {@link UserDTO}
     */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    /**
     * Authenticates a user and returns a JWT token upon successful login.
     *
     * @param request the authentication request containing username and password
     * @return a {@link ResponseEntity} containing the authentication response with a JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return userService.authenticateUser(request);
    }

    /**
     * Updates an existing user's information.
     *
     * @param userId        the ID of the user to update
     * @param updateRequest the updated user details
     * @return a {@link ResponseEntity} containing the updated user details as a {@link UserDTO}
     */
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody User updateRequest) {
        return userService.updateUser(userId, updateRequest);
    }
}
