package com.example.backend.exceptions;

import com.example.backend.exceptions.transactionException.TransactionException;
import com.example.backend.exceptions.accountExceptions.*;
import com.example.backend.exceptions.userExceptions.UserNameException;
import com.example.backend.exceptions.userExceptions.UserNamePasswordFullnameNotValidException;
import com.example.backend.exceptions.userExceptions.UserNamePasswordNotValidException;
import com.example.backend.exceptions.userExceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the application.
 * This class handles exceptions thrown across all controllers and provides appropriate HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the {@link UserNameException} when the username already exists.
     *
     * @param ex the exception
     * @return the error message
     */
    @ExceptionHandler(UserNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserAlreadyExistsException(UserNameException ex) {
        return ex.getMessage();
    }

    /**
     * Handles the {@link UserNotFoundException} when a user is not found.
     *
     * @param ex the exception
     * @return the error message
     */
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserNotFoundException(UserNotFoundException ex) {
        return ex.getMessage();
    }

    /**
     * Handles the {@link UserNamePasswordFullnameNotValidException} for invalid user credentials.
     *
     * @param ex the exception
     * @return the error message
     */
    @ExceptionHandler(UserNamePasswordFullnameNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserNamePasswordException(UserNamePasswordFullnameNotValidException ex) {
        return ex.getMessage();
    }

    /**
     * Handles the {@link AccountAttributesException} when account attributes are invalid.
     *
     * @param ex the exception
     * @return the error message
     */
    @ExceptionHandler(AccountAttributesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAccountAttributesException(AccountAttributesException ex) {
        return ex.getMessage();
    }

    /**
     * Handles the {@link TransactionException} when there is an issue with a transaction.
     *
     * @param ex the exception
     * @return the error message
     */
    @ExceptionHandler(TransactionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleTransactionException(TransactionException ex) {
        return ex.getMessage();
    }

    /**
     * Handles the {@link AccountTransferException} when a transfer fails.
     *
     * @param ex the exception
     * @return the error message
     */
    @ExceptionHandler(AccountTransferException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAccountTransferException(AccountTransferException ex) {
        return ex.getMessage();
    }

    /**
     * Handles the {@link AccountNotFoundException} when an account is not found.
     *
     * @param ex the exception
     * @return the error message
     */
    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAccountNotFoundException(AccountNotFoundException ex) {
        return ex.getMessage();
    }

    /**
     * Handles the {@link InsufficientBalanceException} when there are insufficient funds for a transaction.
     *
     * @param ex the exception
     * @return the error message
     */
    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInsufficientBalanceException(InsufficientBalanceException ex) {
        return ex.getMessage();
    }

    /**
     * Handles the {@link CheckingAccountException} when a user already has a checking account.
     *
     * @param ex the exception
     * @return the error message
     */
    @ExceptionHandler(CheckingAccountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleCheckingAccountException(CheckingAccountException ex) {
        return ex.getMessage();
    }

    /**
     * Handles the {@link UserNamePasswordNotValidException} when the username or password is invalid.
     *
     * @param ex the exception
     * @return the error message
     */
    @ExceptionHandler(UserNamePasswordNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserNamePasswordException(UserNamePasswordNotValidException ex) {
        return ex.getMessage();
    }
}
