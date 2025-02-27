package com.example.backend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.backend.dto.TransactionDTO;
import com.example.backend.entity.Account;
import com.example.backend.entity.Transaction;
import com.example.backend.repository.AccountRepository;
import com.example.backend.repository.TransactionRepository;
import com.example.backend.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Account testAccount;

    @BeforeEach
    void setUp() {
        testAccount = new Account();
        testAccount.setId(1L);
        testAccount.setAccountType("CHECKING");
        testAccount.setBalance(1000.0);
    }

    @Test
    void testCreateTransaction_Success() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setToAccount(testAccount);
        transaction.setAmount(200.0);
        transaction.setTransactionType("DEPOSIT");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(testAccount));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        TransactionDTO result = transactionService.deposit(transaction).getBody();

        assertNotNull(result);
        assertEquals(200.0, result.getAmount());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void testGetTransactionsByAccountId() {
        Transaction transaction1 = new Transaction(testAccount,null , "DEPOSIT", 200.0);
        transaction1.setTransactionId(1L);
        Transaction transaction2 = new Transaction(testAccount,null, "WITHDRAWAL", 100.0);
        transaction2.setTransactionId(2L);
        List<Transaction> transactions = List.of(transaction1, transaction2);

        when(transactionRepository.findByFromAccount_IdOrToAccount_Id(1L,1L)).thenReturn(transactions);

        List<TransactionDTO> result = transactionService.getTransactionHistory(1L).getBody();

        assertEquals(2, result.size());
        verify(transactionRepository, times(1)).findByFromAccount_IdOrToAccount_Id(1L,1L);
    }
}
