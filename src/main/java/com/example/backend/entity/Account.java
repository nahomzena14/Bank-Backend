package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity representing a bank account.
 * Each account is associated with a {@link User} and has a specific type (e.g., checking or saving).
 */
@Entity
@Data
@Table(name = "accounts")
public class Account {

    /** Unique identifier for the account. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    /** The balance amount in the account. */
    @Column(name = "balance")
    private double balance;

    /** Type of account (e.g., checking or saving). */
    @Column(name = "account_type")
    private String accountType;

    /** The user who owns this account. */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** Default constructor. */
    public Account() {
    }

    /**
     * Constructs an {@code Account} with specified details.
     *
     * @param id          the unique ID of the account
     * @param user        the user who owns the account
     * @param accountType the type of the account (checking or saving)
     * @param balance     the initial balance of the account
     */
    public Account(long id, User user, String accountType, double balance) {
        this.id = id;
        this.user = user;
        this.accountType = accountType;
        this.balance = balance;
    }
}
