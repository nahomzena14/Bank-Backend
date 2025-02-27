package com.example.backend.exceptions.userExceptions;

/**
 * Exception thrown when the provided username, password, or full name is not valid.
 * Extends {@link RuntimeException} to provide unchecked exception behavior.
 */
public class UserNamePasswordFullnameNotValidException extends RuntimeException {

    /**
     * Constructs a new {@code UserNamePasswordFullnameNotValidException} with a default message.
     * The default message is "Username, password, or full name provided is not valid".
     */
    public UserNamePasswordFullnameNotValidException(String message) {
        super(message);
    }
}
