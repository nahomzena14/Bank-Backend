package com.example.backend.dto;

import com.example.backend.entity.Account;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing account details.
 * This class is used to transfer account data without exposing the entity directly.
 */
@Data
public class AccountDTO {

    private Long account_id;
    private double balance;
    private String accountType;
    private Long userId;
    private String userFullName;

    /**
     * Constructs an {@code AccountDTO} from an {@link Account} entity.
     *
     * @param account the account entity to be converted into a DTO
     */
    public AccountDTO(Account account) {
        this.account_id = account.getId();
        this.balance = account.getBalance();
        this.accountType = account.getAccountType();
        this.userId = account.getUser().getId();
        this.userFullName = account.getUser().getFullName();
    }
}
