package com.example.backend.dto;

import com.example.backend.entity.Transaction;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for representing transaction details.
 * This class is used to transfer transaction data without exposing the entity directly.
 */
@Data
public class TransactionDTO {

    private Long transaction_id;
    private String transactionType;
    private double amount;
    private LocalDateTime timeStamp;
    private Long fromAccountId;
    private Long toAccountId;

    /**
     * Constructs a {@code TransactionDTO} from a {@link Transaction} entity.
     *
     * @param transaction the transaction entity to be converted into a DTO
     */
    public TransactionDTO(Transaction transaction) {
        this.transaction_id = transaction.getTransactionId();
        this.transactionType = transaction.getTransactionType();
        this.amount = transaction.getAmount();
        this.timeStamp = transaction.getTimeStamp();
        this.fromAccountId = transaction.getFromAccount() != null ? transaction.getFromAccount().getId() : null;
        this.toAccountId = transaction.getToAccount() != null ? transaction.getToAccount().getId() : null;
    }

    /**
     * Constructs a {@code TransactionDTO} with specific transaction details.
     *
     * @param transactionId   the ID of the transaction
     * @param transactionType the type of transaction (e.g., deposit, withdrawal, transfer)
     * @param amount          the amount involved in the transaction
     * @param timeStamp       the timestamp of when the transaction occurred
     * @param fromAccountId   the ID of the account from which the transaction originated (nullable)
     * @param toAccountId     the ID of the account to which the transaction was made (nullable)
     */
    public TransactionDTO(Long transactionId, String transactionType, double amount, LocalDateTime timeStamp, Long fromAccountId, Long toAccountId) {
        this.transaction_id = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timeStamp = timeStamp;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
    }
}
