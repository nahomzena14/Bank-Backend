package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entity representing a transaction in the banking system.
 * A transaction can be a deposit, withdrawal, or transfer between accounts.
 */
@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    /** Unique identifier for the transaction. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    /** Type of transaction (e.g., "DEPOSIT", "WITHDRAWAL", "TRANSFER"). */
    @Column(name = "transaction_type")
    private String transactionType;

    /** The amount of money involved in the transaction. */
    @Column(name = "amount")
    private double amount;

    /** The timestamp when the transaction occurred. */
    @Column(name = "timestamp")
    private LocalDateTime timeStamp = LocalDateTime.now();

    /** The account from which money is withdrawn or transferred. */
    @ManyToOne
    @JoinColumn(name = "from_account_id")
    private Account fromAccount;

    /** The account to which money is deposited or transferred. */
    @ManyToOne
    @JoinColumn(name = "to_account_id")
    private Account toAccount;

    /** Default constructor. */
    public Transaction() {
    }

    /**
     * Constructs a {@code Transaction} with specified details.
     *
     * @param fromAccount   the account from which money is withdrawn or transferred
     * @param toAccount     the account to which money is deposited or transferred
     * @param transactionType the type of the transaction (e.g., "DEPOSIT", "WITHDRAWAL", "TRANSFER")
     * @param amount        the amount involved in the transaction
     */
    public Transaction(Account fromAccount, Account toAccount, String transactionType, Double amount) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.timeStamp = LocalDateTime.now();
    }
}
