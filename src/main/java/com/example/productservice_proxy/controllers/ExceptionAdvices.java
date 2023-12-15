package com.example.productservice_proxy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Exception Advice: Something is wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
