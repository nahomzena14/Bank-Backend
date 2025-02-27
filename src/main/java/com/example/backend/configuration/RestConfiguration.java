package com.example.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for creating a {@link RestTemplate} bean.
 * The {@link RestTemplate} is used for making HTTP requests to external APIs.
 */
@org.springframework.context.annotation.Configuration
public class RestConfiguration {

    /**
     * Creates and provides a {@link RestTemplate} bean.
     * This bean allows the application to send HTTP requests and handle responses.
     *
     * @return a new instance of {@link RestTemplate}.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
