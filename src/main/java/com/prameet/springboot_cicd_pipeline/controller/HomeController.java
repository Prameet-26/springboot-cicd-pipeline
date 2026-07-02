package com.prameet.springboot_cicd_pipeline.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Spring Boot CI/CD Pipeline!";
    }

    @GetMapping("/health")
    public String health() {
        return "Application is running successfully!";
    }
}
