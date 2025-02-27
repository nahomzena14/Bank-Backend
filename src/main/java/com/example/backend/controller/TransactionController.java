package com.example.backend.controller;

import com.example.backend.services.TransactionService;
import com.example.backend.entity.Transaction;
import com.example.backend.dto.TransactionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling transaction-related operations.
 * Provides endpoints for retrieving transaction history, deposits, withdrawals, and transfers.
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    /**
     * Constructs a {@code TransactionController} with the required dependencies.
     *
     * @param transactionService the service handling transaction operations
     */
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Retrieves the transaction history for a specific account.
     *
     * @param accountId the ID of the account whose transactions are to be retrieved
     * @return a {@link ResponseEntity} containing a list of {@link TransactionDTO} objects
     */
    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionByAccountId(@PathVariable Long accountId) {
        return transactionService.getTransactionHistory(accountId);
    }

    /**
     * Processes a deposit transaction.
     *
     * @param transaction the transaction details including account ID and amount
     * @return a {@link ResponseEntity} containing the transaction details as a {@link TransactionDTO}
     */
    @PostMapping("/deposit")
    public ResponseEntity<TransactionDTO> deposit(@RequestBody Transaction transaction) {
        return transactionService.deposit(transaction);
    }

    /**
     * Processes a withdrawal transaction.
     *
     * @param transaction the transaction details including account ID and amount
     * @return a {@link ResponseEntity} containing the transaction details as a {@link TransactionDTO}
     */
    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDTO> withdraw(@RequestBody Transaction transaction) {
        return transactionService.withdraw(transaction);
    }

    /**
     * Processes a transfer transaction between accounts.
     *
     * @param transaction the transaction details including sender, receiver, and amount
     * @return a {@link ResponseEntity} containing the transaction details as a {@link TransactionDTO}
     */
    @PostMapping("/transfer")
    public ResponseEntity<TransactionDTO> transfer(@RequestBody Transaction transaction) {
        return transactionService.transfer(transaction);
    }
}
