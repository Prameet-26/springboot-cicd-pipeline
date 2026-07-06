package com.prameet.springboot_cicd_pipeline.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "CI/CD Pipeline is Working 🚀";
    }
}
