package com.example.openapiproject.client;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feginLoggerLevel() {
        return Logger.Level.FULL;
    }
}
