package com.example.backend.controller;

import com.example.backend.services.AccountService;
import com.example.backend.entity.Account;
import com.example.backend.dto.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing bank account operations
 * Handles account creation and retrieval endpoints
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Retrieves all accounts associated with a specific user
     * @param userId The ID of the user whose accounts to retrieve
     * @return List of accounts wrapped in ResponseEntity
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AccountDTO>> getAccountsByUserId(@PathVariable Long userId) {
        return accountService.getAccountsByUserId(userId);
    }

    /**
     * Creates a new bank account
     * @param account The account details to create
     * @return The created account wrapped in ResponseEntity
     */
    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }
}
