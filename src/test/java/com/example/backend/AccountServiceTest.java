package com.example.backend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.backend.dto.AccountDTO;
import com.example.backend.entity.Account;
import com.example.backend.entity.User;
import com.example.backend.exceptions.accountExceptions.CheckingAccountException;
import com.example.backend.repository.AccountRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountService accountService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testUser");

    }

    @Test
    void testCreateCheckingAccount_Success() {
        Account checkingAccount = new Account();
        checkingAccount.setAccountType("Checking");
        checkingAccount.setUser(testUser);

        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(accountRepository.existsByUser_IdAndAccountType(1L, "Checking")).thenReturn(false);
        when(accountRepository.save(any(Account.class))).thenReturn(checkingAccount);

        AccountDTO result = accountService.createAccount(checkingAccount).getBody();

        assertNotNull(result);
        assertEquals("Checking", result.getAccountType());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void testPreventDuplicateCheckingAccount() {
        Account checkingAccount = new Account();
        checkingAccount.setAccountType("Checking");
        checkingAccount.setUser(testUser);

        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(accountRepository.existsByUser_IdAndAccountType(1L, "Checking")).thenReturn(true);

        assertThrows(CheckingAccountException.class, () -> accountService.createAccount(checkingAccount));
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    void testGetAccountsByUserId() {
        Account account1 = new Account(1L, testUser, "Checking", 100.0);
        Account account2 = new Account(2L, testUser, "Savings", 200.0);
        List<Account> accounts = List.of(account1, account2);

        when(accountRepository.findByUser_Id(1L)).thenReturn(accounts);

        List<AccountDTO> result = accountService.getAccountsByUserId(1L).getBody();

        assertEquals(2, result.size());
        verify(accountRepository, times(1)).findByUser_Id(1L);
    }
}
