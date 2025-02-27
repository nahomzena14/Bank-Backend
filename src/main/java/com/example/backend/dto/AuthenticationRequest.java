package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for authentication requests.
 * This class is used to capture user credentials during login.
 */
@Data
@AllArgsConstructor
public class AuthenticationRequest {

    private String username;
    private String password;
}
