package com.example.backend.services;

import com.example.backend.entity.Account;
import com.example.backend.entity.Transaction;
import com.example.backend.dto.TransactionDTO;
import com.example.backend.exceptions.accountExceptions.AccountNotFoundException;
import com.example.backend.exceptions.accountExceptions.AccountTransferException;
import com.example.backend.exceptions.accountExceptions.InsufficientBalanceException;
import com.example.backend.repository.AccountRepository;
import com.example.backend.repository.TransactionRepository;
import com.example.backend.validator.TransactionValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    // Helper method to fetch account by ID and throw exception if not found
    private Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);
    }

    // Helper method to determine transaction type
    private String getTransactionType(Transaction transaction, Long accountId) {
        if (transaction.getFromAccount() == null) {
            return "Deposit"; // If fromAccount is null, it's a deposit
        } else if (transaction.getToAccount() == null) {
            return "Withdrawal"; // If toAccount is null, it's a withdrawal
        } else if (transaction.getFromAccount().getId().equals(accountId)) {
            return "Transfer Out"; // If accountId is sender, it's Transfer Out
        } else {
            return "Transfer In"; // Otherwise, it's Transfer In
        }
    }

    public ResponseEntity<List<TransactionDTO>> getTransactionHistory(Long accountId) {
        List<Transaction> transactionList = transactionRepository.findByFromAccount_IdOrToAccount_Id(accountId, accountId);

        List<TransactionDTO> transactionDTOS = transactionList.stream()
                .map(transaction -> new TransactionDTO(
                        transaction.getTransactionId(),
                        getTransactionType(transaction, accountId),
                        transaction.getAmount(),
                        transaction.getTimeStamp(),
                        transaction.getFromAccount() != null ? transaction.getFromAccount().getId() : null,
                        transaction.getToAccount() != null ? transaction.getToAccount().getId() : null
                ))
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(transactionDTOS);
    }

    @Transactional
    public ResponseEntity<TransactionDTO> deposit(Transaction transaction) {
        Account account = getAccountById(transaction.getToAccount().getId());

        TransactionValidator.validate(transaction.getAmount());

        account.setBalance(transaction.getAmount() + account.getBalance());
        accountRepository.save(account);

        Transaction newTransaction = transactionRepository.save(new Transaction(null, account, "Deposit", transaction.getAmount()));
        return ResponseEntity.status(HttpStatus.OK).body(new TransactionDTO(newTransaction));
    }

    @Transactional
    public ResponseEntity<TransactionDTO> withdraw(Transaction transaction) {
        Account account = getAccountById(transaction.getFromAccount().getId());

        TransactionValidator.validate(transaction.getAmount());

        if (account.getBalance() >= transaction.getAmount()) {
            account.setBalance(account.getBalance() - transaction.getAmount());
            accountRepository.save(account);

            Transaction newTransaction = transactionRepository.save(new Transaction(account, null, "Withdrawal", transaction.getAmount()));
            return ResponseEntity.status(HttpStatus.OK).body(new TransactionDTO(newTransaction));
        }
        throw new InsufficientBalanceException();
    }

    @Transactional
    public ResponseEntity<TransactionDTO> transfer(Transaction transaction) {
        Long fromId = transaction.getFromAccount().getId();
        Long toId = transaction.getToAccount().getId();
        double amount = transaction.getAmount();

        if (fromId.equals(toId)) {
            throw new AccountTransferException("Transfer failed: The source and destination accounts cannot be the same");
        }

        Account fromAccount = getAccountById(fromId);
        Account toAccount = getAccountById(toId);

        TransactionValidator.validate(amount);

        if (fromAccount.getBalance() >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);

            Transaction newTransaction = transactionRepository.save(new Transaction(fromAccount, toAccount, "Transfer", amount));
            return ResponseEntity.status(HttpStatus.OK).body(new TransactionDTO(newTransaction));
        }

        throw new InsufficientBalanceException();
    }
}
