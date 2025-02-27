package com.example.backend.exceptions.accountExceptions;

/**
 * Exception thrown when there is an issue during an account transfer operation.
 * Extends {@link RuntimeException} to provide unchecked exception behavior.
 */
public class AccountTransferException extends RuntimeException {

    /**
     * Constructs a new {@code AccountTransferException} with the specified detail message.
     *
     * @param message the detail message
     */
    public AccountTransferException(String message) {
        super(message);
    }
}
