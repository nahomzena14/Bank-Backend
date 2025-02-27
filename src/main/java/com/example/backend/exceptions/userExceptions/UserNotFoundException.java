package com.example.backend.exceptions.userExceptions;

/**
 * Exception thrown when a user is not found in the system.
 * Extends {@link RuntimeException} to provide unchecked exception behavior.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code UserNotFoundException} with a default message.
     * The default message is "User Not Found".
     */
    public UserNotFoundException() {
        super("User Not Found");
    }
}
