package com.example.backend.repository;

import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing {@link User} entities in the database.
 * Provides methods for querying and manipulating user data.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     /**
      * Retrieves a {@link User} entity by its username.
      *
      * @param username the username of the user
      * @return an {@link Optional} containing the found user or empty if not found
      */
     Optional<User> findByUsername(String username);

}
