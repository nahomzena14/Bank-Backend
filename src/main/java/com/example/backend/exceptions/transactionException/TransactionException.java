package com.example.backend.exceptions.transactionException;

/**
 * Exception thrown when there is an issue with a transaction.
 * Extends {@link RuntimeException} to provide unchecked exception behavior.
 */
public class TransactionException extends RuntimeException {

    /**
     * Constructs a new {@code TransactionException} with the specified detail message.
     *
     * @param message the detail message
     */
    public TransactionException(String message) {
        super(message);
    }
}
