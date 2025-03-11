Bank Backend API - Spring Boot
Overview
This is a simple banking backend API built with Spring Boot and MySQL, designed for handling user authentication, accounts, transactions, and balance inquiries. It uses JWT authentication for security and supports checking and savings accounts.

Features
✅ User authentication with JWT (Spring Security)
✅ Users can have both savings and checking accounts (one of each)
✅ Separate transaction endpoints for deposit, withdraw, and transfer
✅ Transaction history tracking for all accounts
✅ Secure REST API following best practices
✅ MySQL integration for persistent storage
✅ Unit tests included for core functionalities
✅ Caching support for optimized performance

Tech Stack
Backend: Java 17, Spring Boot, Spring Security, Spring Data JPA
Database: MySQL
Authentication: JWT
Tools: Spring Initializr, Maven, JUnit

API Endpoints

Authentication
POST	/auth/register - Register a new user
POST	/auth/login	- Authenticate and get JWT token

Accounts
GET	/accounts	- Get user accounts	✅ Yes (JWT)

Transactions
POST	/transactions/deposit	- Deposit money into an account	✅ Yes (JWT)
POST	/transactions/withdraw	- Withdraw money from an account	✅ Yes (JWT)
POST	/transactions/transfer	- Transfer money between accounts	✅ Yes (JWT)
GET	/transactions/history	- View transaction history	✅ Yes (JWT)

