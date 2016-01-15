package com.mustafinsa.spring.eshop.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DBErrorHandler {

    @ExceptionHandler(DataAccessException.class)
    public String handleDBException(DataAccessException ex) {
        ex.printStackTrace();
        return "error";
    }
}
