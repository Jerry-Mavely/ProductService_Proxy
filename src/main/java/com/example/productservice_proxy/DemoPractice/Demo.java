package com.example.productservice_proxy.DemoPractice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {
    @GetMapping("/hello")
    public String hello() {
        return "hello World 3";
    }
    @PostMapping("/hello")
    public String hello2() {
        return "hello World 2";
    }
}
