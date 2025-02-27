package com.example.backend.exceptions.accountExceptions;

/**
 * Exception thrown when a user already has a checking account and cannot create another one.
 * Extends {@link RuntimeException} to provide unchecked exception behavior.
 */
public class CheckingAccountException extends RuntimeException {

    /**
     * Constructs a new {@code CheckingAccountException} with a default message.
     * The default message is "User already has a checking account."
     */
    public CheckingAccountException() {
        super("User already has a checking account.");
    }
}
