package com.example.backend.services;

import com.example.backend.dto.AuthenticationRequest;
import com.example.backend.dto.AuthenticationResponse;
import com.example.backend.entity.User;
import com.example.backend.dto.UserDTO;
import com.example.backend.exceptions.userExceptions.UserNameException;
import com.example.backend.exceptions.userExceptions.UserNotFoundException;
import com.example.backend.repository.UserRepository;
import com.example.backend.validator.AuthenticationRequestValidator;
import com.example.backend.validator.UserValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service class handling business logic for user operations.
 * Manages user creation, retrieval, and updates while ensuring
 * data validation and security constraints.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Retrieves a user by their ID and converts to Data Transfer Object
     * @param userId The user's unique identifier
     * @return ResponseEntity with UserDTO
     * @throws UserNotFoundException if user doesn't exist
     */
    public ResponseEntity<UserDTO> getUser(Long userId) {
        User user = findUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new UserDTO(user));
    }

    /**
     * Helper method to fetch user by username and throw exception if not found
     * @param username The username of the user
     * @return Optional<User> if user exists
     * @throws UserNotFoundException if user doesn't exist
     */
    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    /**
     * Helper method to fetch user by userId and throw exception if not found
     * @param userId The unique user ID
     * @return User if found
     * @throws UserNotFoundException if user doesn't exist
     */
    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public ResponseEntity<UserDTO> registerUser(User user) {
        // Validate username, password, and full name format
        UserValidator.validator(user);

        // Check if username already exists
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));  // Encrypt password
            userRepository.save(user);
            String token = jwtService.generateToken(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(user));
        }

        throw new UserNameException();
    }

    @Transactional
    public ResponseEntity<AuthenticationResponse> authenticateUser(AuthenticationRequest request) {
        AuthenticationRequestValidator.validator(request);

        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = findUserByUsername(request.getUsername());
        String token = jwtService.generateToken(user);
        return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(token));
    }

    @Transactional
    public ResponseEntity<UserDTO> updateUser(Long userId, User updatedUser) {
        // Validate username, password, and full name format
        UserValidator.validator(updatedUser);

        User user = findUserById(userId);

        // Check if username is already taken
        Optional<User> existingUser = userRepository.findByUsername(updatedUser.getUsername());
        if (existingUser.isPresent() && !existingUser.get().getId().equals(userId)) {
            throw new UserNameException();
        }

        // Update user details
        user.setUsername(updatedUser.getUsername());
        if (updatedUser.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));  // Encrypt password only if it's provided
        }
        user.setFullName(updatedUser.getFullName());

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(new UserDTO(user));
    }
}
