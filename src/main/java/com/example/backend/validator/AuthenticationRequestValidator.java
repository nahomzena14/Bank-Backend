package com.example.backend.validator;

import com.example.backend.dto.AuthenticationRequest;
import com.example.backend.exceptions.userExceptions.UserNamePasswordNotValidException;

public class AuthenticationRequestValidator {

    /**
     * Validates the username and password from the authentication request.
     * Throws UserNamePasswordNotValidException if either field is empty or null.
     */
    public static void validator(AuthenticationRequest authenticationRequest) {

        String userName = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        // Check if username or password is null or empty after trimming
        if (userName == null || userName.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new UserNamePasswordNotValidException();
        }

    }
}
