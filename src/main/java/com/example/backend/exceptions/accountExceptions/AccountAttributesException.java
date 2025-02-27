package com.example.backend.exceptions.accountExceptions;

/**
 * Exception thrown when there is an issue with account attributes.
 * Extends {@link RuntimeException} to provide unchecked exception behavior.
 */
public class AccountAttributesException extends RuntimeException {

    /**
     * Constructs a new {@code AccountAttributesException} with the specified detail message.
     *
     * @param message the detail message
     */
    public AccountAttributesException(String message) {
        super(message);
    }
}
