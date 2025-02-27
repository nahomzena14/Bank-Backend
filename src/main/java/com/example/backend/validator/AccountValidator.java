package com.example.backend.validator;

import com.example.backend.entity.Account;
import com.example.backend.exceptions.accountExceptions.AccountAttributesException;
import lombok.Data;

@Data
public class AccountValidator {

    // Default constructor is not required as @Data will generate it
    // Public constructor is not required unless you want to initialize some values at object creation

    /**
     * Validates the attributes of an Account.
     * Throws AccountAttributesException if any validation fails.
     */
    public static void validator(Account account) {

        if (account.getBalance() < 0) {
            throw new AccountAttributesException("Account balance cannot be negative.");
        }

        if (!"Checking".equalsIgnoreCase(account.getAccountType()) && !"Savings".equalsIgnoreCase(account.getAccountType())) {
            throw new AccountAttributesException("Invalid account type. Only 'Checking' or 'Savings' are allowed.");
        }

    }
}
