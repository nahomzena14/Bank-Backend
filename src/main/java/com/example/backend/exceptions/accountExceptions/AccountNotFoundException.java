package com.example.backend.exceptions.accountExceptions;

/**
 * Exception thrown when an account is not found in the system.
 * Extends {@link RuntimeException} to provide unchecked exception behavior.
 */
public class AccountNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code AccountNotFoundException} with a default message.
     * The default message is "Account Not Found".
     */
    public AccountNotFoundException() {
        super("Account Not Found");
    }
}
