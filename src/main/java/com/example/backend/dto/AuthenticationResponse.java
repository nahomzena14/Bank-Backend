package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for authentication responses.
 * This class is used to return the JWT token after successful authentication.
 */
@Data
@AllArgsConstructor
public class AuthenticationResponse {

    private String token;
}
