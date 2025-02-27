package com.example.backend.services;

import com.example.backend.entity.Account;
import com.example.backend.dto.AccountDTO;
import com.example.backend.entity.User;
import com.example.backend.exceptions.accountExceptions.CheckingAccountException;
import com.example.backend.exceptions.userExceptions.UserNotFoundException;
import com.example.backend.repository.AccountRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.validator.AccountValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<AccountDTO>> getAccountsByUserId(Long userId) {

        List<Account> accountList = accountRepository.findByUser_Id(userId);
        List<AccountDTO> accountDTOS = accountList.stream().map(AccountDTO::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(accountDTOS);
    }

    @Transactional
    public ResponseEntity<AccountDTO> createAccount(Account account) {
        Optional<User> user = userRepository.findById(account.getUser().getId());

        if (user.isPresent()) {

            // Check for existing checking account before creating a new one
            if (account.getAccountType().equals("Checking") && accountRepository.existsByUser_IdAndAccountType(user.get().getId(), account.getAccountType())) {
                throw new CheckingAccountException();
            }

            // Validate the account
            AccountValidator.validator(account);

            // Set the user and save the account
            account.setUser(user.get());
            Account savedAccount = accountRepository.save(account);

            return ResponseEntity.status(HttpStatus.CREATED).body(new AccountDTO(savedAccount));
        }

        throw new UserNotFoundException();

    }
}
