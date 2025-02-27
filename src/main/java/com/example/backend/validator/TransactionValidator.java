package com.example.backend.validator;

import com.example.backend.exceptions.transactionException.TransactionException;

public class TransactionValidator {

    /**
     * Validates the transaction amount to ensure it is greater than zero.
     * Throws TransactionException if the amount is invalid.
     */
    public static void validate(Double amount) {

        // Check for null and invalid amount
        if (amount == null || amount <= 0) {
            throw new TransactionException("Transaction amount must be greater than zero.");
        }

    }
}
