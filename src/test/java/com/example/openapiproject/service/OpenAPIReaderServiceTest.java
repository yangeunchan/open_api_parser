package com.example.openapiproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenAPIReaderServiceTest {
    @Autowired
    private OpenAPIReaderService openAPIReaderService;

    @Test
    public void test() {
        openAPIReaderService.allAptSearchTest();
    }
}