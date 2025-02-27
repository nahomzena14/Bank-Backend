package com.example.backend.validator;

import com.example.backend.entity.User;
import com.example.backend.exceptions.userExceptions.UserNamePasswordFullnameNotValidException;

public class UserValidator {

    public UserValidator(){}

    /**
     * Validates the user's username, password, and full name.
     * Throws an exception if any of the fields are invalid.
     */
    public static void validator(User user) {

        String userName = user.getUsername();
        String password = user.getPassword();
        String fullName = user.getFullName();

        // Validate that none of the fields are empty
        if (userName.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
            throw new UserNamePasswordFullnameNotValidException("Username, Password, and Full Name must not be empty.");
        }

        // Additional validation rules (optional):
        // Validate username length
        if (userName.length() < 5) {
            throw new UserNamePasswordFullnameNotValidException("Username must be at least 5 characters long.");
        }

        // Validate password length and complexity (example: at least 8 characters, a digit, and a special character)
        if (password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*].*")) {
            throw new UserNamePasswordFullnameNotValidException("Password must be at least 8 characters long, contain a digit, and a special character.");
        }

        // Validate full name format (example: at least two words, first and last name)
        if (fullName.split(" ").length < 2) {
            throw new UserNamePasswordFullnameNotValidException("Full Name must contain at least a first and last name.");
        }
    }
}
