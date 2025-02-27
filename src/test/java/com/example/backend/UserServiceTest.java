package com.example.backend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.backend.dto.AuthenticationRequest;
import com.example.backend.dto.AuthenticationResponse;
import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.JwtService;
import com.example.backend.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        // Initialize a sample user before each test
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("encodedPassword");
    }

    @Test
    void testRegisterUser() {
        // Mock password encoding and user repository save operation
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Call the registerUser method
        User testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("password123");
        testUser.setFullName("Test User");

        UserDTO registeredUser = userService.registerUser(testUser).getBody();

        // Verify that the registered user is not null and has the expected username
        assertNotNull(registeredUser);
        assertEquals("testuser", registeredUser.getUserName());

        // Verify that the save method was called exactly once with any User instance,
        // ensuring that the user is being persisted to the database as expected.
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testAuthenticateUser() {
        // Mock user retrieval, password verification, and JWT token generation
        AuthenticationRequest request = new AuthenticationRequest("testuser", "password123");
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(jwtService.generateToken(any(User.class))).thenReturn("mockJwtToken");

        // Call the authenticate method
        AuthenticationResponse response = userService.authenticateUser(request).getBody();

        // Verify that the authentication response contains the expected JWT token
        assertNotNull(response);
        assertEquals("mockJwtToken", response.getToken());
    }


    @Test
    void testUpdateUser() {
        // Mock user retrieval and update operations

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        //Setup to be updated information
        String updateFullName = "new FullName";
        User updateUser = new User();
        updateUser.setFullName(updateFullName);
        updateUser.setPassword(user.getPassword());
        updateUser.setUsername(updateUser.getFullName());

        // Call the updateUser method
        UserDTO updatedUser = userService.updateUser(1L, updateUser).getBody();

        // Verify that the updated user contains the expected full name
        assertNotNull(updatedUser);
        assertEquals("new FullName", updatedUser.getFullName());
    }
}
