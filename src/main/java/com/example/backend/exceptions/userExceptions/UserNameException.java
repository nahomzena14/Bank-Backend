package com.example.backend.exceptions.userExceptions;

/**
 * Exception thrown when the username is already taken.
 * Extends {@link RuntimeException} to provide unchecked exception behavior.
 */
public class UserNameException extends RuntimeException {

    /**
     * Constructs a new {@code UserNameException} with a default message.
     * The default message is "This username is already taken".
     */
    public UserNameException() {
        super("This username is already taken");
    }
}
