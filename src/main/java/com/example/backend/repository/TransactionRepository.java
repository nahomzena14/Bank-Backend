package com.example.backend.repository;

import com.example.backend.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing {@link Transaction} entities in the database.
 * Provides methods for querying and manipulating transaction data.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Retrieves a list of transactions where the specified account ID is either the sender
     * (fromAccount) or the receiver (toAccount).
     *
     * @param fromAccountId the account ID of the sender
     * @param toAccountId the account ID of the receiver
     * @return a list of {@link Transaction} entities involving the specified accounts
     */
    List<Transaction> findByFromAccount_IdOrToAccount_Id(Long fromAccountId, Long toAccountId);
}
