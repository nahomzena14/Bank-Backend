package com.example.backend.controller;

import com.example.backend.services.UserService;
import com.example.backend.entity.User;
import com.example.backend.dto.UserDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling user-related operations.
 * Provides endpoints for retrieving and updating user information.
 */
@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    private final UserService userService;

    /**
     * Constructs a {@code UserController} with the required dependencies.
     *
     * @param userService the service handling user operations
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a user's details by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return a {@link ResponseEntity} containing the user's details as a {@link UserDTO}
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    /**
     * Updates a user's password or other details.
     *
     * @param userId      the ID of the user to update
     * @param updatedUser the updated user information
     * @return a {@link ResponseEntity} containing the updated user details as a {@link UserDTO}
     */
    @PostMapping("/{userId}/update")
    public ResponseEntity<UserDTO> updateUserPassword(@PathVariable Long userId, @RequestBody User updatedUser) {
        return userService.updateUser(userId, updatedUser);
    }
}
