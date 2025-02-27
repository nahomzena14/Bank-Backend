package com.example.backend.repository;

import com.example.backend.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing {@link Account} entities in the database.
 * Provides methods for querying and manipulating account data.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

   /**
    * Finds all accounts associated with a specific user ID.
    *
    * @param userId the user ID
    * @return a list of {@link Account} entities associated with the given user ID
    */
   List<Account> findByUser_Id(Long userId);

   /**
    * Checks if a user already has an account of a specific type (e.g., checking or saving).
    *
    * @param userId the user ID
    * @param accountType the type of account (e.g., "checking", "saving")
    * @return true if the user already has an account of the specified type, otherwise false
    */
   boolean existsByUser_IdAndAccountType(Long userId, String accountType);

}
