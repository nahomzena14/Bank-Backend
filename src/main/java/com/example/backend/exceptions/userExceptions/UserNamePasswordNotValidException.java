package com.example.backend.exceptions.userExceptions;

/**
 * Exception thrown when the provided username or password is not valid.
 * Extends {@link RuntimeException} to provide unchecked exception behavior.
 */
public class UserNamePasswordNotValidException extends RuntimeException {

    /**
     * Constructs a new {@code UserNamePasswordNotValidException} with a default message.
     * The default message is "Username or password provided is not valid".
     */
    public UserNamePasswordNotValidException() {
        super("Username or password provided is not valid");
    }
}
