package com.example.backend.exceptions.accountExceptions;

/**
 * Exception thrown when an account does not have enough balance to perform an operation.
 * Extends {@link RuntimeException} to provide unchecked exception behavior.
 */
public class InsufficientBalanceException extends RuntimeException {

    /**
     * Constructs a new {@code InsufficientBalanceException} with a default message.
     * The default message is "Insufficient balance".
     */
    public InsufficientBalanceException() {
        super("Insufficient balance");
    }
}
