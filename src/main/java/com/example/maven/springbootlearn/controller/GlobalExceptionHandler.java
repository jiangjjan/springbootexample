package com.example.maven.springbootlearn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handException(HttpServletRequest req, Exception ex) {
        System.out.println("this is global handler "+ex);
        return "000000";
    }
    @ExceptionHandler(AException.class)
    public String handAException(HttpServletRequest req, AException ex) {
        System.out.println("this is aException handler "+ex);
        return "000001";
    }
}
