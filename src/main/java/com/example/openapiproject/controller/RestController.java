package com.example.openapiproject.controller;

import com.example.openapiproject.service.OpenAPIReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping
@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final OpenAPIReaderService openAPIReaderService;

    @GetMapping("/getAptList")
    public void test() {
        openAPIReaderService.allAptSearchTest();
    }
}
