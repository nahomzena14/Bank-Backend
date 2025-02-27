package com.example.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for password encoding.
 * Defines a {@link PasswordEncoder} bean using BCrypt hashing.
 */
@Configuration
class PasswordEncoderConfig {

    /**
     * Creates and provides a {@link PasswordEncoder} bean.
     * Uses {@link BCryptPasswordEncoder} for secure password hashing.
     *
     * @return a {@link PasswordEncoder} instance with BCrypt encryption.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
