package com.example.backend.configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger OpenAPI documentation.
 * Defines API metadata such as title, version, and description.
 */
@Configuration
public class SwaggerConfiguration {

    /**
     * Creates and provides an {@link OpenAPI} bean with custom API documentation settings.
     *
     * @return a configured {@link OpenAPI} instance with title, version, and description.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Bank API Documentation")
                        .version("1.0")
                        .description("API for managing users, accounts, and transactions"));
    }
}
